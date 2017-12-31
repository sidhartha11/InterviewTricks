/**
 * 
 */
package com.georgecurington.functionalstudymod.trees.unbalanced;

import com.georgecurington.functionalstudymod.design.visitorpattern.Visitable;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public interface Node<K,V> extends Visitable<K,V> {

	public K getKey();
	public V getData();
	public boolean setParent(Node<K,V> node);
	public boolean setLeft(Node<K,V> node);
	public boolean setRight(Node<K,V> node);
	public Node<K,V> getLeft();
	public Node<K,V> getRight();
	public Node<K,V> getParent();
	public int getCount();
	public void updateCount();
	
	
}
