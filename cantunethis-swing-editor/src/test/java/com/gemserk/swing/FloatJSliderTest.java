package com.gemserk.swing;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class FloatJSliderTest {

	@Test
	public void testConstructor() {
		float min = 0.001f;
		float max = 0.005f;
		float scale = 0.001f;

		FloatJSlider slider = new FloatJSlider(min, max, 0.002f, scale);

		assertThat(slider.getMinimum(), IsEqual.equalTo(1));
		assertThat(slider.getMaximum(), IsEqual.equalTo(5));
		assertThat(slider.getValue(), IsEqual.equalTo(2));

		assertThat(slider.getMinorTickSpacing(), IsEqual.equalTo(1));
	}

	@Test
	public void testConstructor2() {
		float min = 100f;
		float max = 500f;
		float scale = 10f;

		FloatJSlider slider = new FloatJSlider(min, max, 250f, scale);

		assertThat(slider.getMinimum(), IsEqual.equalTo(10));
		assertThat(slider.getMaximum(), IsEqual.equalTo(50));
		assertThat(slider.getValue(), IsEqual.equalTo(25));

		assertThat(slider.getMinorTickSpacing(), IsEqual.equalTo(1));
	}

}
