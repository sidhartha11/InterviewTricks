/**
 * 
 */
package com.georgecurington.functionalstudymod.strings.anagram;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class AnagramImplTest {

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
	 * Test method for {@link com.georgecurington.functionalstudymod.strings.anagram.AnagramImpl#AnagramImpl(char)}.
	 */
	@Test
	public void testAnagramImpl() {
		String teststring1 = "AppleAppleAppleBob";
		String teststring2 = "elppAelppAelppABobXrX";
		AnagramHashIntf anagram1 = new AnagramHash(teststring1);
		AnagramHashIntf anagram2 = new AnagramHash(teststring2);
		assertTrue ("this should be an anagram", anagram1.isAnagram(anagram2));
		
		teststring1 = "The grey fox jumped over the moon";
		teststring2 = "over the moon jumped fox The grey";
		anagram1 = new AnagramHash(teststring1);
		anagram2 = new AnagramHash(teststring2);
		assertTrue ("this should be an anagram", anagram1.isAnagram(anagram2));
	}

}
