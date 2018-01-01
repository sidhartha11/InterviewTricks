/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.node;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class NodeImpl<E> implements Node<E> {

	private final E data;
	private Node<E> next;
	/**
	 * 
	 */
	public NodeImpl(E data) {
		this.data = data;
	}
	@Override
	public E getData() {
		// TODO Auto-generated method stub
		return this.data;
	}

	@Override
	public Node<E> getNext() {
		// TODO Auto-generated method stub
		return this.next;
	}
	@Override
	public boolean insertNext(Node<E> next) {
		// TODO Auto-generated method stub
		
		this.next = next ;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NodeImpl [data=" + data + ", next=" + next + "]";
	}

}
