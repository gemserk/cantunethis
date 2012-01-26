package com.gemserk.properties;

import com.gemserk.properties.reflection.InternalFields;

public class Properties {
	
	public static <T> Property<T> internalProperty(Object object, String fieldName) {
		return new InternalFieldProperty<T>(object, InternalFields.internalField(object, fieldName));
	}

}
