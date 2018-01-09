/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.memoizer;

import java.math.BigInteger;

import com.georgecurington.functionalstudymod.mathstuff.recursive.Factorial;

/**
 * <pre>
 * Taken from the book "Java Concurrency in Practice".
 * Here I will try to implement the Memoizer example , document it 
 * and test it.
 * ==========================================
 * The Computable<A, V> interface in Listing 5.16 describes a function with 
 * input of type A and result of type V. ExpensiveFunction, 
 * which implements Computable, takes a long time to compute its result; 
 * we'd like to create a Computable wrapper that remembers the 
 * results of previous computations and encapsulates the caching process. 
 * (This technique is known as memoization.)
 * 
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {

	/**
	 * 
	 */
	public ExpensiveFunction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public BigInteger compute(String arg){
		long number = Long.parseLong(arg);
		System.out.println("converted to long:" + number);
		BigInteger big = new BigInteger(arg);
//		String result = String.valueOf(Factorial.iterativeFactorial(number));
		long n = System.currentTimeMillis();
		String result = null;
		try {
//		result = String.valueOf(Factorial.recursiveFactorial(number));
		System.out.println("converted to BigInteger=" + big);
//		result = String.valueOf(Factorial.recursiveFactorialBig(big));
		System.out.println("calculating InteractiveFactorialBig");
		big = Factorial.iterativeFactorialBig(number);
		result = String.valueOf(big);
		System.out.println("BigInteger       result=" + big);
		System.out.println("String converted result=" + result);
		
		} catch ( Throwable t) {
			System.out.println("exception:" + t);
		}
//		long e = System.nanoTime();
		long e = System.currentTimeMillis();

		System.out.println("elapsed:" + (e - n));

//		return new BigInteger(result);
		return big;
	}

}
