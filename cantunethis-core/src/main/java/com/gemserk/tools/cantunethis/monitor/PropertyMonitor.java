package com.gemserk.tools.cantunethis.monitor;

import com.gemserk.properties.Property;

@SuppressWarnings("rawtypes")
public class PropertyMonitor {

	// implement a way to store the state of a property to easily check differences with another state of the same property.

	Property property;
	PropertyState propertyState;
	
	boolean wasModified;

	public PropertyMonitor(Property property, PropertyState propertyState) {
		this.property = property;
		this.propertyState = propertyState;
		propertyState.store(property);
		wasModified = false;
	}

	public PropertyMonitor(Property property) {
		this(property, new DefaultPropertyState());
	}

	public void update() {
		wasModified = false;
		if (!propertyState.compare(property)) {
			wasModified = true;
			propertyState.store(property);
		}
	}

	public boolean wasModified() {
		return wasModified;
	}

}
