/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.lambdaclosure;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

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
public class Chapter2_1 {

	/**
	 * 
	 */
	public Chapter2_1() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BiFunction<String,String,String> concat = (a,b) -> a + b;
		callBiFunction(concat, "hello" , "world");
	}

	public static void callBiFunction(BiFunction<String,String,String> concat,String a, String b){
		Utility.p(concat.apply(a,b));
	}
	/**
	 * 
	 */
	private static void supplierExample() {
		Supplier<Integer> numbGen = () -> ThreadLocalRandom.current().nextInt();
	    doNumGen(numbGen);
	}

	private static void doNumGen(Supplier<Integer> numbGen) {
		Utility.p("gen()=" + numbGen.get());
	}

	/**
	 * 
	 */
	private static void example1() {
		String greeting = "Hello, ";
		Function<String,String> greeter = whom -> greeting + whom + "!";
		greetWorld(greeter);
	}
	
	public static void greetWorld(Function<String,String> greeter){
		System.out.println(greeter.apply("World"));
	}

}
