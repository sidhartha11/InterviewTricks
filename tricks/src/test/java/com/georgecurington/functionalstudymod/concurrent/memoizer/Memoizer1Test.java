/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.memoizer;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author george
 *
 */
public class Memoizer1Test {

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
	 * Test method for {@link com.georgecurington.functionalstudymod.concurrent.memoizer.Memoizer1#Memoizer1(com.georgecurington.functionalstudymod.concurrent.memoizer.Computable)}.
	 */
	@Test
	public void testMemoizer1() {
		/** create a test expensive **/
		ExpensiveFunction compute = new ExpensiveFunction();
		Memoizer1<String,BigInteger> memoizer1 = new Memoizer1<>(compute);
		/****/
		
			BigInteger bi = memoizer1.compute("16");
			System.out.println(bi);
	
	}
	
	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.concurrent.memoizer.Memoizer1#Memoizer1(com.georgecurington.functionalstudymod.concurrent.memoizer.Computable)}.
	 */
	@Test
	public void testMemoizer2() {
		/** create a test expensive **/
		ExpensiveFunction compute = new ExpensiveFunction();
		Memoizer2<String,BigInteger> memoizer2 = new Memoizer2<>(compute);
		/****/
		
			BigInteger bi = memoizer2.compute("6");
			System.out.println(bi);
	
	}


	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.concurrent.memoizer.Memoizer1#compute(java.lang.Object)}.
	 */
	@Ignore
	public void testCompute() {
		fail("Not yet implemented");
	}

}
