package com.gemserk.tools.cantunethis;

import static org.junit.Assert.assertThat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class FirstTest {

	interface Property<T extends Object> {

		T get();

		void set(T value);

	}

	class ReflectionProperty<T> implements Property<T> {

		Object object;
		Field field;

		public ReflectionProperty(Object object, String field) {
			this.object = object;
			try {
				this.field = object.getClass().getDeclaredField(field);
				this.field.setAccessible(true);
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			} catch (NoSuchFieldException e) {
				throw new RuntimeException(e);
			}
		}

		public T get() {
			try {
				return (T) field.get(object);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		public void set(T value) {
			try {
				field.set(object, value);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

	}

	class Editor {

	}
	
	interface InternalField {

		Object getValue(Object obj);

		void setValue(Object obj, Object value);

	}
	
	class InternalFieldPublicImpl implements InternalField {

		private final Field field;

		public InternalFieldPublicImpl(Field field) {
			this.field = field;
		}

		public Object getValue(Object obj) {
			try {
				return field.get(obj);
			} catch (Exception e) {
				throw new RuntimeException("field must be public", e);
			}
		}

		public void setValue(Object obj, Object value) {
			try {
				field.set(obj, value);
			} catch (Exception e) {
				throw new RuntimeException("field must be public", e);
			}
		}

	}
	
	class InternalFieldMethodsReflectionImpl implements InternalField {

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
	
	protected static Method findMethod(Class clazz, String methodName) {
		Method[] methods = clazz.getMethods();
		for (Method method : methods)
			if (method.getName().equals(methodName))
				return method;
		return null;
	}
	
	static class MyObject {
		
		private String name;
		
	}

	@Test
	public void test() {
		MyObject myObject = new MyObject();
		myObject.name = "Hello";
		
		Property<String> property = new ReflectionProperty<String>(myObject, "name");
		
		assertThat(property.get(), IsEqual.equalTo("Hello"));
		property.set("World");
		assertThat(myObject.name,  IsEqual.equalTo("World"));
	}

}
