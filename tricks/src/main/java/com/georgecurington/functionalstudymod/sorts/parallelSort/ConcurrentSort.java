/**
 * 
 */
package com.georgecurington.functionalstudymod.sorts.parallelSort;

import java.util.List;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 23, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public interface ConcurrentSort<T> {
	void process(List<T> data);

	void stop();

	List<T> getSortedList();

	List<T> processFullFile(List<T> data);
}
