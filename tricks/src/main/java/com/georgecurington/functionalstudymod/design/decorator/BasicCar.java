/**
 * 
 */
package com.georgecurington.functionalstudymod.design.decorator;

/**
 * <pre>
 * Component Implementation – The basic implementation of the component interface. 
 * We can have BasicCar class as our component implementation.
 * 
 * Decorator Design Pattern
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class BasicCar implements Car {

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.decorator.Car#assemble()
	 */
	@Override
	public void assemble() {
		System.out.println("Basic Car Implementation");

	}

}
