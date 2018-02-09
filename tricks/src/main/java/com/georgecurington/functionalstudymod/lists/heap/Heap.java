/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.heap;

import java.util.List;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 5, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public interface Heap<T> {
	
	int getLeftIndex(int parentIndex);
	int getRightIndex(int parentIndex);
	int getParent(int childIndex);
	int getHeap_size();
	int leafStart(int numberNodes);
	int getNmbrNodesBasedOnHeight(int height);
	int getNmbrNodesBasedOnHeightTernary(int height);
	int getHeightOfCompleteBinaryTree(int p);
	int getLeafStart(int p);
	void maxHeapify(List<T> list, int i);
	void setSize(int size);
	int getSize();
	void exchange(List<T> list, int i, int largest);
	T extractMax(List<T> list);
	void buildHeap(List<T> list, boolean b);

}
