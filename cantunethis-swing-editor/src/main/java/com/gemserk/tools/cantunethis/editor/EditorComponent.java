package com.gemserk.tools.cantunethis.editor;

import com.gemserk.tools.cantunethis.properties.TunableProperty;


public interface EditorComponent {

	/**
	 * Called to synchronize the values of the component with the property and vice versa.
	 */
	void update(TunableProperty tunableProperty);
	
	void setPropertyId(String propertyId);
	
	String getPropertyId();
	
}
