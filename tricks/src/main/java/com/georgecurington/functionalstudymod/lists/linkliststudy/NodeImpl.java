/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.linkliststudy;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 24, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class NodeImpl<T> implements Node<T> {

	protected Node<T> previous;
	protected Node<T> next;
	protected  T data;
	/**
	 * 
	 */
	public NodeImpl(T data) {
		this.data = data ; 
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.lists.linkliststudy.Node#getPrevious()
	 */
	@Override
	public Node<T> getPrevious() {
		// TODO Auto-generated method stub
		return this.previous;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.lists.linkliststudy.Node#getNext()
	 */
	@Override
	public Node<T> getNext() {
		// TODO Auto-generated method stub
		return this.next;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.lists.linkliststudy.Node#setPrevious(com.georgecurington.functionalstudymod.lists.linkliststudy.Node)
	 */
	@Override
	public void setPrevious(Node<T> newPrevious) {
		this.previous = newPrevious;

	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.lists.linkliststudy.Node#setNext(com.georgecurington.functionalstudymod.lists.linkliststudy.Node)
	 */
	@Override
	public void setNext(Node<T> newNext) {
		this.next = newNext;

	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.lists.linkliststudy.Node#getData()
	 */
	@Override
	public T getData() {
		// TODO Auto-generated method stub
		return this.data;
	}
	
	@Override
	public void setData(T data){
		this.data = data ; 
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NodeImpl [previous=" + previous + ", next=" + next + ", data=" + data + "]";
	}

}
