/**
 * 
 */
package com.georgecurington.functionalstudymod.numerical;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author george
 *
 */
public class GAddTwoNumberSummingToNTest {

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
	 * Test method for {@link com.georgecurington.functionalstudymod.numerical.GAddTwoNumberSummingToN#getSolutionPairSetFromSortedArray(java.lang.Integer[], int)}.
	 */
	@Test
	public void testGetSolutionPairSetFromSortedArray() {
		GAddTwoNumberSummingToN gadd =
				new GAddTwoNumberSummingToN();
		assertNotNull("Cannot Instantiate", gadd);
		
		Integer[] input = {
				2,4,-1,6 
		};
		Integer[] input2 = {3,6, 3,0, 4, 2,2,2,2, -8, 4, 10, 1, 1, 88, 1, 98,5, -10, 4, 4, -8, 30, -6,12,11,11 };

	    Set<List<Integer>> set=gadd.getSolutionPairSetFromSortedArray
	    (input2,22);
	    System.out.println("input size:" + input2.length);
	    System.out.println(set.toString());
	    assertEquals("[[-1, 6]]", set.toString());
	}

}
