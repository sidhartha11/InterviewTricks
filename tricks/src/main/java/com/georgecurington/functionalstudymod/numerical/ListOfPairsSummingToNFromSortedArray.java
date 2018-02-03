/**
 * 
 */
package com.georgecurington.functionalstudymod.numerical;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.georgecurington.functionalstudymod.utilities.Pair;
import com.georgecurington.functionalstudymod.utilities.PairImpl;

/**
 * <pre>
 * <p><b>INTERVIEW TRICKS</b></p>
 * </pre>
 * <p>
 * ================================================
 * </p>
 * 
 * <pre>
 * This program will return all the pairs in an array that sum up 
 * to a particular value in a SORTED array. The fact that the array is sorted
 * makes the problem pretty simple.
 * </pre>
 * 
 * <br>
 * 
 * @author George Curington
 * @version 1.0.0
 * @since Feb 3, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */

public class ListOfPairsSummingToNFromSortedArray {

	/**
	 * 
	 */
	public ListOfPairsSummingToNFromSortedArray() {
		// TODO Auto-generated constructor stub
	}

	public Set<Pair<Integer, Integer>> findPairsSumming(int[] arr, int sum) {
		Set<Pair<Integer, Integer>> pairs = new HashSet<>();
		int l = 0;
		int r = arr.length - 1;
		while (l < r) {
			if (arr[l] + arr[r] > sum) {
				r--;
			} else if (arr[l] + arr[r] < sum) {
				l++;
			} else {
				pairs.add(new PairImpl<>(arr[l], arr[r]));
				r--;
			}
		}
		return pairs;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = { 5, 8, 3, 4, 2, 6, 10, 7, 1, 9 };
		Arrays.sort(arr);
		ListOfPairsSummingToNFromSortedArray prs = new ListOfPairsSummingToNFromSortedArray();
		Set<Pair<Integer, Integer>> set = prs.findPairsSumming(arr, 11);
		set.forEach(System.out::println);
	}

}
