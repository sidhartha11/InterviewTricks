/**
 * 
 */
package com.georgecurington.functionalstudymod.trees.unbalanced;

import com.georgecurington.functionalstudymod.design.visitorpattern.Visitor;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class NodeImpl<K,V> implements Node<K, V> {

	private final K key;
	private final V data;
	private Node<K,V> parent;
	protected Node<K,V> left;
	protected Node<K,V> right;
	private int count;
	private Visitor<K, V> visitor;
	/**
	 * 
	 */
	public NodeImpl(K key, V data) {
		this(key,data,null);
	}
	
	public NodeImpl(K key, V data, Node<K,V> parent) {
		this.key = key;
		this.data = data;
		this.parent = parent;
	}

	@Override
	public V getData() {
		// TODO Auto-generated method stub
		return this.data;
	}

	@Override
	public boolean setParent(Node<K, V> node) {
		this.parent = node;
		return true;
	}

	@Override
	public boolean setLeft(Node<K, V> node) {
		this.left = node;
		return true;
	}

	@Override
	public boolean setRight(Node<K, V> node) {
		this.right = node;
		return true;
	}

	@Override
	public Node<K, V> getLeft() {
		// TODO Auto-generated method stub
		return this.left;
	}

	@Override
	public Node<K, V> getRight() {
		// TODO Auto-generated method stub
		return this.right;
	}

	@Override
	public Node<K, V> getParent() {
		// TODO Auto-generated method stub
		return this.parent;
	}

	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NodeImpl [key=" + key + ", data=" + data + ", parent=" + parent + ", left=" + left + ", right=" + right
				+ ", count=" + count + "]";
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public void updateCount() {
		count++;
		
	}

	@Override
	public void accept(Visitor<K, V> visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

}
