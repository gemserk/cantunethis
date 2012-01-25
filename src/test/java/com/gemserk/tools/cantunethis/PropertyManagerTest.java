package com.gemserk.tools.cantunethis;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import com.gemserk.properties.MockProperty;
import com.gemserk.properties.Property;

public class PropertyManagerTest {
	
	class PropertyManagerImpl implements PropertyManager {

		public void register(String id, Property<?> property) {
			// TODO Auto-generated function stub
			
		}

		public <T> Property<T> get(String id) {
			// TODO Auto-generated function stub
			return null;
			
		}

		public boolean contains(String id) {
			// TODO Auto-generated function stub
			return false;
			
		}

		public List<String> listProperties() {
			// TODO Auto-generated function stub
			return null;
			
		}
		
	}

	@Test
	public void test2() {
		PropertyManager propertyManager = new PropertyManagerImpl();
		
		MockProperty<Integer> mockProperty = new MockProperty<Integer>(50);
		
		propertyManager.register("prop1", mockProperty);
		Property<Integer> property = propertyManager.get("prop1");
		
		assertThat(property, IsNull.notNullValue());
		
	}

}
