package com.gemserk.properties.reflection;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class InternalFieldMethodsImplTest {

	static class MyObject {

		private String name;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}

	@Test
	public void testGetFieldValue() {
		MyObject myObject = new MyObject();
		myObject.name = "Hello";
		InternalField internalField = InternalFields.internalFieldFromMethods(myObject.getClass(), "name");
		String value = (String) internalField.getValue(myObject);
		assertThat(value, IsEqual.equalTo("Hello"));
	}
	
	@Test
	public void testSetFieldValue() {
		MyObject myObject = new MyObject();
		myObject.name = "Hello";
		InternalField internalField = InternalFields.internalFieldFromMethods(myObject.getClass(), "name");
		internalField.setValue(myObject, "World");
		assertThat(myObject.name, IsEqual.equalTo("World"));
	}


}
