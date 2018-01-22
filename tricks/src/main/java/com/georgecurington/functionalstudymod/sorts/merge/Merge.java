/**
 * 
 */
package com.georgecurington.functionalstudymod.sorts.merge;

import java.util.List;

import com.georgecurington.functionalstudymod.utilities.Pair;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 21, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public interface Merge<T> {
	public void  merge(List<T> data,int p, int r, int q );

	public Pair<List<T>, List<T>> getHalves(List<T> data, int p, int q, int r);

	public void merge_sort(List<T> data, int p, int r);

}
