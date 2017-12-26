/**
 * 
 */
package com.georgecurington.functionalstudymod.sorts;

import java.util.List;

/**
 * @author george
 *
 */
public interface GSort<T> {
	List<T> getList();
	void sort(List<T> data);
	void sort();
	void sort(boolean experimentalPivot);
	int partition(List<T> data, int start, int end);
}
