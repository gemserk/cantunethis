package com.gemserk.properties;

import com.gemserk.properties.reflection.InternalField;

/**
 * Implements the Property<T> API by accessing an internal field of an Object instance.
 * 
 * @author acoppes
 * 
 */
public class InternalFieldProperty<T> implements Property<T> {

	private final Object object;
	private final InternalField field;

	public InternalFieldProperty(Object object, InternalField field) {
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