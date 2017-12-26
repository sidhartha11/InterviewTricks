/**
 * 
 */
package com.georgecurington.functionalstudymod.search;

import java.util.List;

import com.georgecurington.functionalstudymod.utilities.Pair;

/**
 * @author george
 * General interface for variious search implementations
 * based on a Collection only.
 *
 */
public interface GSearch<T> {
	T find(List<T> list, T element);
	int findIndex(List<T> list, T element);
	T find();
	int findIndex();
	List<Pair<T,Integer>> getDuplicates(List<T> list, T lookingfor, int foundIndex);
}
