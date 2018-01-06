/**
 * 
 */
package com.georgecurington.functionalstudymod.design.decorator;

/**
 * <pre>
 * Decorator – Decorator class implements the component interface and it has a 
 * HAS-A relationship with the component interface. 
 * The component variable should be accessible to the 
 * child decorator classes, so we will make this variable protected.
 * 
 * Decorator Design Pattern
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 *
 */
public class CarDecorator implements Car {

	protected Car car;
	
	public CarDecorator(Car car){
		this.car = car ;
	}
	@Override
	public void assemble() {
		this.car.assemble();
	}

}
