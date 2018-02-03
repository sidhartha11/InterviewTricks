/**
 * 
 */
package com.georgecurington.functionalstudymod.mathstuff.dynamic.subsum;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.georgecurington.functionalstudymod.utilities.Pair;

/**
 * <pre>
 * <p><b>INTERVIEW TRICKS</b></p>
 * </pre>
 * 
 * <b> SUBSET SUM PROBLEM </b>
 * 
 * <pre>
 * This is a simple naive solution to creating the memoization matrix for 
 * solving the subset sum problem. This approach attempts to take the word description of 
 * the problem and set that word description to programming. 
 * NOTE: 
 * This is not the an optimize solution by no means; yet. It is a simple mapping from 
 * the words of a Professor directly to a Java program. Super redirection of array indexes are not used
 * in this approach. High level Java collections are used instead to map directly to the words of 
 * the professor. I may convert this high level approach to a low-level array approach later.
 * ( once I fully understand the high level approach and what it actaully means and is used for.
 * </pre>
 * <p>
 * ================================================
 * </p>
 * <br>
 * 
 * @author George Curington
 * @version 1.0.0
 * @since Feb 1, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class SubSumProblem {

	/**
	 * <pre>
	 * The availableSet is a set of numbers that we want to find values 
	 * from that sum up to the number, solutionsize, below. The availableSet
	 * is sorted with the first number in the set being set to zero.
	 * The memoizationMatrix is a two element array of boolean values of 
	 * dimension availableset.length, solutionsetsize.
	 * </pre>
	 * 
	 */
	public SubSumProblem() {
	}

	/**
	 * @param availableSet
	 *            the Set of numbers that we hope constains a subset that sums
	 *            to solutionsize
	 * @param solutionsize
	 *            the size we wish to find a subset that sums to.
	 * @param memoizationMatrix
	 *            the boolean matrix used to solve the problem using dynamic
	 *            programming.
	 * @return matrix representing the memoization matrix
	 */
	public boolean[][] getMemoizationMatrix(List<Pair<Integer, Integer>> availableSet, int solutionsize,
			boolean[][] memoizationMatrix) {
		Set<Pair<Integer, Integer>> captureSet = new LinkedHashSet<>();
		for (int j = 0; j < availableSet.size(); j++) {
			Pair<Integer, Integer> newValue = availableSet.get(j);
			for (int i = 0; i <= solutionsize; i++) {
				/** the base case is 0 **/
				switch (j) {
				case 0:
					if (availableSet.get(j).getLeft() >= i) {
						memoizationMatrix[j][i] = true;
					} else {
						memoizationMatrix[j][i] = false;
					}
					break;
				default:
					/**
					 * formula: Truth value by excluding new value | Truth value
					 * by including new value
					 */
					boolean b = false;
					for (Pair<Integer, Integer> pr : captureSet) {
						/** can we use the captureArraySet to form i ? **/
						Integer val = pr.getLeft();
						Integer idx = pr.getRight();
						b = memoizationMatrix[idx][i];
						if (b) {
							break;
						}
					}
					boolean newb = false;
					/** note that availableSet.get(j) = newValue **/
					if (availableSet.get(j).getLeft() == i) {
						newb = true;
					} else if (availableSet.get(j).getLeft() < i) {
						/**
						 * if the new value is less than i, then we must figure
						 * out if it can be used to form i
						 */
						int sum = i - newValue.getLeft();
						/**
						 * we need to look at each value in the captureArraySet
						 * and see if one of the values has true for the sum
						 */
						for (Pair<Integer, Integer> pr : captureSet) {
							/** can we use the captureArraySet to form sum ? **/
							Integer val = pr.getLeft();
							Integer idx = pr.getRight();
							newb = memoizationMatrix[idx][sum];
							if (newb) {
								break;
							}
						}
					}
					memoizationMatrix[j][i] = b || newb;
				}
			} // inner for loop
			captureSet.add(newValue);
		} // outer for loop
		return memoizationMatrix;
	}

	public static void main(String... strings) {
		new SubSumProblem();
	}
}
