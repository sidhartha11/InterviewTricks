/**
 * 
 */
package com.georgecurington.functionalstudymod.design.decorator;

/**
 * <pre>
 * Concrete Decorators – Extending the base decorator functionality and 
 * modifying the component behavior accordingly. 
 * We can have concrete decorator classes as LuxuryCar and SportsCar.
 * </pre>
 */
public class SportsCar extends CarDecorator {

	public SportsCar(Car car) {
		super(car);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void assemble(){
		super.assemble();
		System.out.print(" Adding features of sports car");
	}

}
