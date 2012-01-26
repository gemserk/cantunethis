package com.gemserk.tools.cantunethis;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import com.gemserk.properties.MockProperty;
import com.gemserk.tools.cantunethis.properties.TunableProperty;

public class PropertyManagerTest {

	@Test
	public void testGetPropertyAfterRegister() {
		PropertyManager propertyManager = new PropertyManagerImpl();

		MockProperty<Integer> mockProperty = new MockProperty<Integer>(50);

		propertyManager.register("prop1", new TunableProperty(mockProperty));
		TunableProperty property = propertyManager.get("prop1");

		assertThat(property, IsNull.notNullValue());
	}
	
	@Test
	public void shouldReturnNullIfNoPropertyRegisteredWithTheSpecifiedIdentifier() {
		PropertyManager propertyManager = new PropertyManagerImpl();

		MockProperty<Integer> mockProperty = new MockProperty<Integer>(50);

		TunableProperty property = propertyManager.get("prop1");

		assertThat(property, IsNull.nullValue());
	}
	
	@Test
	public void shouldContainRegisteredProperty() {
		PropertyManager propertyManager = new PropertyManagerImpl();

		MockProperty<Integer> mockProperty = new MockProperty<Integer>(50);

		propertyManager.register("prop1", new TunableProperty(mockProperty));
		
		assertThat(propertyManager.contains("prop1"), IsEqual.equalTo(true));
	}
	
	@Test
	public void shouldNotContainNonRegisteredProperty() {
		PropertyManager propertyManager = new PropertyManagerImpl();

		MockProperty<Integer> mockProperty = new MockProperty<Integer>(50);

		propertyManager.register("prop1", new TunableProperty(mockProperty));
		
		assertThat(propertyManager.contains("prop2"), IsEqual.equalTo(false));
	}

}
