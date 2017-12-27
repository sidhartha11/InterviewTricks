/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.javalist;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class JavaListBasedStackAndQueueTest {

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

	@Test
	public void testStack() {
		GStackUsingJava<Integer> stack = new GStackUsingJava<>(new LinkedList<Integer>());
		assertNotNull("stack not constructed", stack);
		stack.push(2);
		stack.push(3);
		stack.push(1);
		stack.push(10);
		assertEquals("size should be 4",stack.size(),4 );
		assertEquals("top of stack incorrect", (int)stack.peek(),10);
		assertTrue("stack should not be empty", stack.isEmpty()==false);
		assertEquals("10 should pop off stack", (int)stack.pop(), 10);
		while ( stack.isEmpty()==false){
			System.out.println(stack.pop());
		}
		assertTrue("stack should be empty", stack.isEmpty());
	}
	
	@Test
	public void testQueue() {
		GQueueUsingJava<Integer> queue = new GQueueUsingJava<>(new LinkedList<Integer>());
		assertNotNull("queue not constructed", queue);
		assertEquals("size should be zero",queue.size(),0);
		queue.offer(2);
		queue.offer(3);
		queue.offer(1);
		queue.offer(10);
		assertEquals("size should be 4",queue.size(),4 );
		assertEquals("head of queue should be 2", (int)queue.peek(),2);
		assertTrue("queue should not be empty", queue.isEmpty()==false);
		assertEquals("2 should retrieved from queue", (int)queue.poll(), 2);
		while ( queue.isEmpty()==false){
			System.out.println(queue.poll());
		}
		assertTrue("queue should be empty", queue.isEmpty());
	}


}
