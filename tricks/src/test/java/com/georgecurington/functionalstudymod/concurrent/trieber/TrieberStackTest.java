/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.trieber;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author george
 *
 */
public class TrieberStackTest {

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
	 * Test method for {@link com.georgecurington.functionalstudymod.concurrent.trieber.TrieberStack#push(java.lang.Object)}.
	 */
	@Test
	public void testPush() {
		TrieberStack<String> stack = new TrieberStack<>();
		assertNotNull("stack not created", stack);
		stack.push("hello");
		stack.push("folks");
		stack.push("goodbye");
		assertEquals("top of stack should be:", "goodbye", stack.pop());
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.concurrent.trieber.TrieberStack#pop()}.
	 */
	@Test
	public void testPop() {
		fail("Not yet implemented");
	}

}
