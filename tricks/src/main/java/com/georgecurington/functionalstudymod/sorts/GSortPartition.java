/**
 * 
 */
package com.georgecurington.functionalstudymod.sorts;

import java.util.List;

/**
 * @author george
 *
 */
public interface GSortPartition<T> {
	int partition(List<T> data, int start, int end);

}
