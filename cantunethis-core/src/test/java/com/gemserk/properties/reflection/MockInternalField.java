package com.gemserk.properties.reflection;

public class MockInternalField implements InternalField {

	public Object value;

	public Object getValue(Object obj) {
		return value;
	}

	public void setValue(Object obj, Object value) {
		this.value = value;
	}

}