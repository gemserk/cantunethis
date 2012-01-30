package com.gemserk.tools.cantunethis.monitor;

import com.gemserk.properties.Property;

/**
 * Implements PropertyState<T> interface with a default behaviour using the equals of states, use it only if T is mutable like a float, integer, other.
 * 
 * @author acoppes
 * 
 * @param <T>
 */
public class DefaultPropertyState<T> implements PropertyState<T> {

	private T state;

	@Override
	public void store(Property<T> property) {
		state = property.get();
	}

	@Override
	public boolean compare(Property<T> property) {
		if (state == null)
			return property.get() == null;
		return state.equals(property.get());
	}

}