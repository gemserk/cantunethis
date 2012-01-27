package com.gemserk.tools.cantunethis.editor;

import static org.junit.Assert.assertThat;

import java.util.Dictionary;

import javax.swing.JLabel;
import javax.swing.JSlider;

import org.hamcrest.core.IsEqual;
import org.junit.Test;


public class CommonComponentsBuilderTest {

	@Test
	public void test() {
		float min = 0.001f;
		float max = 0.005f;
		float scale = 0.001f;

		JSlider slider = CommonsComponentBuilder.slider(min, max, 0.002f, scale);

		Dictionary labelTable = slider.getLabelTable();
		assertThat(((JLabel) labelTable.get(1)).getText(), IsEqual.equalTo(Float.toString(min)));
		assertThat(((JLabel) labelTable.get(5)).getText(), IsEqual.equalTo(Float.toString(max)));
	}

	@Test
	public void test2() {
		float min = 100f;
		float max = 500f;
		float scale = 10f;

		JSlider slider = CommonsComponentBuilder.slider(min, max, 250f, scale);

		Dictionary labelTable = slider.getLabelTable();
		assertThat(((JLabel) labelTable.get(10)).getText(), IsEqual.equalTo(Float.toString(min)));
		assertThat(((JLabel) labelTable.get(50)).getText(), IsEqual.equalTo(Float.toString(max)));
	}

}
