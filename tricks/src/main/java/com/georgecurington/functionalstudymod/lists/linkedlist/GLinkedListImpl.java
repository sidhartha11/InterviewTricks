/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.linkedlist;

import com.georgecurington.functionalstudymod.lists.node.Node;
import com.georgecurington.functionalstudymod.lists.node.NodeImpl;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class GLinkedListImpl<N> implements GLinkedList<N> {
	private Node<N> head;
	private Node<N> tail;
	protected int size;
	
	/**
	 * 
	 */
	public GLinkedListImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Node<N> getNext() {
		Node<N> temp = head.getNext();
		head = temp;
		return temp;
	}

	@Override
	public boolean isEmpty() {
		boolean b = head == null || tail == null || size == 0 ;
		return b;
	}

	@Override
	public boolean insertNext(N data) {
		try {
		if ( head == null ) {
			head = new NodeImpl<N>(data);
			tail = head;
			size++;
		} else {
			Node<N> node = new NodeImpl<N>(data);
			head.insertNext(node);
			tail = node;
			size++;
		}
		return true;
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean insertHead(N data) {
		try { 
		if ( head == null ) {
			head = new NodeImpl<N>(data);
//			head.insertNext(head);
			tail = head;
			size++;
		} else {
			Node<N> node = new NodeImpl<N>(data);
			node.insertNext(head);
			head = node;
			size++;
		}
		return true;
		} catch ( Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insertTail(N data) {
		try {
		if ( head == null ){
			Node<N> node = new NodeImpl<>(data);
			head = node;
			tail = node;
			size++;
		} else {
			
			Node<N> node = new NodeImpl<>(data);
			tail.insertNext(node);
			tail = node;
			size++;
		}
		return true;
		} catch ( Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public N getHead() {
		// TODO Auto-generated method stub
		return head.getData();
	}

	@Override
	public N getTail() {
		return this.tail.getData();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GLinkedListImpl [head=" + head + ", tail=" + tail +  ", size=" + size + "]";
	}

	@Override
	public N removeHead() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public N removeTail() {
		// TODO Auto-generated method stub
		return null;
	}



}
