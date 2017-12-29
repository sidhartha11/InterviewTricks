/**
 * 
 */
package com.georgecurington.functionalstudymod.misc.listprocessing;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.georgecurington.functionalstudymod.testdata.TestData;

/**
 * @author george
 *
 */
public class UniqueRandomElementTest {

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
	 * Test method for {@link com.georgecurington.functionalstudymod.misc.listprocessing.UniqueRandomElement#UniqueRandomElement(java.lang.String[])}.
	 */
	@Test
	public void testUniqueRandomElement() {
		UniqueRandomElement ure = new UniqueRandomElement(TestData.namesUniverse);
		assertNotNull("could not create namesUniverse", ure);
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.misc.listprocessing.UniqueRandomElement#getRandomNames()}.
	 */
	@Ignore
	public void testGetRandomNames() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.misc.listprocessing.UniqueRandomElement#getRandomNames(int)}.
	 */
	@Test
	public void testGetRandomNamesInt() {
		/** return a list of random names of size n **/
		/** first create the universe of names to test with **/
		UniqueRandomElement ure = new UniqueRandomElement(TestData.namesUniverse);
		assertNotNull("could not create namesUniverse", ure);
		/** now get a set of unique non repeating names **/
		Set<String> s = ure.getRandomNames(100);
		assertNotNull("could not create set of unique names, check input size", s);
		s.forEach(System.out::println);
	}

}
