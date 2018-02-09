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
public interface DLinkList<T> extends Iterable<T> {
	public boolean insertAtHead(T data	 );
	public boolean insertAtEnd (T data   );
	public boolean insertAtMiddle( T insertPosition , T data );
	public boolean insertBeforeMiddle(T insertPosition, T data);
	public boolean insertInOrder(T data);
	public boolean deleteHeadNode();
	public int size();
	public boolean deleteANode(T data);
	public T findMiddle();
}
