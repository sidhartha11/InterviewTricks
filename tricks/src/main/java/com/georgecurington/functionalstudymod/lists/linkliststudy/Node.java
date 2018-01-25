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
public interface Node<T> {
	Node <T> getPrevious();
	Node <T> getNext();
	void setPrevious(Node<T> newPrevious);
	void setNext(Node<T> newNext);
	T getData();
	void setData(T data);
	static <T> Node<T> newNode(T data) {
		Node<T> n = new NodeImpl<>(data);
		return n;
	}
}
