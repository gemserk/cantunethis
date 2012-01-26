package com.gemserk.tools.cantunethis.properties;

import com.gemserk.properties.Property;

public class TunablePropertyBuilder {
	
	private static final TunablePropertyBuilder tunablePropertyBuilder = new TunablePropertyBuilder();
	
	public static TunablePropertyBuilder tunableProperty(Property property) {
		return tunablePropertyBuilder.property(property);
	}
	
	TunableProperty tunableProperty;
	
	private TunablePropertyBuilder property(Property property) {
		this.tunableProperty = new TunableProperty(property);
		return this;
	}

	public TunablePropertyBuilder constraint(String id, Object constraint) {
		this.tunableProperty.addConstraint(id, constraint);
		return this;
	}
	
	public TunableProperty build() {
		return tunableProperty;
	}

}
