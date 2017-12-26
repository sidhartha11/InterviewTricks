/**
 * 
 */
package com.georgecurington.functionalstudymod.numerical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 * <p><center>Pairs of numbers equaling a sum</center></p>
 * <pre>
 * This trick question will return unique pairs of numbers
 * that equal to a particular N. The input array is sorted; a requirement.
 * </pre>
 * <p>The algorithm proceeds as follows:
 * <ul>
 * <li>Load the sorted array into a map
 * <li>loop thru the sorted array
 * <li>If the -map(current-N) exists, then you hava a pair.
 * <li>put the pair in a set to get rid of duplicates.
 * </ul>
 *
 */
public class GAddTwoNumberSummingToN {
	private static final boolean DEBUG=false;

	/**
	 * 
	 */
	public GAddTwoNumberSummingToN() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Set<List<Integer>> getSolutionPairSetFromSortedArray(Integer[] input, int nmbr) {
		/** set used to remove duplicates from solution set **/
		Set<List<Integer>> unique = new HashSet<>();

		/** for collecting non unique intermediate solution set **/
		List<Integer> dups = new ArrayList<>();
		
		/** put the array into a map first **/
		Map<Integer,Integer> map = new HashMap<>();
		/** for positive numbers use the below optimization **/
//		Arrays.stream(input).filter(p -> p < nmbr).forEach(p -> {
		Arrays.stream(input).forEach(p -> {

			map.put(p,p);
		});
		/** loop thru the array to get solution set **/
		int len = map.size();
		System.out.println(Arrays.toString(input));
		System.out.println(nmbr); 
		for (int a = 0; a < len; a++) {
			int k = -(input[a] - nmbr );
			if (map.containsKey(k)) {
				if ( DEBUG ){
			System.out.println(map.get(k) + "," + input[a] );
				}
			dups.add(map.get(k));
			dups.add(map.get(input[a]));
			dups.sort((Integer e1, Integer e2) -> e1.compareTo(e2));
			unique.add(dups);
			dups = new ArrayList<>();
		}	
		}
		return unique;
	}

}
