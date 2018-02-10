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
public class ExplicitPartialFunctionExample {

	/**
	 * 
	 */
	public ExplicitPartialFunctionExample() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * First we create a BiFunction which is simply a generic
		 * functional interface with 3 type parameters representing
		 * the first and second parameters and the return type.
		 */
		BiFunction<String,String,String> concat = (a,b) -> a + b;
		
		/**
		 * we then pass this functional interface of type BiFunction to 
		 * a method taking a BiFunction and a simple string.
		 * At this point nothing has really been executed.
		 * 
		 * applyPartial with simply compose yet another Function of type Function
		 * that will enable yet another function, greeFolks, to apply 
		 * parameters to the Function returned by applyPartial.
		 * 
		 * 
		 */
		greetFolks(applyPartial(concat, "Hello, "));
		
		

	}
	
	/**
	 * <pre>
	 * This applyPartial function takes a BiFunctional argument and
	 * a simple generic parameter. 
	 * It creates a new Function by returning a lambda expression 
	 * of type Function that passes its single input u as a parameter
	 * to the input BiFunction along with the firstArgument parameter.
	 * NOTE TO SELF: Why does Java make this such a fuss!!!!
	 * </pre>
	 * @param bif
	 * @param firstArgument
	 * @return
	 */
	public static <T,U,V> Function<U,V> applyPartial(
			BiFunction<T,U,V> bif , T firstArgument 
			) {
		return u -> bif.apply(firstArgument, u);
	}
	
	public static void greetFolks(Function<String,String> greeter){
		for ( String name : Arrays.asList("Alice", "Bob" , "Cathy")){
			
			System.out.println(greeter.apply(name));
		}
	}

}
