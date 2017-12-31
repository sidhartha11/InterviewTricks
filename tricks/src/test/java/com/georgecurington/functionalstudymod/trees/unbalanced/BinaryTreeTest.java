/**
 * 
 */
package com.georgecurington.functionalstudymod.trees.unbalanced;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class BinaryTreeTest {
	private Tree<Integer,Integer> tree;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tree = new BinaryTree<>("george");
		assertNotNull("tree is null", tree);
		assertEquals("name incorrect","george", tree.getName());
		assertEquals("size should be 0", 0, tree.size());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.trees.unbalanced.BinaryTree#BinaryTree(java.lang.String)}.
	 */
	@Test
	public void testBinaryTree() {
		Tree<Integer,Integer> tree = new BinaryTree<>("george");
		assertNotNull("tree is null", tree);
		assertEquals("name incorrect","george", tree.getName());
		assertEquals("size should be 0", 0, tree.size());
	}
	
	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.trees.unbalanced.BinaryTree#insert(java.lang.Comparable, java.lang.Object)}.
	 */
	@Ignore
	public void testInsert() {
		tree.insert(1, 10);
		assertEquals("size should be 1",1,tree.size());
		tree.insert(10, 10);
		assertEquals("size should be 2",2,tree.size());
		tree.insert(20, 34);
		assertEquals("size should be 3",3,tree.size());
		tree.insert(20, 34);
		assertEquals("size should be 3",3,tree.size());
		System.out.println(tree);
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.trees.unbalanced.BinaryTree#get(java.lang.Comparable)}.
	 */
	@Test
	public void testGet() {
		tree.insert(1, 10);
		assertEquals("size should be 1",1,tree.size());
		tree.insert(10, 10);
		assertEquals("size should be 2",2,tree.size());
		tree.insert(20, 34);
		assertEquals("size should be 3",3,tree.size());
		tree.insert(20, 34);
		assertEquals("size should be 3",3,tree.size());
		System.out.println(tree);
		System.out.println("testing get");
		Integer element = tree.get(20);
		assertEquals("element should be 20", 34, (int)element);
		
		element = tree.get(1);
		assertEquals("element should be 10", 10, (int)element);
		
		element = tree.get(-8);
		assertNull("Should return null", element);
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.trees.unbalanced.BinaryTree#delete(java.lang.Comparable)}.
	 */
	@Ignore
	public void testDelete() {
		tree.insert(1, 10);
		assertEquals("size should be 1",1,tree.size());
		tree.insert(10, 10);
		assertEquals("size should be 2",2,tree.size());
		tree.insert(20, 34);
		assertEquals("size should be 3",3,tree.size());
		tree.insert(20, 34);
		assertEquals("size should be 3",3,tree.size());
		System.out.println(tree);
		System.out.println("testing get");
		Integer element = tree.get(20);
		assertEquals("element should be 20", 34, (int)element);
		
		element = tree.get(1);
		assertEquals("element should be 10", 10, (int)element);
		
		element = tree.get(-8);
		assertNull("Should return null", element);
		
		Integer t = tree.delete(20);
		assertEquals("returned data should be 34",34,(int)t);
		assertEquals("size should now be 2",2,tree.size());
	}
	
	@Test
	public void testTraversals() {
		tree.insert(1, 10);
		assertEquals("size should be 1",1,tree.size());
		tree.insert(10, 10);
		assertEquals("size should be 2",2,tree.size());
		tree.insert(20, 34);
		assertEquals("size should be 3",3,tree.size());
		tree.insert(20, 34);
		assertEquals("size should be 3",3,tree.size());
		System.out.println(tree);
		System.out.println("testing get");
		Integer element = tree.get(20);
		assertEquals("element should be 20", 34, (int)element);
		
		element = tree.get(1);
		assertEquals("element should be 10", 10, (int)element);
		
		element = tree.get(-8);
		assertNull("Should return null", element);
		
		tree.insert(1, 34);
		tree.insert(100, 34);
		tree.insert(-8, 34);
		tree.insert(0, 34);
		
		System.out.println("============= running inorder ============");
		tree.inorder();
		
		System.out.println("============= running inorder ============");
		tree.inorder();
		
		System.out.println("============= running preorder ============");
		tree.preorder();
		
		System.out.println("============= running preorder ============");
		tree.preorder();
		
		System.out.println("============= running postorder ============");
		tree.postorder();
		
		System.out.println("============= running postorder ============");
		tree.postorder();
		
		
		System.out.println("============= running inorder ============");
		tree.inorder();
		
		System.out.println("============= running breadthFirst ============");
		tree.breadthFirst();
		
		System.out.println("============= running breadthFirst ============");
		tree.breadthFirst();
		
		System.out.println("============= running depthFirst ============");
		tree.depthFirst();
		
		System.out.println("============= running depthFirst ============");
		tree.depthFirst();
		
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.trees.unbalanced.BinaryTree#size()}.
	 */
	@Ignore
	public void testSize() {
		fail("Not yet implemented");
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.trees.unbalanced.BinaryTree#inorder()}.
	 */
	@Ignore
	public void testInorder() {
		fail("Not yet implemented");
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.trees.unbalanced.BinaryTree#preorder()}.
	 */
	@Ignore
	public void testPreorder() {
		fail("Not yet implemented");
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.trees.unbalanced.BinaryTree#postorder()}.
	 */
	@Ignore
	public void testPostorder() {
		fail("Not yet implemented");
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.trees.unbalanced.BinaryTree#depthFirst()}.
	 */
	@Ignore
	public void testDepthFirst() {
		fail("Not yet implemented");
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.trees.unbalanced.BinaryTree#breadthFirst()}.
	 */
	@Ignore
	public void testBreadthFirst() {
		fail("Not yet implemented");
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.trees.unbalanced.BinaryTree#toString()}.
	 */
	@Ignore
	public void testToString() {
		fail("Not yet implemented");
	}

}
