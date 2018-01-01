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
 *          <p>
 * 			<center>Pairs of numbers equaling a sum</center>
 *          </p>
 * 
 *          <pre>
 * This trick question will return unique pairs of numbers
 * that equal to a particular N. The input array is sorted; a requirement.
 *          </pre>
 *          <p>
 * 			The algorithm proceeds as follows:
 *          <ul>
 *          <li>Load the sorted array into a map
 *          <li>loop thru the sorted array
 *          <li>If the -map(current-N) exists, then you hava a pair.
 *          <li>put the pair in a set to get rid of duplicates.
 *          </ul>
 *
 */
public class GAddTwoNumberSummingToN {
	private static final boolean DEBUG = false;

	public GAddTwoNumberSummingToN() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	public Set<List<Integer>> getSolutionPairSetFromSortedArray(Integer[] input, int nmbr) {
		/** map to store initial input array **/
		Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

		/** set used to remove duplicates from solution set **/
		Set<List<Integer>> unique = new HashSet<>();

		/** for collecting non unique intermediate solution set **/
		List<Integer> dups = null;

		/** put the array in the map **/
		for (int i = 0; i < input.length; i++) {
			Map<Integer, Integer> innerMap = map.get(input[i]);
			if (innerMap == null) {
				innerMap = new HashMap<>();
				innerMap.put(i, i);
				map.put(input[i], innerMap);
			} else {
				innerMap.put(i, i);
			}
		}
		if (DEBUG) {
			System.out.println(map);
		}
		/** loop thru the array to get solution set **/
		int cntr = 0;
		int len = input.length;
		for (int a = 0; a < len; a++) {
			cntr++;
			int k = -(input[a] - nmbr);
			boolean found = false;
			Map<Integer, Integer> innerMap = map.get(k);
			if (DEBUG) {
				System.out.println("cntr=" + cntr + ",input[a]=" + input[a] + ",k=" + k + ",a=" + a
						+ ",-(input[a]-nmbr)=" + (-(input[a] - nmbr)));
				System.out.println("innerMap=" + innerMap);
			}

			/**
			 * The condition being checked here is: If the current value we are
			 * looking up in the map is identical to the value located in the
			 * array at index 'a' , then we need to make sure there are multiple
			 * values for that value stored in the map; otherwise we could end
			 * up with duplicating that value in the solution set.
			 */
			if (innerMap != null) {
				if (k == input[a])
					found = innerMap.size() > 1;
				else {
					found = true;
				}
			}
			if (
			// map.containsKey(k)
			found) {
				if (DEBUG) {
					System.out.println("** " + k + "," + input[a]);
				}
				dups = new ArrayList<>();
				dups.add(k);
				dups.add(input[a]);
				dups.sort((Integer e1, Integer e2) -> e1.compareTo(e2));
				unique.add(dups);
				if (DEBUG) {
					System.out.println((cntr) + ": " + map.get(k) + "," + input[a]);
					System.out.println((cntr) + ":unique " + unique);

				}
			}
		}
		return unique;
	}

}
