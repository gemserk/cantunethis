package com.gemserk.tools.cantunethis;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.gemserk.properties.InternalFieldProperty;
import com.gemserk.properties.Property;
import com.gemserk.properties.reflection.InternalFields;

public class FirstTest {

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

	static class MyObject {

		private String name;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
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

	@Test
	public void test() {
		MyObject myObject = new MyObject();
		myObject.name = "Hello";

		Property<String> property = new InternalFieldProperty<String>(myObject, InternalFields.internalField(myObject, "name"));

		assertThat(property.get(), IsEqual.equalTo("Hello"));
		property.set("World");
		assertThat(myObject.name, IsEqual.equalTo("World"));
	}

	PropertyManager propertyManager;

	@Test
	public void test2() {
		Property<Integer> property = propertyManager.get("prop1");
		property.set(50);
	}

}
