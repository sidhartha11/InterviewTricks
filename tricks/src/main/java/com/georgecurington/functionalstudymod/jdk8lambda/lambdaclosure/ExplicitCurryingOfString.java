/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.lambdaclosure;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 9, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class ExplicitCurryingOfString {

	/**
	 * 
	 */
	public ExplicitCurryingOfString() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/** BiFunction taking two parameters and returning a type
		 * specified in the declaration. 
		 * Here the two parameters are String and String and return value String
		 */
		BiFunction<String,String,String> concat = (a,b) -> a + b;
		
		/**
		 * curriedConcat is a function that returns a function of the following
		 * type Function<String, Function<String,String>>
		 */
		Function<String,Function<String,String>> curriedConcat = 
				curry(concat);
		
		for (String greetings : Arrays.asList("Hello", "Guten Tag", "Bonjour")){
			greetFolks(curriedConcat.apply(greetings + ", "));
		}
	}
	
	public static <T, U, V> Function<T, Function<U,V>> curry ( BiFunction<T,U,V> bif){
		
		return t -> (u -> bif.apply(t,u));

	}
	
	/**
	 * This function just applys a string to the Functional parameter , greeter.
	 * @param greeter
	 */
	public static void greetFolks ( Function<String, String> greeter) {
		for ( String name : Arrays.asList("Alice", "Bob", "Cathy")){
			System.out.println(greeter.apply(name));
		}
	}

}
