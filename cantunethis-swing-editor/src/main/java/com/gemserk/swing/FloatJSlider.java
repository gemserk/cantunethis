package com.gemserk.swing;

import javax.swing.JSlider;

public class FloatJSlider extends JSlider {

	private static final long serialVersionUID = 1887702568856516783L;

	private float scale;

	public float getScale() {
		return scale;
	}

	public FloatJSlider(float min, float max, float scale) {
		this(min, max, min, scale);
	}

	public FloatJSlider(float min, float max, float value, float scale) {
		int minimum = Math.round(min / scale);
		int maximum = Math.round(max / scale);
		int sliderValue = Math.round(value / scale);

		setMinimum(minimum);
		setMaximum(maximum);
		setValue(sliderValue);

		setMinorTickSpacing(1);

		this.scale = scale;
	}

	public float getFloatValue() {
		return ((float) getValue()) * scale;
	}
	
	public void setFloatValue(float value) {
		setValue(Math.round(value / scale));
	}

}