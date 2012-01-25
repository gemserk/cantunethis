package com.gemserk.properties.reflection;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class InternalFieldPublicImplTest {

	static class MyObject {

		private String name;

	}

	@Test
	public void testGetFieldValue() {
		MyObject myObject = new MyObject();
		myObject.name = "Hello";
		InternalField internalField = InternalFields.internalFieldFromField(myObject.getClass(), "name");
		String value = (String) internalField.getValue(myObject);
		assertThat(value, IsEqual.equalTo("Hello"));
	}
	
	@Test
	public void testSetFieldValue() {
		MyObject myObject = new MyObject();
		myObject.name = "Hello";
		InternalField internalField = InternalFields.internalFieldFromField(myObject.getClass(), "name");
		internalField.setValue(myObject, "World");
		assertThat(myObject.name, IsEqual.equalTo("World"));
	}


}
