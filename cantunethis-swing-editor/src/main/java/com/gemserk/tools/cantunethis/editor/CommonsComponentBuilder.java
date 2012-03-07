package com.gemserk.tools.cantunethis.editor;

import java.util.Hashtable;

import javax.swing.JLabel;

import com.gemserk.properties.Property;
import com.gemserk.swing.FloatJSlider;
import com.gemserk.tools.cantunethis.PropertyManager;
import com.gemserk.tools.cantunethis.properties.TunableProperty;

public class CommonsComponentBuilder {
	
	public static class FloatJSliderEditorComponent extends FloatJSlider implements EditorComponent {

		private static final long serialVersionUID = 2819592512449538963L;

		PropertyManager propertyManager;
		String propertyId;

		float previousValue;

		public void manageProperty(PropertyManager propertyManager, String propertyId) {
			this.propertyManager = propertyManager;
			this.propertyId = propertyId;
		}

		public FloatJSliderEditorComponent(float min, float max, float scale) {
			super(min, max, scale);
		}

		public FloatJSliderEditorComponent(float min, float max, float value, float scale) {
			super(min, max, value, scale);
			previousValue = value;
		}

		@Override
		public void update() {
			TunableProperty tunableProperty = propertyManager.get(propertyId);
			if (tunableProperty == null)
				return;
			Property<Float> property = tunableProperty.getProperty();
			if (property == null)
				return;

			float floatValue = getFloatValue();

			if (floatValue != previousValue) {
				// if editor value changed then try to set the new value.
				property.set(floatValue);
				previousValue = floatValue;
			} else {
				// otherwise tries to get the value from the property.
				
				Float realValue = property.get();
				float floatRealValue = realValue.floatValue();
				
				if (previousValue != floatRealValue) {
					setFloatValue(realValue.floatValue());
					previousValue = realValue.floatValue();
				}
				
			}
		}

	}

	public static FloatJSliderEditorComponent slider(float min, float max, float value, float scale) {
		FloatJSliderEditorComponent slider = new FloatJSliderEditorComponent(min, max, value, scale);

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