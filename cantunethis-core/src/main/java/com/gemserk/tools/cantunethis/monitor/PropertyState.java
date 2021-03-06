package com.gemserk.tools.cantunethis.monitor;

import com.gemserk.properties.Property;

/**
 * Stores the state of a Property in a custom way to be able to check it later.
 * 
 * @author acoppes
 * 
 * @param <T>
 */
public interface PropertyState<T> {

	/**
	 * Stores the state of the specified Object.
	 * 
	 * @param t
	 *            The Object source of the state to store.
	 */
	void store(Property<T> property);

	/**
	 * Returns true whenever the internal state of the specified Object is the same to the stored state, false otherwise.
	 * 
	 * @param t
	 *            The Object from where to get the state to check.
	 */
	boolean compare(Property<T> property);

}