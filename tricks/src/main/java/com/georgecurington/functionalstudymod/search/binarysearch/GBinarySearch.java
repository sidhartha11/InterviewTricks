/**
 * 
 */
package com.georgecurington.functionalstudymod.search.binarysearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.georgecurington.functionalstudymod.search.GSearch;
import com.georgecurington.functionalstudymod.utilities.Pair;
import com.georgecurington.functionalstudymod.utilities.PairImpl;

/**
 * @author george
 *
 */
public class GBinarySearch<T extends Comparable<? super T>> implements GSearch<T> {

	private List<T> list;
	private T element;
	/**
	 * 
	 */
	public GBinarySearch() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param list
	 * @param element
	 */
	public GBinarySearch(List<T> list, T element) {
		super();
		this.list = list;
		this.element = element;
	}

	@Override
	public T find(List<T> list, T element) {
		this.list = list;
		this.element = element;
		return find();
	}

	@Override
	public int findIndex(List<T> list, T element) {
		this.list = list;
		this.element = element;
		return search2Index(element);
	}

	@Override
	public T find() {
		Objects.requireNonNull(list);
		Objects.requireNonNull(element);
		return search2(element);
	}

	@Override
	public int findIndex() {
		Objects.requireNonNull(list);
		Objects.requireNonNull(element);
		return search2Index(element);
	}
	
	/** recursive search **/
    protected T search2( T item ) {
    	return search2(0,list.size()-1, item);
    }
	protected T search2(int start, int end, T item) {
		if ( end < start  ) {
			return null ;
		} else {
			int midpoint = (start + end)/2;
			int found = item.compareTo(list.get(midpoint));
			if (found < 0){			
				return search2(start,midpoint-1,item);
			} else if ( found > 0 ) {
				return search2(midpoint + 1, end,item);
			} else  {
				return item;
			} 
		}
	}
	
	/** recursive search **/
    protected int search2Index( T item ) {
    	return search2Index(0,list.size()-1, item);
    }
	protected int search2Index(int start, int end, T item) {
		if ( end < start  ) {
			return -1 ;
		} else {
			int midpoint = (start + end)/2;
			int found = item.compareTo(list.get(midpoint));
			if (found < 0){			
				return search2Index(start,midpoint-1,item);
			} else if ( found > 0 ) {
				return search2Index(midpoint + 1, end,item);
			} else  {
				return midpoint;
			} 
		}
	}

	@Override
	public List<Pair<T,Integer>> getDuplicates(List<T> list, T lookingfor, int foundIndex) {
		List<Pair<T,Integer>> pairs = null;
		/** take care of end point cases first **/
		if ( foundIndex == 0 ) { // just search in forward direction
			pairs = getDuplicatedElements(list,lookingfor,foundIndex,1);
		} else if ( foundIndex == list.size()-1) { // just search in backwards direction
			pairs = getDuplicatedElements(list,lookingfor,foundIndex,-1);
		} else { // need to search in both directions
			pairs = getDuplicatedElements(list,lookingfor,foundIndex,1);			
			List<Pair<T,Integer>> pairs2 = getDuplicatedElements(list,lookingfor,foundIndex-1,-1);
			if (pairs2 != null && pairs2.size() > 0){
				pairs.addAll(pairs2);
			}
		}
		return pairs;
	}

	private List<Pair<T, Integer>> getDuplicatedElements(
			List<T> list
			, T lookingfor
			, int foundIndex
			, int j) {
		
		List<Pair<T,Integer>> pairs = new ArrayList<>();
		
		int x = foundIndex;
        while (list.get(x).compareTo(lookingfor) == 0) {
        	pairs.add(new PairImpl<T,Integer>(list.get(x),x));
        	x=x+j;
        }
		return pairs;
	}

}
