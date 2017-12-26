/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.node;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author george
 *
 */
public class NodeImplTest {

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
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.node.NodeImpl#NodeImpl(java.lang.Object)}.
	 */
	@Test
	public void testNodeImpl() {
		Node<String> node = new NodeImpl<>("george");
		assertNotNull("node not created", node);
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.node.NodeImpl#getData()}.
	 */
	@Test
	public void testGetData() {
		Node<String> node = new NodeImpl<>("george");
		assertNotNull("node not created", node);
		assertEquals("failed to getData","george",node.getData());
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.node.NodeImpl#getNext()}.
	 */
	@Test
	public void testGetNext() {
		Node<String> node = new NodeImpl<>("george");
		assertNotNull("node not created", node);
		assertNull("next should be null", node.getNext());
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.node.NodeImpl#insertNext(com.georgecurington.functionalstudymod.lists.node.Node)}.
	 */
	@Test
	public void testInsertNext() {
		Node<String> node = new NodeImpl<>("george");
		assertNotNull("node not created", node);
		Node<String> node2 = new NodeImpl<>("mary");
		assertNotNull("node not created", node2);
		node.insertNext(node2);
		assertEquals("node not inserted", node2, node.getNext());
	}

}
