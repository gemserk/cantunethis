package com.gemserk.tools.cantunethis.editor;

import static org.junit.Assert.assertThat;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JSlider;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.gemserk.swing.FloatJSlider;

public class CommonComponentsBuilderTest {

	public static class CommonsComponentBuilder {

		public static FloatJSlider slider(float min, float max, float value, float scale) {
			FloatJSlider slider = new FloatJSlider(min, max, value, scale);

			Hashtable labels = new Hashtable();
			labels.put(slider.getMinimum(), Float.toString(min));
			labels.put(slider.getMaximum(), Float.toString(max));
			labels.put(slider.getValue(), "*");
			slider.setLabelTable(labels);

			return slider;
		}

	}

	@Test
	public void test() {
		float min = 0.001f;
		float max = 0.005f;
		float scale = 0.001f;

		JSlider slider = CommonsComponentBuilder.slider(min, max, 0.002f, scale);

		Dictionary labelTable = slider.getLabelTable();
		assertThat((String) labelTable.get(1), IsEqual.equalTo(Float.toString(min)));
		assertThat((String) labelTable.get(5), IsEqual.equalTo(Float.toString(max)));
	}

	@Test
	public void test2() {
		float min = 100f;
		float max = 500f;
		float scale = 10f;

		JSlider slider = CommonsComponentBuilder.slider(min, max, 250f, scale);

		Dictionary labelTable = slider.getLabelTable();
		assertThat((String) labelTable.get(10), IsEqual.equalTo(Float.toString(min)));
		assertThat((String) labelTable.get(50), IsEqual.equalTo(Float.toString(max)));
	}

}
