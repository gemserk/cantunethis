package com.gemserk.tools.cantunethis;

import java.util.List;

import com.gemserk.properties.Property;


/**
 * Provides an API to register and fetch Properties to be modified in runtime.
 * 
 * @author acoppes
 * 
 */
public interface PropertyManager {

	/**
	 * Registers a new Property with the specified identifier.
	 * 
	 * @param id
	 *            The identifier of the EditorProperty.
	 * @param property
	 *            The Property to be registered.
	 */
	void register(String id, Property<?> property);

	/**
	 * Returns the Property identified by the identifier.
	 * 
	 * @param id
	 *            The identifier of the Property.
	 */
	<T> Property<T> get(String id);

	/**
	 * Returns true if it contains the Property identified by the identifier, false otherwise.
	 * 
	 * @param id
	 *            The identifier of the Property.
	 */
	boolean contains(String id);

	/**
	 * Returns the list of the identifiers of the registered properties.
	 */
	List<String> listProperties();

}