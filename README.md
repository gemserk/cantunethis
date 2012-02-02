Description
------------

The objective of this project is to provide an easy way to declare stuff to be modified in runtime and to modify it in a graphical editor semi automatically built using the declared stuff. 

Basically, you declare in your code you want to be able to modify a field from a class and the editor will create a corresponding graphical component to edit that field. For example, if the field is a float, the editor will create a slider for it. 

It is mainly focused for games but should be able to be used in other environments. For example, you can declare you want to be able to modify the main character speed in runtime with some constraints like minimum and maximum values, suppose you have the class:

	Character {
		float speed;
	}

Then in some part of the code you can register a tunable property for the character speed field with metadata declaring a minimum and maximum values of 10 and 50 respectively:

	Character character = ...

	propertyManager.register("character.speed", //
		TunablePropertyBuilder.tunableProperty(Properties.internalProperty(character, "speed")) //
				.constraint(CommonConstraints.ForFloats.MIN_CONSTRAINT, 10f) // here are the restrictions used by the slider in the editor
				.constraint(CommonConstraints.ForFloats.MAX_CONSTRAINT, 50f) //
				.constraint(CommonConstraints.ForFloats.SCALE_CONSTRAINT, 5f) //
				.build());

![Editor Screenshot](http://www.gemserk.com/things/mrtoad3.png)

For now the library and tool is in alpha state and the API could still change a lot.

Features in mind
------------

* Declare values to be customized in runtime in an easy way.
* Declare metadata to customizable values to be able to customize later the graphical components.
* Default editor graphical components for default classes like float, integer, boolean, etc.
* Customize editor components for your own classes.
* Edit values of any device remotelly using your PC (for example, edit values on a running Android instance of a game in your desktop machine).

* Read features.txt for more.

Contributing
------------

Feel free to add issues, make forks or contact us directly if you have suggestions, bug reports or enhancements.

