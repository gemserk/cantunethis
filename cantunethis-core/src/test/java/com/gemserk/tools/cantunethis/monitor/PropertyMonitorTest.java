package com.gemserk.tools.cantunethis.monitor;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.gemserk.properties.MockProperty;

public class PropertyMonitorTest {

	@Test
	public void wasModifiedShouldReturnFalseWhenThePropertyWasNotModified() {
		MockProperty<Float> floatProperty = new MockProperty<Float>(50f);
		PropertyMonitor propertyMonitor = new PropertyMonitor(floatProperty);
		propertyMonitor.update();
		assertThat(propertyMonitor.wasModified(), IsEqual.equalTo(false));
	}
	
	@Test
	public void wasModifiedShouldReturnTrueWhenThePropertyWasModified() {
		MockProperty<Float> floatProperty = new MockProperty<Float>(50f);
		PropertyMonitor propertyMonitor = new PropertyMonitor(floatProperty);
		floatProperty.set(20f);
		propertyMonitor.update();
		assertThat(propertyMonitor.wasModified(), IsEqual.equalTo(true));
	}

}
