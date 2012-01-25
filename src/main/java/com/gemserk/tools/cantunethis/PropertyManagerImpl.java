package com.gemserk.tools.cantunethis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gemserk.properties.Property;

public class PropertyManagerImpl implements PropertyManager {

	@SuppressWarnings("rawtypes")
	private final Map<String, Property> properties = new HashMap<String, Property>();
	private final ArrayList<String> propertyIdentifiers = new ArrayList<String>();

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

	public List<String> listProperties() {
		return propertyIdentifiers;
	}

}