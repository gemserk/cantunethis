package com.gemserk.properties.reflection;

import java.lang.reflect.Field;

/**
 * Implements the InternalField API by accessing to a public field.
 * 
 * @author acoppes
 * 
 */
public class InternalFieldPublicImpl implements InternalField {

	private final Field field;

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