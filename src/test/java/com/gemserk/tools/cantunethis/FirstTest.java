package com.gemserk.tools.cantunethis;

import static org.junit.Assert.assertThat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.gemserk.commons.reflection.ReflectionUtils;

public class FirstTest {

	static interface Property<T extends Object> {

		T get();

		void set(T value);

	}

	static class MockProperty<T> implements Property<T> {

		private T t;

		public MockProperty(T t) {
			this.t = t;
		}

		public T get() {
			return t;
		}

		public void set(T value) {
			this.t = value;
		}

	}

	class ReflectionProperty<T> implements Property<T> {

		Object object;
		InternalField field;

		public ReflectionProperty(Object object, InternalField field) {
			this.object = object;
			this.field = field;
		}

		public T get() {
			return (T) field.getValue(object);
		}

		public void set(T value) {
			field.setValue(object, value);
		}

	}

	static interface InternalField {

		Object getValue(Object obj);

		void setValue(Object obj, Object value);

	}

	// can be cached since it doesn't have state

	static class InternalFieldPublicImpl implements InternalField {

		Field field;

		public InternalFieldPublicImpl(Field field) {
			this.field = field;
		}

		public Object getValue(Object obj) {
			try {
				return field.get(obj);
			} catch (Exception e) {
				throw new RuntimeException("Field must be public", e);
			}
		}

		public void setValue(Object obj, Object value) {
			try {
				field.set(obj, value);
			} catch (Exception e) {
				throw new RuntimeException("Field must be public", e);
			}
		}

	}

	static class InternalFieldMethodsReflectionImpl implements InternalField {

		Method getterMethod;
		Method setterMethod;

		public InternalFieldMethodsReflectionImpl(Method getterMethod, Method setterMethod) {
			this.getterMethod = getterMethod;
			this.setterMethod = setterMethod;
		}

		public Object getValue(Object obj) {
			try {
				return getterMethod.invoke(obj, new Object[] {});
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		public void setValue(Object obj, Object value) {
			try {
				setterMethod.invoke(obj, value);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	static Method findMethod(Class clazz, String methodName) {
		Method[] methods = clazz.getMethods();
		for (Method method : methods)
			if (method.getName().equals(methodName))
				return method;
		return null;
	}

	static Field findField(Class clazz, String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	static InternalField internalFieldFromField(Class clazz, String fieldName) {
		Field field = findField(clazz, "name");
		field.setAccessible(true);
		return new InternalFieldPublicImpl(field);
	}

	static InternalField internalFieldFromMethods(Class clazz, String fieldName) {
		Method setter = ReflectionUtils.getSetter(clazz, fieldName);
		Method getter = ReflectionUtils.getGetter(clazz, fieldName);
		return new InternalFieldMethodsReflectionImpl(getter, setter);
	}

	static InternalField internalField(Class clazz, String fieldName) {
		if (ReflectionUtils.getGetter(clazz, fieldName) != null && ReflectionUtils.getSetter(clazz, fieldName) != null) {
			return internalFieldFromMethods(clazz, fieldName);
		} else {
			return internalFieldFromField(clazz, fieldName);
		}
	}

	static InternalField internalField(Object object, String fieldName) {
		return internalField(object.getClass(), fieldName);
	}

	static class MyObject {

		private String name;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}

	static class EditorProperty<T> {

		String id;
		Property<T> property;

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}

		public Property<T> get() {
			return property;
		}

		public EditorProperty(String id, Property<T> property) {
			this.id = id;
			this.property = property;
		}

	}

	// static class LimitedEditorProperty<T> extends EditorProperty<T> {
	//
	// private T min;
	// private T max;
	//
	// public T getMin() {
	// return min;
	// }
	//
	// public T getMax() {
	// return max;
	// }
	//
	// public LimitedEditorProperty(String id, Property<T> property, T min, T max) {
	// super(id, property);
	// this.min = min;
	// this.max = max;
	// }
	//
	// }

	static interface PropertyManager {

		void register(String id, EditorProperty<?> property);

		EditorProperty<?> get(String id);

		List<String> listProperties();

	}

	@Test
	public void test() {
		MyObject myObject = new MyObject();
		myObject.name = "Hello";

		Property<String> property = new ReflectionProperty<String>(myObject, internalField(myObject, "name"));

		assertThat(property.get(), IsEqual.equalTo("Hello"));
		property.set("World");
		assertThat(myObject.name, IsEqual.equalTo("World"));
	}

}
