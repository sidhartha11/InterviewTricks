/**
 * 
 */
package com.georgecurington.functionalstudymod.arrays;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 13, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class FindMissingNumberTwoDuplicateArrays {

	/**
	 * 
	 */
	public FindMissingNumberTwoDuplicateArrays() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = { 0 , 1 , 2 , 3 , 4 , 5 };
		int[] c = { 1 , 0 , 4 , 5 , 3 };
		int[] b = { 0 , 1 ,  3 , 4 , 5 };
		
		int missingNumber = findMissingNumber(a,c);
		Utility.p("missing number is " + missingNumber );

	}

	private static  int findMissingNumber(int[] a, int[] b) {
		/** assuming valid arrays so skippping error checks **/
		int longest = Math.max(a.length,b.length);
		int xor = 0 ; 
		for ( int i = 0 ; i < a.length ; i++ ) {
			xor ^= a[i]; 
		}
		for ( int i = 0 ; i < b.length ; i++ ) {
			xor ^= b[i]; 
		}
		return xor;
	}

}
