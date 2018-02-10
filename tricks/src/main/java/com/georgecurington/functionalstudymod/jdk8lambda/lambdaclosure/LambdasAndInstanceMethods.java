/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.lambdaclosure;

import java.beans.ExceptionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

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
public class LambdasAndInstanceMethods {
	

	/**
	 * 
	 * @param <T> the type of the operand and result of the operator
	 * @FunctionalInterface
	 *   public interface UnaryOperator<T> extends Function<T, T> {
	 *   
     * @FunctionalInterface
     * public interface IntFunction<R> {
     * Represents a function that accepts an int-valued argument and produces a
     * result.  This is the {@code int}-consuming primitive specialization for
	 *   
	 */
	Consumer<String> print = System.out::println;
	UnaryOperator<String> makeGreeting = "Hello, "::concat;
	/** the function below is equivalent to 
	 * UnaryOperator<String> makeGreeting2 = ( x , y ) -> x.concat(y)
	 */
	Runnable runMe = () -> System.out.println("running");
	Callable<Long> callMe = System::currentTimeMillis;
	ThreadFactory t = Thread::new;

	IntFunction<String> lookup = Arrays.asList("a","b","c")::get;
	IntSupplier randomInt = new Random()::nextInt;
	Path base = Paths.get(".");
	Function<String,Path> resolvePath = base::resolve;
	ExceptionListener listener = Exception::printStackTrace;
	
	public IntUnaryOperator compareAgainst(Integer compareLeft) {
		return compareLeft::compareTo;
	}
	/**
	 * 
	 */
	public LambdasAndInstanceMethods() {
		dotest2();
	}

	private void dotest2() {
		IntStream.generate(randomInt).limit(10).forEach(System.out::println);
		
		Arrays.asList("a" , "b").stream().map(resolvePath).forEach(System.out::println);
	    
        runMe.run();
        try {
			System.out.println("callMe.call() = " + callMe.call());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        t.newThread(new Runnable  () {
        	public void run(){System.out.println("I am running");}
        }).start();
	}

	private void dotest() {
		List<String> list = Arrays.asList("a", "b");
		list.forEach(p -> {
			String t = makeGreeting.apply(p);
			System.out.println(t);
		});
		
		IntStream.of(0,1,2).forEach(p -> {
			String t = lookup.apply(p);
			Utility.p(t);
		});
		
		IntStream
		.of(0,1,2).forEach(p -> System.out.println(lookup.apply(p)));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LambdasAndInstanceMethods();

	}

}
