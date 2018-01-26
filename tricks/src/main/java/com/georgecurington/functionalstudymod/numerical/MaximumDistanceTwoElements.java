/**
 * 
 */
package com.georgecurington.functionalstudymod.numerical;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.utilities.Pair;
import com.georgecurington.functionalstudymod.utilities.PairImpl;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * This is a well known trick problem. The issue is to determine 
 * which two numbers in an array are the maximum distance apart.
 * This solution, created by some math student also returns the two 
 * indexes that contain the elements that produce the solution.
 * The use of Pair, PairImpl, Answer is just for amusement
 * </pre>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 26, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class MaximumDistanceTwoElements {

	private static final boolean DEBUG = false;

	/**
	 * 
	 */
	public MaximumDistanceTwoElements() {
		// TODO Auto-generated constructor stub
	}
	
	class Answer {
		private final Pair<Integer, Pair<Integer,Integer>> answer;
		
		public Answer(Pair<Integer , Pair<Integer,Integer>> answer) {
			this.answer = answer;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Answer [answer=" + answer + "]";
		}
	}
	
	public Answer maxDistance(int[] arr ) {
		int minEle = arr[0];
		int maxDiff = arr[1] - arr[0];
		int maxelement = 0;
		int minelement = 0;
		int savemin = 1;
		for ( int i = 1; i < arr.length ; i++ ){
			if ( arr[i] - minEle > maxDiff ){
				maxDiff = arr[i] - minEle;
				maxelement = i;
				savemin = minelement;
				if (DEBUG) System.out.println("maxelemnt=" + maxelement + ",savemin=" + savemin);
			}
			if ( arr[i] < minEle ){
				minEle = arr[i];
				minelement = i ;
				if (DEBUG) System.out.println("minelement=" + i);
			}
		}
		return new Answer( (Pair<Integer, Pair<Integer, Integer>>) new PairImpl(maxDiff, new PairImpl(maxelement,savemin)));
	}
	
	public static void main(String...strings ){
		int [] arr = {12, 20, 15, 30, 32, 7, 18};
		MaximumDistanceTwoElements maxDist = new MaximumDistanceTwoElements();
		Answer answer = maxDist.maxDistance(arr);
		Utility.p(answer.toString());
	}

}
