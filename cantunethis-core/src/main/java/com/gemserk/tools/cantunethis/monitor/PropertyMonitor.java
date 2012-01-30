package com.gemserk.tools.cantunethis.monitor;

import com.gemserk.properties.Property;

public class PropertyMonitor {

	// implement a way to store the state of a property to easily check differences with another state of the same property.

	Property<?> property;

	public PropertyMonitor(Property<?> property) {
		this.property = property;
	}

	public void update() {

	}

	public boolean wasModified() {
		return false;
	}

}
