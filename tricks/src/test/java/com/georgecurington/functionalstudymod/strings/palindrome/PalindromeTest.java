/**
 * 
 */
package com.georgecurington.functionalstudymod.strings.palindrome;

import static org.junit.Assert.*;

import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.georgecurington.functionalstudymod.testdata.TestData;

/**
 * @author george
 *
 */
public class PalindromeTest {

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
	 * Test method for {@link com.georgecurington.functionalstudymod.strings.palindrome.Palindrome#isPalindrome(java.lang.String)}.
	 */
	@Test 
	public void testIsPalindrome() {	
		Palindrome pal=new Palindrome();
		assertTrue("Is not palindrome",pal.isPalindrome("abba1abba"));
		System.out.println("abba1abba passed");
		assertTrue("Is not palindrome",pal.isPalindrome("abba"));
		System.out.println("abba passed");
		assertTrue("Is not palindrome",!pal.isPalindrome("abba12abba"));
		System.out.println("abba12abba negative passed");
		IntStream.range(0,Integer.MAX_VALUE).forEach(p -> {
//			String s = TestData.getSaltString(18,6);
			String s = TestData.getSaltString(8,14,-1);
//			System.out.println("trying " + s);
			boolean b=!pal.isPalindrome(s);
		});
	}
	
	@Ignore 
	public void testRemoveFirst() {	
		Palindrome pal=new Palindrome();
		IntStream.range(0,TestData.palindromes.length).forEach(p -> {
//			String s = TestData.getSaltString(18,6);
			String s = TestData.getSaltString(2,-1);
	//		System.out.println("trying " + s);
			StringBuilder snew = new StringBuilder(TestData.palindromes[p]);
			snew.replace(0, 2, s);	
			String snew2=String.format("%2d. %s", p,snew.toString());
			System.out.println(snew2);
			
		});
	}

}
