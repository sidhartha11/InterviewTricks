/**
 * 
 */
package com.georgecurington.functionalstudymod.numerical;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * Another popular trick question that comes up from time to time
 * on core java interviews. This one, yet another math related problem,
 * ask you to find the missing number in an array of integers. I assume
 * it means this:
 * If you have an int[] array of say 10 elements containing random values.
 * If present with only length-1 of the elements, what is the missing number ?
 * I found this math formula on the internet via google which seems to be 
 * one of the popular answers that solves the problem in O(n) time as opposed to 
 * the naive brute force forumula. I will put both approaches here:
 * brute force:
 * add all the numbers up.
 * then add all the numbers up minus the missing number.
 * subtract the two answers.
 * 
 * O(n) way:
 * 1. Get the sum of numbers using this forumula: total = n*(n+1)/2
 * 2. Subtract all the numbers from sum and you will get the missing number.
 * or 
 * using xor 
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 26, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class MIssingNumberInArray {

	/**
	 * 
	 */
	public MIssingNumberInArray() {
		// TODO Auto-generated constructor stub
	}
	
	public int bruteForce(int[] arr) {
	    int n = arr.length + 1;
	    boolean[] flag = new boolean[n];
	    // Of course you can combine these two for-loops together.
	    for (int i = 0; i < arr.length; i++) {
	        flag[arr[i] - 1] = true;
	    }
	    for (int i = 0; i < n; i++) {
	        if (!flag[i])
	            return i + 1;
	    }
	    return n;
		
	}
	
	public int formula(int[] arr) {
		  int n=arr.length+1;
		  int sum=n*(n+1)/2;
		  int restSum=0;
		  for (int i = 0; i < arr.length; i++) {
		   restSum+=arr[i];
		  }
		  int missingNumber=sum-restSum;
		  return missingNumber;
	}
	
	public int xorFormula(int[] arr){
	    int len = arr.length + 1;
	    int n = 0;
	    // Of course you can combine these two for-loops together.
	    for (int i = 0; i < arr.length; i++) {
	        n = n ^ arr[i];
	    }
	    for (int i = 1; i <= len; i++) {
	        n = n ^ i;
	    }
	    return n;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr1={7,5,6,1,4,2};
		MIssingNumberInArray m = new MIssingNumberInArray();
		Utility.p("brute force=" + m.bruteForce(arr1));
		Utility.p("formula=" + m.formula(arr1));
		Utility.p("xorFormula=" + m.xorFormula(arr1));

	}

}
