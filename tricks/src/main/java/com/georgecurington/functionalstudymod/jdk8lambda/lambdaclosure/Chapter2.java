/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.lambdaclosure;

import java.util.function.Function;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 8, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class Chapter2 {

	/** example for anonomous inner classes as closures */
	
	public interface Greeter {
		String createGreeting(String whom);
	}
	/**
	 * 
	 */
	public Chapter2() {
		dosection_2();
	}

	private void dosection_2() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		showClosureExample();
		new Chapter2();

	}
	
	private static void showClosureExample() {
		final String greeting = "Hello, ";
		Greeter greeter = new Greeter(){

			@Override
			public String createGreeting(String whom) {
				// Close (ie: capture) the variable here
				return greeting + whom + "!";
			}
			
		};
		
		greetWorld(greeter);
	}

	public static void greetWorld(Greeter greeter) {
		System.out.println(greeter.createGreeting("world"));
	}
	public static <V> Function<V,V> identityFunction() {
		return value -> value;
	}
	
	public static <V> Function<V,V> identityFunciAIC() {
		return new Function<V,V>() {

			@Override
			public V apply(V value) {
				return value;
			}
		};
	}

}
