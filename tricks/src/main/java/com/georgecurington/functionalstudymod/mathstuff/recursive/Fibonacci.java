/**
 * 
 */
package com.georgecurington.functionalstudymod.mathstuff.recursive;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.IntStream;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class Fibonacci {
	/** used in example showing an optimized cached version of the function **/
	private static final ConcurrentMap<Integer, Long> fibCache = new ConcurrentHashMap<>();
	private static int SOMENUM = 8;

	/**
	 * This example illustrates a couple of methods that can be used to generate
	 * the fibonacci sequence. F_n = F(n-1) + F(n-2) with seed values
	 * 
	 * F(0) = 0,F(1) = 1.
	 */
	public Fibonacci() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Recursive version
	 * 
	 * @param n
	 *            generate fib for input integer
	 */
	public static long fibonacci(int n) {
		if (n <= 1)
			return n;
		else
			return fibonacci(n - 1) + fibonacci(n - 2);
	}

	/**
	 * iterative version
	 * 
	 * @param n
	 *            generate fibonacci for input integer
	 */
	public static int fibonacciInt(int n) {
		int a = 0, b = 1;

		for (int i = 0; i < n; i++) {
			a = a + b;
			b = a - b;
		}
		return a;
	}

	/**
	 * Cached Recursive Version of fibonacci ...
	 * Note that this is not the most optimal version of a cached
	 * implementation because multiple identical function calls are 
	 * possible in a heavily concurrent environment. 
	 * @param n
	 * @return
	 */
	public long fibonacciCache(int n) {
		if (n <= 1)
			return n;
		else if (fibCache.get(n) != null) {
//			System.out.println("found " + n + " in cache");
			return fibCache.get(n);
		}
		return fibonacciCache(n - 1) + fibonacciCache(n - 2);

	}

	public void testFibonacciCache(int numberOfFibs) {
		long startTime = System.currentTimeMillis();
		IntStream.rangeClosed(0, numberOfFibs).forEach(p -> {
			Long fib = fibCache.computeIfAbsent(p, this::fibonacciCache);
//			System.out.println("fid of " + p + " is " + fib);
			System.out.println(String.format("fib of %d is %d", p,fib));

		});
		long endTime = System.currentTimeMillis();
//		System.out.println("elapsed:" + (endTime - startTime));
	}

	public static void main(String[] args) {
//		nonCachedVersions();
		int numberOfFibs=100;
		cachedVersion(numberOfFibs);
		
	}

	private static void cachedVersion(int numberOfFibs) {
		Fibonacci fibCall = new Fibonacci();
		fibCall.testFibonacciCache(numberOfFibs);
	}

	/**
	 * 
	 */
	private static void nonCachedVersions() {
		int n = SOMENUM;
		System.out.println("=====Recursive=====");
		for (int i = 1; i <= n; i++)
			System.out.println(i + ": " + fibonacci(i));
		System.out.println("=====Iterative=====");
		for (int i = 1; i <= n; i++)
			System.out.println(i + ": " + fibonacciInt(i));
	}
}
