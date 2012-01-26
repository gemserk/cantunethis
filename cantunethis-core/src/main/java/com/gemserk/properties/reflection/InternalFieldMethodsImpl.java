package com.gemserk.properties.reflection;

import java.lang.reflect.Method;

/**
 * Implements the InternalField interface by accessing the getter/setter methods of the field.
 * 
 * @author acoppes
 * 
 */
public class InternalFieldMethodsImpl implements InternalField {

	private final Method getterMethod;
	private final Method setterMethod;

	public InternalFieldMethodsImpl(Method getterMethod, Method setterMethod) {
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