package com.gemserk.tools.cantunethis.examples;

import com.gemserk.properties.Properties;
import com.gemserk.tools.cantunethis.CommonConstraints;
import com.gemserk.tools.cantunethis.PropertyManager;
import com.gemserk.tools.cantunethis.PropertyManagerImpl;
import com.gemserk.tools.cantunethis.editor.PropertiesEditor;
import com.gemserk.tools.cantunethis.properties.TunablePropertyBuilder;

public class ExampleApplication {

	public static class MyObject {

		public float speed;
		public boolean enabled;

	}

	public static void main(String[] argv) throws InterruptedException {
		PropertyManager propertyManager = new PropertyManagerImpl();

		MyObject myObject = new MyObject();
		
		myObject.speed = 5f;
		myObject.enabled = true;

		propertyManager.register("mainCharacter.speed", //
				TunablePropertyBuilder.tunableProperty(Properties.internalProperty(myObject, "speed")) //
						.constraint(CommonConstraints.ForFloats.MIN_CONSTRAINT, 1f) // here are the restrictions used by the slider in the editor
						.constraint(CommonConstraints.ForFloats.MAX_CONSTRAINT, 10f) //
						.constraint(CommonConstraints.ForFloats.SCALE_CONSTRAINT, 1f) //
						.build());

		propertyManager.register("mainCharacter.enabled", //
				TunablePropertyBuilder.tunableProperty(Properties.internalProperty(myObject, "enabled")) //
						.build());

		PropertiesEditor editor = new PropertiesEditor();
		editor.setPropertyManager(propertyManager);
		editor.start();
		
		while(true) {
			
			System.out.println("myObject.speed=" + myObject.speed);
			System.out.println("myObject.enabled=" + myObject.enabled);
			
			Thread.sleep(3000);
		}
		
	}

}
