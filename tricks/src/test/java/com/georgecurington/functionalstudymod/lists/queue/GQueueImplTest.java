/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.queue;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author george
 *
 */
public class GQueueImplTest {
	private static final Logger logger=Logger.getLogger(GQueueImplTest.class);
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

	/** PRELIMINARY TESTS **/
	
	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.queue.GQueueImpl#size()}.
	 */
	@Test
	public void testSize() {
		GQueue<String> queue = new GQueueImpl<>();
		assertNotNull("cannot initialize queue",queue);
		int i = queue.size();
		assertEquals("queue should be zero",0,i);
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.queue.GQueueImpl#GQueueImpl()}.
	 */
	@Test
	public void testGQueueImpl() {
		GQueue<String> queue = new GQueueImpl<>();
		assertNotNull("cannot initialize queue",queue);
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.queue.GQueueImpl#poll()}.
	 */
	@Test
	public void testPoll() {
		GQueue<String> queue = new GQueueImpl<>();
		assertNotNull("cannot initialize queue",queue);
		String b = queue.poll();
		assertNull("empty queue should return null", b);
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.queue.GQueueImpl#peek()}.
	 */
	@Test
	public void testPeek() {
		GQueue<String> queue = new GQueueImpl<>();
		assertNotNull("cannot initialize queue",queue);
		String b = queue.peek();
		assertNull("empty queue peek should return null", b);
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.queue.GQueueImpl#offer(java.lang.Object)}.
	 */
	@Test
	public void testOffer() {
		GQueue<String> queue = new GQueueImpl<>();
		assertNotNull("cannot initialize queue",queue);
		boolean b = queue.offer("ballons");
		assertTrue("offer to empty queue=true",b);
	}

	/** COMPOUND TESTING OF MULIPLE STATES **/

	@Test
	public void testMultipleOffers(){
		GQueue<String> queue = new GQueueImpl<>();
		assertNotNull("cannot initialize queue",queue);
		Arrays.asList(
				"testing",
				"multiple",
				"offers").forEach(queue::offer);
		
		assertEquals("size should be 3",3,queue.size());
		logger.info(queue);
	}
	
	@Test
	public void testMultipleOffersAndPolls(){
		GQueue<String> queue = new GQueueImpl<>();
		assertNotNull("cannot initialize queue",queue);
		Arrays.asList(
				"testing",
				"multiple",
				"offers").forEach(queue::offer);
		
		assertEquals("size should be 3",3,queue.size());
		logger.info(queue);
		
		String s = queue.peek();
		assertEquals("Incorrect peek item", "testing", s);
		StringBuilder sb = new StringBuilder();
		while ( queue.isEmpty() == false ){
			s = queue.poll();
			if ( sb.length() > 0){
				sb.append(", ");
			}
			sb.append(s);
		}
		assertEquals
		(
				"incorrect polling"
				,"testing, multiple, offers"
				,sb.toString()
		);
		
		assertEquals("size should be 0", 0, queue.size());
	}
}
