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

	@SuppressWarnings("unchecked")
	public <K> K getConstraint(String id) {
		return ((K) constraints.get(id));
	}
	
	@SuppressWarnings("unchecked")
	public <K> K getConstraint(String id, K defaultValue) {
		if (!constraints.containsKey(id))
			return defaultValue;
		return ((K) constraints.get(id));
	}

}