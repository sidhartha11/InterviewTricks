/**
 * 
 */
package com.georgecurington.functionalstudymod.mathstuff.recursive;

/**
 * <pre>
 * n! = n × (n−1)! and 0! = 1
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 * @see https://en.wikipedia.org/wiki/Factorial
 *
 */
public class Factorial {

	/**
	 * <pre>
	 * factorial of 6 is:
	 * 0 * 1 * 2 * 3 * 4 * 5 * 6
	 * 0 * 1 * 2 * 3 * 4 * ( 30 )
	 * 0 * 1 * 2 * 3 * ( 120 )
	 * 0 * 1 * 2 * ( 360 )
	 * 0 * 1 * ( 720 )
	 * </pre>
	 * 
	 * 
	 */
	public Factorial() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long i=0;
		
		i = iterativeFactorial(20l);
		System.out.println("iterative " + i);
		i = recursiveFactorial(20l);
		System.out.println("recursive " + i);
		
		i = iterativeFactorial(6);
		System.out.println("iterative " + i);
		i = recursiveFactorial(6);
		System.out.println("recursive " + i);

	}

	public static long iterativeFactorial(long f) {
		long fact=1;
		for (int i=1; i <= f; i++) {
			fact=fact*i;
		}
		return fact;
	}
	
	public static long recursiveFactorial(long f) {
		if ( f <= 1l ){
			return 1l;
		} else {
			return recursiveFactorial(f - 1) * f;
		}
	}

}
