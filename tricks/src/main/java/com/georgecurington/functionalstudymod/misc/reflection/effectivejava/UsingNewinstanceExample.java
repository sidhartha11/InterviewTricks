package com.georgecurington.functionalstudymod.misc.reflection.effectivejava;

import java.util.Arrays;
import java.util.Set;

/**
 * <pre>
 * Example taken from Effective Java 2nd Edition.
 * Item 53: Prefer interfaces to reflection
 * 
 * Item 40 contains the advice that you should use interfaces 
 * rather than classes as parameter types. More generally, 
 * you should favor the use of interfaces rather than 
 * classes to refer to objects. If appropriate interface 
 * types exist, then parameters, return values, 
 * variables, and fields should all be declared using 
 * interface types. The only time you really need to refer to an 
 * object’s class is when you’re creating it with a constructor. 
 * To make this concrete, consider the case of Vector, which is an 
 * implementation of the List interface. Get in the habit of typing this:
 * List<Subscriber> subscribers = new Vector<Subscriber>();
 * 
 * </pre>
 * 
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class UsingNewinstanceExample {

	public UsingNewinstanceExample() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creating a Set<String> via newInstance method
	 * after creating a class using the Class.forName 
	 * method. 
	 * @param args a classname arg1 arg2 .... 
	 * 
	 */
	public static void main(String ...args){
		// Translate the class name into a Class object
		Class<?> cl = null;
		
		try {
			cl = Class.forName(args[0]);
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found.");
			System.exit(1);
		}
		
		// instantiate the class
		Set<String> s = null;
		try {
			s = (Set<String>)cl.newInstance();
		} catch (IllegalAccessException e) {
			System.err.println("Class not accessible.");
			System.exit(1);
		} catch (InstantiationException e) {
			System.err.println("class not instantiable");
			System.exit(1);
		}
		
		// Exercise the set
		s.addAll(Arrays.asList(args).subList(1, args.length));
		System.out.println(s);
	}
}
