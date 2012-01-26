package com.gemserk.properties;

import com.gemserk.properties.Property;

public class MockProperty<T> implements Property<T> {

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