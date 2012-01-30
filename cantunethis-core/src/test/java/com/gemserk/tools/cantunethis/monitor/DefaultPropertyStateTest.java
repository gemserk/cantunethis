package com.gemserk.tools.cantunethis.monitor;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.gemserk.properties.MockProperty;

public class DefaultPropertyStateTest {

	@Test
	public void shouldReturnTrueIfPropertiesHoldSameValue() {
		MockProperty<Float> floatProperty = new MockProperty<Float>(50f);
		DefaultPropertyState<Float> propertyState = new DefaultPropertyState<Float>();
		propertyState.store(floatProperty);
		assertThat(propertyState.compare(floatProperty), IsEqual.equalTo(true));
	}

	@Test
	public void shouldReturnFalseIfPropertiesDoesntHoldSameValue() {
		MockProperty<Float> floatProperty = new MockProperty<Float>(50f);
		DefaultPropertyState<Float> propertyState = new DefaultPropertyState<Float>();
		propertyState.store(floatProperty);

		floatProperty.set(20f);

		assertThat(propertyState.compare(floatProperty), IsEqual.equalTo(false));
	}

	@Test
	public void shouldNotFailWithNullValuesAndReturnTrue() {
		MockProperty<Float> floatProperty = new MockProperty<Float>(null);
		DefaultPropertyState<Float> propertyState = new DefaultPropertyState<Float>();
		propertyState.store(floatProperty);
		assertThat(propertyState.compare(floatProperty), IsEqual.equalTo(true));
	}

	@Test
	public void compareShouldBeFalseWhenNullValueAndThenNonNullValue() {
		MockProperty<Float> floatProperty = new MockProperty<Float>(null);
		DefaultPropertyState<Float> propertyState = new DefaultPropertyState<Float>();
		propertyState.store(floatProperty);
		floatProperty.set(50f);
		assertThat(propertyState.compare(floatProperty), IsEqual.equalTo(false));
	}

}
