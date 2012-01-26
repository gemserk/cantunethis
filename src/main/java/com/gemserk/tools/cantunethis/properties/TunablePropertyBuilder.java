package com.gemserk.tools.cantunethis.properties;

import com.gemserk.properties.Property;

public class TunablePropertyBuilder<T> {
	
	private static final TunablePropertyBuilder tunablePropertyBuilder = new TunablePropertyBuilder();
	
	public static <T> TunablePropertyBuilder<T> tunableProperty(Property<T> property) {
		return tunablePropertyBuilder.property(property);
	}
	
	TunableProperty<T> tunableProperty;
	
	private TunablePropertyBuilder<T> property(Property<T> property) {
		this.tunableProperty = new TunableProperty<T>(property);
		return this;
	}

	public TunablePropertyBuilder<T> constraint(String id, Object constraint) {
		this.tunableProperty.addConstraint(id, constraint);
		return this;
	}
	
	public TunableProperty<T> build() {
		return tunableProperty;
	}

}
