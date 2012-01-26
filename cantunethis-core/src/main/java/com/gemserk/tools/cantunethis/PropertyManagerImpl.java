package com.gemserk.tools.cantunethis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.gemserk.tools.cantunethis.properties.TunableProperty;

public class PropertyManagerImpl implements PropertyManager {

	private final Map<String, TunableProperty> properties = new HashMap<String, TunableProperty>();
	private final HashSet<String> propertyIdentifiers = new HashSet<String>();

	public void register(String id, TunableProperty property) {
		properties.put(id, property);
		propertyIdentifiers.add(id);
	}

	public TunableProperty get(String id) {
		return properties.get(id);
	}

	public boolean contains(String id) {
		return properties.containsKey(id);
	}

	public Set<String> listProperties() {
		return propertyIdentifiers;
	}

}