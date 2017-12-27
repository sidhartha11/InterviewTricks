/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.linkedlist;

import com.georgecurington.functionalstudymod.lists.node.Node;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 * <p>
 * API for a flexible linked list<br>
 * This is a minimal implementation of a linked list.<BR>The use here is 
 * form the basis of an ADT that can be used to implement<BR>a Queue and 
 * a Stack by simply calling methods in the<BR>link list implementation.<BR>
 * It should be possible to do the same with java.util.List implementation. 
 * </p>
 * <pre>
 * getNext
 * get the next node in the current list,starting at head.
 * adjust pointers.
 * insertHead
 * insert the node at the head of the list 
 * insertTail
 * insert the node at the tail of the list
 * size
 * return the size of the list
 * getHead
 * get the data element in the head of the list 
 * getTail
 * get the data element in the tail of the list 
 * removeHead ( TBD )
 * remove the head of the list 
 * removeTail ( TBD )
 * remove the tail of the list 
 * 
 *
 */
public interface GLinkedList<N> {
	Node<N> getNext();
	boolean insertNext(N data);
	N removeHead();
	N removeTail();
	boolean isEmpty();
	boolean insertHead(N data);
	boolean insertTail(N data);
	int size();
	N getHead();
	N getTail();

}
