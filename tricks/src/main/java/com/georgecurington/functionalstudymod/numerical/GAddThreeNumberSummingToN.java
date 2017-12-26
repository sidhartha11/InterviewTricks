package com.georgecurington.functionalstudymod.numerical;

/**
 * 
 */
import java.util.ArrayList;
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
 *          <center> <b>TRICK PROBLEM</b> </center>
 *          </p>
 * 
 *          <pre>
 * This is a popular trick number problem. The idea is:
 * given:
 * An unsorted array of integers.
 * Some target number N.
 * problem:
 * Find all the 'unique' triplets occuring in the array that 
 * sum up to some number N.
 * Solution:
 * There are many different solutions and approaches that can
 * be taken to such a problem. This problem can also be extended
 * to include a clustered environment which will not be
 * done here. The simple ONE JVM solution that I take is as 
 * follows:
 * 
 * Insert all the numbers initially into a map
 * next compare all the combinations duplicates against the
 * data in the map using:
 * {@code -( element1 + element2 - nmbr ) }
 * 			</pre>
 * 
 *          <pre>
 * Remove duplicates from the solution set
 *          </pre>
 *
 */
public class GAddThreeNumberSummingToN {
	private static final boolean DEBUG = false;
	static private Integer[] input = { 3, 4, -3, 2, -8, 4, 10, 1, 1, 88, 1, 98, -10, 4, 4, -8, 30 };

	/**
	 * 
	 */
	public GAddThreeNumberSummingToN() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<List<Integer>> unique = getSolutionSet(input, 5);
		unique.forEach((v) -> {
			System.out.println(v);
		});

	}

	private static Set<List<Integer>> getSolutionSet(Integer[] input, int nmbr) {

		/** map to store initial input array **/
		Map<Integer, Integer> map = new HashMap<>();

		/** set used to remove duplicates from solution set **/
		Set<List<Integer>> unique = new HashSet<>();

		/** for collecting non unique intermediate solution set **/
		List<Integer> dups = new ArrayList<>();
		
		/** put the array in the map **/
		for (int i = 0; i < input.length; i++) {
			map.put(input[i],input[i]);
		}

		/** loop thru the array to get solution set **/
		int len = input.length;
		for (int a = 0; a < len; a++) {
			for (int b = a + 1; b < len ; b++) {

				int k = -(input[a] + input[b] - nmbr) ;
				if (map.containsKey(k)) {
					/** we found a triple **/
					if (DEBUG) {
						System.out.println("** " + k + "," + input[a] + "," + input[b]);
					}

					dups = new ArrayList<>();
					dups.add(map.get(k));
					dups.add(map.get(input[a]));
					dups.add(map.get(input[b]));
					dups.sort((Integer e1, Integer e2) -> e1.compareTo(e2));
					unique.add(dups);

					if (DEBUG) {
						System.out.println(map.get(k) + "," + map.get(input[a]) + "," + map.get(input[b]));
					}
				}
			}
		}

		return unique;
	}

}
