package com.gemserk.tools.cantunethis.properties;

import com.gemserk.properties.Property;

public class TunablePropertyBuilder<T> {
	
	private static final TunablePropertyBuilder tunablePropertyBuilder = new TunablePropertyBuilder();
	
	public static <T> TunablePropertyBuilder<T> tunableProperty(Property<T> property) {
		return tunablePropertyBuilder.property(property);
	}
	
	Property<T> property;
	
	private TunablePropertyBuilder property(Property<T> property) {
		this.property = property;
		return this;
	}
	
	public TunableProperty<T> build() {
		return new TunableProperty<T>(property);
	}

}
