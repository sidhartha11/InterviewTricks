/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.linkliststudy;

import java.util.Iterator;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * <pre>
 * Collections of algorithms involving linked lists.
 * Currently just a few simple things.
 * </pre>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 24, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class DLinkListImpl<T extends Comparable<? super T>> implements DLinkList<T> {

	private static final boolean DEBUG = false;
	protected Node<T> head;
	protected Node<T> tail;
	protected int cntr;
	/**
	 * 
	 */
	public DLinkListImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insertAtHead(T data) {
		/** create a new node to go at the head **/
		Node<T> newNode = Node.newNode(data);
		if ( head == null ) {
			head = newNode;
			return true;
		}
		/**
		 * newNode --->next--->head.
		 * head--->previous-->newNode
		 * newNode-->previous-->null 
		 */
		newNode.setNext(head);
		head.setPrevious(newNode);
		head = newNode;
		
		return true;
	}

	@Override
	public boolean insertAtEnd(T data) {
		/** this just follows the list until we come to the last node **/
		if ( head == null ) {
			/** just make it the head in this simple case **/
			head = Node.newNode(data);
			return true;
		}
		/** have to follow the pointers until we get to the last pointer **/
		Node<T> tmp = head;
		while (tmp.getNext() != null ){
			tmp = tmp.getNext();
		}
		/** now insert at end **/
		boolean b = insertAtEnd(tmp,data);
		return b;
	}

	@Override
	public boolean insertAtMiddle(T insertPosition , T data) {
		if ( DEBUG ) {
			Utility.p("inserting " + data);
		}
		if ( head == null ) {
			/** just make it the head **/
			head = Node.newNode(data);
			return true;
		} 
		Node<T> ptr = head;
		Node<T> savepoint = null;
		while ( ptr != null && notInsertPosition(insertPosition, ptr)) {
			/** savepoint is required just in case insertPosition is not present in the list **/
			savepoint = ptr;
			ptr = ptr.getNext();
		}
		/** did we find insertPosition ? **/
		if ( ptr != null ) {
			/** yes we did **/
			Node<T> newNode = Node.newNode(data);
			/** we need to save the address of ptr in a temp var **/
			Node<T> ptrNext = ptr.getNext();
			/** we need to set address of ptr.next to the new node **/
			ptr.setNext(newNode);
			/** we need to point the previous pointer of newNode to ptr(the insertionpoint) **/
			newNode.setPrevious(ptr);
			/** we need to point the next ptr of the newNode to ptrNext(what ptr pointed to prior to insertion **/
			newNode.setNext(ptrNext);
			/** we need to point the ptrNext's previous pointer to newNode **/
			ptrNext.setPrevious(newNode);
		} else {
			insertAtEnd(savepoint,data);
		}
		return true;
	}

	private boolean insertAtEnd(Node<T> ptr, T data) {
		/** ptr represents the last node in the list **/
		/** create a new node **/
		Node<T> newNode = Node.newNode(data);
		ptr.setNext(newNode);
		newNode.setPrevious(ptr);
		return true;	
	}

	private boolean notInsertPosition(T insertPosition, Node<T> ptr) {
		boolean b = insertPosition.compareTo(ptr.getData()) >= 0 ;
		return b;
	}

	@Override
	public Iterator<T> iterator() {
		Iterator<T> it = new Iterator<T>(){
			
			private Node<T>  hptr = head;

			@Override
			public boolean hasNext() {
				//Utility.p("hasNext()");
				return hptr != null ;
			}

			@Override
			public T next() {
				//Utility.p("next()");
				T data = hptr.getData();
				//Utility.p("data=" + data);
				hptr = hptr.getNext();
				return data;
			}
			
		};
		return it;
	}

	@Override
	public boolean insertBeforeMiddle(T insertPosition , T data) {
		if ( DEBUG ) {
			Utility.p("inserting " + data);
		}
		if ( head == null ) {
			/** just make it the head **/
			head = Node.newNode(data);
			return true;
		} 
		Node<T> ptr = head;
		Node<T> savepoint = null;
		while ( ptr != null && !notInsertPosition(insertPosition, ptr)) {
			/** savepoint is required just in case insertPosition is not present in the list **/
			savepoint = ptr;
			ptr = ptr.getNext();
		}
		/** did we find insertPosition ? **/
		if ( ptr != null ) {
			/** yes we did **/
			Node<T> newNode = Node.newNode(data);
			
			/** what is the previous node of insertion pt ? **/
			Node<T> prevInsertion = ptr.getPrevious();
			/** set the newNode next to the insertion point **/
			newNode.setNext(ptr);
			/** set the previous of insertion to newnode **/
			ptr.setPrevious(newNode);
			/** set the previous of newnode to preInsertion **/
			newNode.setPrevious(prevInsertion);
			/** set next of prevInsertion to the newnode **/
			if ( prevInsertion != null ) {
			prevInsertion.setNext(newNode);
			} else {
				head = newNode;
			}
			
		} else {
			insertAtEnd(savepoint,data);
		}
		return true;
	}

	@Override
	public boolean insertInOrder(T data) {
		/** if head is null, set to head **/
		if ( head == null ) {
			head = Node.newNode(data);
			return true;
		}
		/** find the correct place to put the node according to natural ordering **/
		Node<T> ptr = head;
		Node<T> savepoint = null;
		while (ptr != null && data.compareTo(ptr.getData()) >= 0 ) {
			savepoint = ptr;
			ptr = ptr.getNext();
		}
		/** if ptr is null , then it is at the end of the current list **/
		Node<T> newNode = Node.newNode(data);
		if ( ptr == null ) {
			savepoint.setNext(newNode);
			newNode.setPrevious(savepoint);
			return true;
		} else if ( ptr == head ) { /** new node is less than current head **/
			newNode.setNext(head);
			head.setPrevious(newNode);
			head = newNode;
			return true;
		} else { /** the node is somewhere in the middle **/
			/** ptr is greater than the newNode at this point  **/
			/** we need to place the newNode between ptr and the savepoint. **/
			/** the savepoint currently points at ptr **/
			newNode.setNext(ptr);
			newNode.setPrevious(savepoint);
			savepoint.setNext(newNode);
			ptr.setPrevious(newNode);
			return true;
		}
		
	}

	private boolean notSortInsertionPoint(Node<T> ptr, T data) {
		boolean larger = data.compareTo(ptr.getData()) >= 0 ;
		boolean smaller= data.compareTo(ptr.getNext().getData()) <=0 ;
		return !(larger && smaller) ;
	}

}
