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
		
	    Set<List<Integer>> set=gadd.getSolutionPairSetFromSortedArray
	    (input,5);
	    
	    assertEquals("[[-1, 6]]", set.toString());
	}

}
