/**
 * 
 */
package com.georgecurington.functionalstudymod.strings.reversals;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author george
 *
 */
public class ReverseStringTest {

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
	 * Test method for {@link com.georgecurington.functionalstudymod.listprocessing.reversals.ReverseString#reverse(java.lang.String)}.
	 */
	@Test
	public void testReverse() {
		ReverseString rev = new ReverseString();
		String s = rev.reverse("Apple");
		assertEquals("failed", "elppA", s);
		
		StringBuilder sb=new StringBuilder("ReverseString rev = new ReverseString()");
		s = rev.reverse("ReverseString rev = new ReverseString()");
		assertEquals("failed", sb.reverse().toString(), s);
		System.out.println("reversed=\n" + s);
		
		s = rev.reverse("Geor");
		assertEquals("failed", "roeG", s);
	}
	
	@Test
	public void testReverseArr() {
		ReverseString rev = new ReverseString();
		String s = rev.reverseArr("Apple");
		assertEquals("failed", "elppA", s);
		
		StringBuilder sb=new StringBuilder("ReverseString rev = new ReverseString()");
		s = rev.reverseArr("ReverseString rev = new ReverseString()");
		assertEquals("failed", sb.reverse().toString(), s);
		System.out.println("reversed=\n" + s);
		
		s = rev.reverseArr("Geor");
		assertEquals("failed", "roeG", s);
	}
	
	@Test
	public void testReverseRecur() {
		ReverseString rev = new ReverseString();
		String s = rev.reverseRecur("Apple");
		assertEquals("failed", "elppA", s);
		
		StringBuilder sb=new StringBuilder("ReverseString rev = new ReverseString()");
		s = rev.reverseRecur("ReverseString rev = new ReverseString()");
		assertEquals("failed", sb.reverse().toString(), s);
		System.out.println("reversed=\n" + s);
		
		s = rev.reverseRecur("Geor");
		assertEquals("failed", "roeG", s);
	}

}
