package com.gemserk.properties.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.gemserk.tools.cantunethis.reflection.ReflectionUtils;

public class InternalFields {

	public static InternalField internalFieldFromField(Class clazz, String fieldName) {
		try {
			Field field = ReflectionUtils.getClassField(clazz, fieldName);
			field.setAccessible(true);
			return new InternalFieldPublicImpl(field);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	public static InternalField internalFieldFromMethods(Class clazz, String fieldName) {
		Method setter = ReflectionUtils.getSetter(clazz, fieldName);
		Method getter = ReflectionUtils.getGetter(clazz, fieldName);
		return new InternalFieldMethodsImpl(getter, setter);
	}

	/**
	 * Returns an InternalField implementation using getter/setter methods if they exist or a direct field implementation otherwise.
	 * 
	 * @param clazz
	 *            The class of the object.
	 * @param fieldName
	 *            The name of the field to use when getting/setting values.
	 */
	public static InternalField internalField(Class clazz, String fieldName) {
		if (ReflectionUtils.getGetter(clazz, fieldName) != null && //
				ReflectionUtils.getSetter(clazz, fieldName) != null)
			return internalFieldFromMethods(clazz, fieldName);
		else
			return internalFieldFromField(clazz, fieldName);
	}

	public static InternalField internalField(Object object, String fieldName) {
		return internalField(object.getClass(), fieldName);
	}

}
