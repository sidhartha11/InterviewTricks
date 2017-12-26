/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.linkedlist;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author george
 *
 */
public class GLinkedListImplTest {
	private static Logger logger=Logger.getLogger(GLinkedListImplTest.class);
	private GLinkedList<String> listProcessedAtHead=null;
	@SuppressWarnings("unused")
	private GLinkedList<String> listProcessedAtTail=null;
	private String[] testdata = {
			"apple","banana","orange","carrot","grape"
	};
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		logger.info("running setup for individual test");
		
		listProcessedAtHead = new GLinkedListImpl<>();
		Arrays.stream(testdata).forEach( p -> {
			listProcessedAtHead.insertHead(p);
		});

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.linkedlistProcessedAtHead.GLinkedListImpl#GLinkedListImpl()}.
	 */
	@Ignore
	public void testGLinkedListImpl() {
		assertNotNull("list not created", listProcessedAtHead);
	}


	/**
	 * LILO ( or stack ) processing is achieved by using the insertHead method on the underlying
	 * GLinkedList Implementation. 
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.linkedlist.GLinkedListImpl#insertHead(java.lang.Object)}.
	 */
	@Ignore
	public void testgetNext() {
	
		/** traverse list **/
		logger.info(listProcessedAtHead);
		logger.info("=========== dumping list ===========");

		while ( listProcessedAtHead.isEmpty() == false ){
			String data = listProcessedAtHead.getHead();
			listProcessedAtHead.getNext();
			logger.info(data);
		}
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.linkedlist.GLinkedListImpl#insertNext(java.lang.Object)}.
	 */
	@Ignore
	public void testInsertNext() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.linkedlist.GLinkedListImpl#removeNext()}.
	 */
	@Ignore
	public void testRemoveNext() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.linkedlist.GLinkedListImpl#isEmpty()}.
	 */
	@Ignore
	public void testIsEmpty() {
		GLinkedList<String> list = new GLinkedListImpl<>();
		assertNotNull("list not created", list);
		assertTrue("list should be empty", list.isEmpty());
	}
	
	/**
	 * Negative Test method for {@link com.georgecurington.functionalstudymod.lists.linkedlist.GLinkedListImpl#isEmpty()}.
	 */
	@Ignore
	public void testNotEmpty() {
		GLinkedList<String> list = new GLinkedListImpl<>();
		assertNotNull("list not created", list);
		list.insertHead("test");
		assertTrue("list should not be empty", list.isEmpty() == false );
		String head = list.getHead();
		assertTrue("head not correct" ,head.equals("test") );
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.linkedlist.GLinkedListImpl#insertHead(java.lang.Object)}.
	 */
	@Ignore
	public void testInsertHead() {
		fail("Not yet implemented");
	}

	/**
	 * FIFO processing. 
	 * Using the insertTail method on the underlying GLinkedList you achieve
	 * queue-style processing; First In , First out. 
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.linkedlist.GLinkedListImpl#insertTail(java.lang.Object)}.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testInsertTail() {
		GLinkedList<String> list=new GLinkedListImpl<>();
		boolean b=list.insertTail("apple");
		assertTrue("size must be 1", list.size()==1);
		
		assertEquals("tail not set","apple", list.getTail());
		assertEquals("head not set","apple", list.getHead());
		b=list.insertTail("banana");
		assertEquals("tail not set","banana", list.getTail());
		assertEquals("head not set","apple", list.getHead());
		
		b=list.insertTail("orange");
		assertEquals("tail not set","orange", list.getTail());
		assertEquals("head not set","apple", list.getHead());
		
		/** traverse list **/
		logger.info(listProcessedAtHead);
		logger.info("=========== dumping list ===========");

		while ( list.isEmpty() == false ){
			String data = list.getHead();
			list.getNext();
			logger.info(data);
		}

	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.linkedlist.GLinkedListImpl#size()}.
	 */
	@Ignore
	public void testSize() {
		assertEquals("list size should be 4", listProcessedAtHead.size(),4);
	}

}
