/**
 * 
 */
package com.georgecurington.functionalstudymod.mathstuff.recursive;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author george
 *
 */
public class FibonacciSeriesTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.mathstuff.recursive.FibonacciSeries#FibonacciSeries()}.
	 */
	@Test
	public void testFibonacciSeries() {
		FibonacciSeries fib = new FibonacciSeries();
		assertNotNull("cannot construct", fib);
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.mathstuff.recursive.FibonacciSeries#fibIterative(int)}.
	 */
	@Ignore
	public void testFibIterative() {
		FibonacciSeries fib = new FibonacciSeries();
		IntStream.rangeClosed(0,10).forEach(p -> {
			long n = fib.fibIterative(p);
			System.out.println(String.format("fib of %d is %d", p,n));
		});
	}
	
	@Ignore
	public void testFibRecursive() {
		FibonacciSeries fib = new FibonacciSeries();
		IntStream.rangeClosed(0,100).forEach(p -> {
			long n = fib.fibRecursive(p);
			System.out.println(String.format("fib of %d is %d", p,n));
		});
	}
	
	@Ignore
	public void testFibRecursiveCache() {
		FibonacciSeries fib = new FibonacciSeries();
		IntStream.rangeClosed(0,100).forEach(p -> {
			long n = fib.fibRecursiveCache(p);
			System.out.println(String.format("fib of %d is %d", p,n));
		});
	}
	
	@Ignore
	public void testFibRecursiveBigInteger() {
		FibonacciSeries fib = new FibonacciSeries();
		IntStream.rangeClosed(0,100).forEach(p -> {
			BigInteger n = BigInteger.valueOf(p);
			n = fib.fibRecursiveBig(n);
			System.out.println(String.format("fib of %d is %d", p,n));
		});
	}
	
	@Test
	public void testFibRecursiveBigIntegerCache() {
		long start = System.currentTimeMillis();
		FibonacciSeries fib = new FibonacciSeries();
		IntStream.rangeClosed(0,100).forEach(p -> {
			BigInteger n = BigInteger.valueOf(p);
			n = fib.fibRecursiveBigCache(n);
//			System.out.println(String.format("fib of %d is %d", p,n));
		});
		long end = System.currentTimeMillis();
		System.out.println("elapsed:" + (end - start) + "," + ((end - start)/1000));
	}

}
