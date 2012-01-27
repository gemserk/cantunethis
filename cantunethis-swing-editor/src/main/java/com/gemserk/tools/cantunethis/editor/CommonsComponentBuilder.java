package com.gemserk.tools.cantunethis.editor;

import java.util.Hashtable;

import javax.swing.JLabel;

import com.gemserk.swing.FloatJSlider;

public class CommonsComponentBuilder {

	public static FloatJSlider slider(float min, float max, float value, float scale) {
		FloatJSlider slider = new FloatJSlider(min, max, value, scale);

		Hashtable labels = new Hashtable();
		labels.put(slider.getMinimum(), new JLabel(Float.toString(min)));
		labels.put(slider.getMaximum(), new JLabel(Float.toString(max)));
		labels.put(slider.getValue(), new JLabel("*"));
		slider.setLabelTable(labels);
		
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		return slider;
	}

}