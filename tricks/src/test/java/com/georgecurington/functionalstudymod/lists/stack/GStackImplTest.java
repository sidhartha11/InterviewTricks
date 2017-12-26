/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.stack;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author george
 *
 */
public class GStackImplTest {
	private static final Logger logger=Logger.getLogger(GStackImplTest.class);
	GStack<String> stack;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		stack = new GStackImpl<>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.stack.GStackImpl#GStackImpl()}.
	 */
	@Test
	public void testGStackImpl() {
		assertNotNull("stack not created", stack);
		logger.info(stack);
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.stack.GStackImpl#pop()}.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testPop() {
		boolean b = false;
		b=stack.push("charlie");
		b=stack.push("moe");
		b=stack.push("curly");	
		while ( stack.isEmpty()==false){
			String s = stack.pop();
			logger.info(s);
		}
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.stack.GStackImpl#peek()}.
	 */
	@Test
	public void testPeek() {
		boolean b = stack.push("charlie");
		assertTrue(b);
		assertEquals("size should be 1",1,stack.size());
		assertEquals("charlie",(stack.peek()));
		
		b=stack.push("moe");
		assertTrue(b);
		assertEquals("size should be 2",2,stack.size());
		assertEquals("moe",(stack.peek()));
		
		b=stack.push("curly");	
		assertTrue(b);
		assertEquals("size should be 3",3,stack.size());
		assertEquals("curly",(stack.peek()));
		
		String s=stack.pop();
		assertEquals("size should be 2",2,stack.size());
		assertEquals("incorrect item popped","curly",s);
		assertEquals("incorrect item peeped","moe",(stack.peek()));
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.stack.GStackImpl#push(java.lang.Object)}.
	 */
	@Test
	public void testPush() {
		boolean b = stack.push("charlie");
		assertTrue(b);
		assertEquals("size should be 1",1,stack.size());
		assertEquals("charlie",(stack.peek()));
		
		b=stack.push("moe");
		assertTrue(b);
		assertEquals("size should be 2",2,stack.size());
		assertEquals("moe",(stack.peek()));
		
		b=stack.push("curly");	
		assertTrue(b);
		assertEquals("size should be 3",3,stack.size());
		assertEquals("curly",(stack.peek()));
	}

}
