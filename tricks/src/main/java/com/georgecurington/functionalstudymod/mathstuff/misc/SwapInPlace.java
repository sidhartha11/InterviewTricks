/**
 * 
 */
package com.georgecurington.functionalstudymod.mathstuff.misc;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * This is a trick question that pops up from time to time on an 
 * interview. How to swap two numbers without using a temporary 
 * variable:
 * 
 * Two methods:
 * 1. using the XOR operator 
 * 2. using addition and subtraction
 * 
 * </pre>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 31, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class SwapInPlace {
	long nmbr_1=1034;
	long nmbr_2=5678;

	/**
	 * 
	 */
	public SwapInPlace() {
		Utility.p("nmbr_1=" + nmbr_1);
		Utility.p("nmbr_2=" + nmbr_2);
		swapAdd();
		Utility.p("nmbr_1=" + nmbr_1);
		Utility.p("nmbr_2=" + nmbr_2);
	}

	private void swapAdd() {

		long a = nmbr_1;
		long b = nmbr_2;
		
		nmbr_1 = nmbr_1 + nmbr_2 ;
		nmbr_2 = nmbr_1 - nmbr_2 ;
		nmbr_1 = nmbr_1 - nmbr_2 ;
		
	}

	private void swapXOR() {
		
		long a = nmbr_1;
		long b = nmbr_2;

		nmbr_1 = nmbr_1 ^ nmbr_2 ;
		nmbr_2 = nmbr_1 ^ nmbr_2 ;
		nmbr_1 = nmbr_1 ^ nmbr_2;

		
	}
	
	public static void main(String...strings ) {
		new SwapInPlace();
	}

}
