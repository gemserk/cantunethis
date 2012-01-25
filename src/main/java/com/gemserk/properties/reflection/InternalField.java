package com.gemserk.properties.reflection;

/**
 * Abstracts how to get and set a value to a field of a class.
 * 
 * @author acoppes
 * 
 */
public interface InternalField {

	/**
	 * Returns the value of the field from the specified Object instance.
	 * 
	 * @param obj
	 *            The object to where to get the value of the field.
	 */
	Object getValue(Object obj);

	/**
	 * Sets the specified value to the field of the specified Object instance.
	 * 
	 * @param obj
	 *            The object to where to set the value of the field.
	 * @param value
	 *            The value to set in the field.
	 */
	void setValue(Object obj, Object value);

}