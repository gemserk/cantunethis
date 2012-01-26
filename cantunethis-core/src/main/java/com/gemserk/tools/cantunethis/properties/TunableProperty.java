package com.gemserk.tools.cantunethis.properties;

import java.util.HashMap;
import java.util.Map;

import com.gemserk.properties.Property;

public class TunableProperty {
	
	private final Property property;
	private final Map<String, Object> constraints = new HashMap<String, Object>(); 
	
	public TunableProperty(Property property) {
		this.property = property;
	}
	
	public <T> Property<T> getProperty() {
		return property;
	}
	
	public void addConstraint(String id, Object constraint) {
		constraints.put(id, constraint);
	}

	public <K> K getConstraint(String id) {
		return (K) constraints.get(id);
	}

}