package com.gemserk.tools.cantunethis.monitor;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.gemserk.properties.Property;

public class DefaultPropertyStateTest {
	
	public static class DefaultPropertyState<T> implements PropertyState<T> {

		@Override
		public void store(Property<T> property) {
			// TODO Auto-generated function stub
			
		}

		@Override
		public boolean compare(Property<T> property) {
			// TODO Auto-generated function stub
			return false;
			
		}

	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
