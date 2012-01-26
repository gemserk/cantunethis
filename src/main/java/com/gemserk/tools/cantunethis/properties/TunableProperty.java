package com.gemserk.tools.cantunethis.properties;

import java.util.HashMap;
import java.util.Map;

import com.gemserk.properties.Property;

public class TunableProperty<T> {
	
	private final Property<T> property;
	private final Map<String, Object> constraints = new HashMap<String, Object>(); 
	
	public TunableProperty(Property<T> property) {
		this.property = property;
	}
	
	public Property<T> getProperty() {
		return property;
	}
	
	public void addConstraint(String constraintName, Object constraint) {
		constraints.put(constraintName, constraint);
	}

	public Object getConstraint(String constraintName) {
		return constraints.get(constraintName);
	}

}