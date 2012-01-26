package com.gemserk.tools.cantunethis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.gemserk.properties.Property;

public class PropertyManagerImpl implements PropertyManager {

	@SuppressWarnings("rawtypes")
	private final Map<String, Property> properties = new HashMap<String, Property>();
	private final HashSet<String> propertyIdentifiers = new HashSet<String>();

	public void register(String id, Property<?> property) {
		properties.put(id, property);
		propertyIdentifiers.add(id);
	}

	public <T> Property<T> get(String id) {
		return (Property<T>) properties.get(id);
	}

	public boolean contains(String id) {
		return properties.containsKey(id);
	}

	public Set<String> listProperties() {
		return propertyIdentifiers;
	}

}