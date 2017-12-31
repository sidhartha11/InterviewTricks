/**
 * 
 */
package com.georgecurington.functionalstudymod.trees.unbalanced;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 * <pre>
 * In this implementation, there is a requirement
 * to supply both key and data as individual objects
 * to the underlying implementation.
 * </pre>
 *
 */
public interface Tree<K,V> {

	public V get(K key);
	public boolean insert(K key,V data);
	public V delete(K key);
	public int size();
	public void inorder();
	public void preorder();
	public void postorder();
	public void depthFirst();
	public void breadthFirst();
	public String getName();
}