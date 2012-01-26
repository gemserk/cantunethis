package com.gemserk.tools.cantunethis;

import java.util.Set;

import com.gemserk.tools.cantunethis.properties.TunableProperty;


/**
 * Provides an API to register and fetch tunable properties to be modified in runtime.
 * 
 * @author acoppes
 * 
 */
public interface PropertyManager {

	/**
	 * Registers a new TunableProperty with the specified identifier.
	 * 
	 * @param id
	 *            The identifier of the TunableProperty.
	 * @param property
	 *            The TunableProperty to be registered.
	 */
	void register(String id, TunableProperty property);

	/**
	 * Returns the TunableProperty identified by the identifier.
	 * 
	 * @param id
	 *            The identifier of the TunableProperty.
	 */
	TunableProperty get(String id);

	/**
	 * Returns true if it contains the TunableProperty identified by the identifier, false otherwise.
	 * 
	 * @param id
	 *            The identifier of the TunableProperty.
	 */
	boolean contains(String id);

	/**
	 * Returns the list of the identifiers of the registered properties.
	 */
	Set<String> listProperties();

}