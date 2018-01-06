/**
 * 
 */
package com.georgecurington.functionalstudymod.design.factory;

/**
 * <pre>
 * This is the public api to the internal
 * factory that exposes a static creator 
 * method to client.
 * </pre>
 * <pre>
 * Notes:
 * We can keep Factory class Singleton or we can keep the method 
 * that returns the subclass as static. Notice that based on the 
 * input parameter, different subclass is created and returned. 
 * getComputer is the factory method.
 * 
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class ComputerFactory {

	/**
	 * 
	 */
	public ComputerFactory() {
		// TODO Auto-generated constructor stub
	}

	public static Computer getComputer(String type, String ram, String hdd, String cpu) {
		if ("PC".equalsIgnoreCase(type))
			return new PC(ram, hdd, cpu);
		else if ("Server".equalsIgnoreCase(type))
			return new Server(ram, hdd, cpu);

		return null;
	}

}
