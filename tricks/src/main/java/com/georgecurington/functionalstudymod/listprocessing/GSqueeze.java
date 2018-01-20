/**
 * 
 */
package com.georgecurington.functionalstudymod.listprocessing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * Simple algorithm to remove duplicates from a sorted array.
 * The only thing interesting about the algorithm is the 
 * one that does the removal in place. This one has space complexity
 * of O(1).
 * The recursive version is interesting in how it works.
 * </pre>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 20, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * @param <T>
 */
public class GSqueeze<T> implements GSqueezeIntf<T> {

	private final List<T> list;

	/**
	 * 
	 */
	public GSqueeze(List<T> list) {
		Objects.requireNonNull(list);
		this.list = list;
	}

	@Override
	public List<T> squeeze() {
		return squeezeSortedRecursive(list, new ArrayList<T>());
	}

	@Override
	public List<T> squeezeIterTemp() {
		return squeezeSortedIterativeTempList(list);
	}

	private List<T> squeezeSortedRecursive(List<T> list2, ArrayList<T> acc) {
		if (list2.size() == 1) {
			acc.add(list2.get(0));
			return acc;
		} else if (!list2.get(0).equals(list2.get(1))) {
			acc.add(list2.get(0));
			return squeezeSortedRecursive(list2.subList(1, list2.size()), acc);
		} else {
			return squeezeSortedRecursive(list2.subList(1, list2.size()), acc);
		}
	}

	private List<T> squeezeSortedIterativeTempList(List<T> list2) {
		List<T> temp = new ArrayList<>();
		int limitsize = list2.size() - 1;
		int j = 0;
		for (int i = 0; i < limitsize; i++) {
			if (!list2.get(i).equals(list2.get(i + 1))) {
				temp.add(j, list2.get(i));
				j++;
			}
		}
		temp.add(list2.get(limitsize));
		return temp;
	}

}
