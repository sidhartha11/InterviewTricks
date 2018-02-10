/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.lambdaclosure;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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
public class ConsumerExample {

	/**
	  * Represents an operation that accepts a single input argument and returns no
      * result. Unlike most other functional interfaces, {@code Consumer} is expected
      * to operate via side-effects.
      *
      * <p>This is a <a href="package-summary.html">functional interface</a>
      * whose functional method is {@link #accept(Object)}.
      *
      * @param <T> the type of the input to the operation
      * @FunctionalInterface
      *     public interface Consumer<T> 
      * @param t the input argument
      *     void accept(T t);
	  * 
	 */
	public ConsumerExample() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		greetFolks();
		concat();

	}
	
	public static void greetFolks() {
		    Consumer<String> doGreet = name -> System.out.println("Hello, " + name);
		    for ( String name : Arrays.asList("Alice" , "Bob" , "Cathy")) {
		    	doGreet.accept(name);
		    }
	}
	
	public static void concat () { 
		BiConsumer<String, String> printConcat = ( left , right ) ->
		 System.out.println( left + right );
		 
		 for ( String name : Arrays.asList("Alice" , "Bob" , "Cathy")){
			 printConcat.accept("Goodbye ," , name);
		 }
		 
	}

}
