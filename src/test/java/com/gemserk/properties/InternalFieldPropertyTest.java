package com.gemserk.properties;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.gemserk.properties.reflection.MockInternalField;


public class InternalFieldPropertyTest {

	@Test
	public void shouldUseInternalFieldWhenGettingValue() {
		MockInternalField mockInternalField = new MockInternalField();
		mockInternalField.value = "Hello";
		
		Property<String> property = new InternalFieldProperty<String>(null, mockInternalField);

		assertThat(property.get(), IsEqual.equalTo("Hello"));
	}
	
	@Test
	public void shouldUseInternalFieldWhenSettingValue() {
		MockInternalField mockInternalField = new MockInternalField();
		mockInternalField.value = "Hello";
		
		Property<String> property = new InternalFieldProperty<String>(null, mockInternalField);
		property.set("World");

		assertThat((String)mockInternalField.value, IsEqual.equalTo("World"));
	}

}
