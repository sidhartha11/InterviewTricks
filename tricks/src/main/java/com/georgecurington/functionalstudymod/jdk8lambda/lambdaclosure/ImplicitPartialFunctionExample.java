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
public class ImplicitPartialFunctionExample {

	/**
	 * 
	 */
	public ImplicitPartialFunctionExample() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 *
         * @param <T> the type of the first argument to the function
         * @param <U> the type of the second argument to the function
         * @param <R> the type of the result of the function
         *
         *     @FunctionalInterface
         *     public interface BiFunction<T, U, R> {
         * Here we define a BiFunction type that simply
         * concatenates its two input arguments and returns
         * the result.
		 */
		BiFunction<String,String,String> concat = 
				(a,b) -> a + b ;
	    /**
	     * 
	     * Here we are passing a functional parameter of tpe
	     * function to the greetFolks method.
	     * This function is being passed an application of the 
	     * concat function. The whom parameter is not applied
	     * to concat until the actual greetFolks method is called.
	     * This is a very clumsy application of partial function
	     * technique found in functional languages like Scala.
	     * concat appears to be partially executed
	     */
	    greetFolks(whom -> concat.apply("Hello, ", whom));

	}
	
	/**
	 * Here greetFolks take a Functional parameter of type Function.
     *
     * @param <T> the type of the input to the function
     * @param <R> the type of the result of the function
     * @FunctionalInterface
     *     public interface Function<T, R> {
	 * @param greeter
	 */
	public static void greetFolks ( Function<String,String> greeter) {
		for ( String name : Arrays.asList("Alice", "Bob", "cathy")) {
			System.out.println(greeter.apply(name));
		}
	}

}
