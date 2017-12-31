/**
 * 
 */
package com.georgecurington.functionalstudymod.trees.unbalanced;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.georgecurington.functionalstudymod.design.visitorpattern.Visitor;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class BinaryTree<K extends Comparable<? super K>,V> implements Tree<K, V> {

	private static final boolean DEBUG = false;
	private int size;
	private Node<K,V> root;
	private final String name;
	private final Visitor<K,V> visitor = new NodeVisitor<>();
	/**
	 * 
	 */
	public BinaryTree(String name) {
		this.name = name;
	}
	
	/**
	 * This method calls a recursive findNode method
	 * @param K key the key being looked up
	 * @return V the data component of the node
	 */
	@Override
	public V get(K key) {
		Node<K,V> returnNode = new NodeImpl<K,V>(null,null);
		Node<K,V> node = findNode(root,key,returnNode);
		return returnNode.getParent() == null ? null : returnNode.getParent().getData();
	}
	@Override
	public boolean insert(K key, V data) {
		
		root = insertNode(root,key,data);
		
		return root == null ? false : true ;
	}
	
	/**
	 * Recursive binary tree insert. This method does not
	 * allow duplicates but instead updates a counter
	 * in the node representing how many times the node
	 * has been visited. 
	 * 
	 * @param root starting point to insert a node
	 * @param key The key component
	 * @param data The data component 
	 * @return returns the root node
	 */
	private Node<K,V> insertNode(Node<K,V> root, K key, V data) {
		if ( root == null ) {
			size++;
			return new NodeImpl<K,V>(key,data);
		} else if ( key.compareTo(root.getKey())==0) {
			root.updateCount();		
			return root;
		} else if ( key.compareTo(root.getKey()) < 0 ) {
			((NodeImpl<K,V>)root).left =  insertNode(root.getLeft(),key,data);
			
		} else {
			((NodeImpl<K,V>)root).right =  insertNode(root.getRight(),key,data);
		}
		
		return root;
	}
	
	/**
	 * This method is recursive in nature and mimics the insert
	 * method.
	 * @param root The root from which to start the search
	 * @param key The key component to look up
	 * @param returnNode 
	 * @return Returns the node being looked up.
	 */
	private Node<K,V> findNode(Node<K,V> root, K key, Node<K, V> returnNode) {
		if ( root == null ) {
			return null;
		} else if ( key.compareTo(root.getKey())==0) {
			returnNode.setParent(root);
			return root;
		} else if ( key.compareTo(root.getKey()) < 0 ) {
			((NodeImpl<K,V>)root).left =  findNode(root.getLeft(),key,returnNode);
			
		} else {
			((NodeImpl<K,V>)root).right =  findNode(root.getRight(),key,returnNode);
		}
		
		return root;
	}

	/**
	 * UNIMPLEMENTED: Deletion is too complicated to 
	 * implement at this time. When I find time to do so 
	 * I will implement this. Feel free to request a pull
	 * request if you have a working algorithm
	 * 
	 * @param K key  the key to delete
	 * @see com.georgecurington.functionalstudymod.trees.unbalanced.Tree#delete(java.lang.Object)
	 */
	@Override
	public V delete(K key) {
		throw new RuntimeException("Delete Is Too Freaking Complicated!");
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}
	@Override
	public void inorder() {
		Node<K,V> temp=root;
		inorder(temp);
		
	}
	
	/* Given a binary tree, print its roots in inorder */
	private void inorder(Node<K,V> root) {
		if (root == null)
			return;

		/* first recur on left child */
		inorder(root.getLeft());

		/* then print the data of root */
		root.accept(visitor);

		/* now recur on right child */
		inorder(root.getRight());
	}
	
	/* Given a binary tree, print its roots in preorder */
	private void preorder(Node<K,V> root) {
		if (root == null)
			return;
		
		/* then print the data of root */
		root.accept(visitor);

		/* then recur on left sutree */
		preorder(root.getLeft());

		/* now recur on right subtree */
		preorder(root.getRight());
	}
	
	@Override
	public void preorder() {
		Node<K,V> temp=root;
		preorder(temp);
		
	}
	@Override
	public void postorder() {
		Node<K,V> temp=root;
		postorder(temp);
		
	}
	
	private void postorder(Node<K,V> root) {
		if (root == null)
			return;

		// first recur on left subtree
		postorder(root.getLeft());

		// then recur on right subtree
		postorder(root.getRight());

		// now deal with the root
		root.accept(visitor);
	}
	
	@Override
	public void depthFirst() {
		Node<K,V> temp=root;
		depthFirst(temp);
		
	}
	@Override
	public void breadthFirst() {
		Node<K,V> temp=root;
		breadthFirst(temp);
		
	}
	
	private void breadthFirst(Node<K,V> root) {
		if ( root == null ) {
			return;
		}
		Queue<Node<K,V>> queue = new ConcurrentLinkedQueue<>();
		queue.offer(root);
		Node<K,V> node=null;
		while (!queue.isEmpty() ){
			node = queue.poll();
			// now deal with the node
			node.accept(visitor);

			if (node.getLeft() != null){
				if (DEBUG)
				System.out.println("getting left node:" + node.getLeft().getData());
				queue.offer(node.getLeft());
			}
			
			if (node.getRight() != null){
				if (DEBUG)
				System.out.println("getting right node:" + node.getRight().getData());
				queue.offer(node.getRight());
			}
		}
		
	}
	
	private void depthFirst(Node<K,V> root) {
		if ( root == null ) {
			return;
		}
		Stack<Node<K,V>> stack = new Stack<>();
		stack.push(root);
		Node<K,V> node=null;
		while (!stack.isEmpty() ){
			node = stack.pop();
			// now deal with the node
			node.accept(visitor);

			if (node.getRight() != null){
				if (DEBUG)
				System.out.println("getting right node:" + node.getRight().getData());
				stack.push(node.getRight());
			}
			
			if (node.getLeft() != null){
				if (DEBUG)
				System.out.println("getting left node:" + node.getLeft().getData());
				stack.push(node.getLeft());
			}
		}
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BinaryTree [size=" + size + ", root=" + root + ", name=" + name + "]";
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
