/**
 * 
 */
package com.georgecurington.functionalstudymod.arrays;

import java.util.stream.IntStream;

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
public class CompareArrays {

	/**
	 * 
	 */
	public CompareArrays() {
		// TODO Auto-generated constructor stub
	}
	public static boolean notequal=true;
	public static <T extends Comparable<? super T>> boolean compareArraysSlow(T[] a , T[]b ){
		if ( a == b ) {
			return true;
		}
		if ( a == null || b == null ) {
			return false;
		}
		if ( a.length != b.length ) {
			return false;
		}
		IntStream.rangeClosed(0,a.length-1).forEach(p -> {
			if (a[p].equals(b[p]) == false ) {
				notequal=false;
				return;
			}
		});
		return notequal;
	}
	
	public static <T extends Comparable<? super T>> boolean compareArraysMedium(T[] a , T[] b){
		if ( a == b ) {
			return true;
		}
		if ( a == null || b == null ) {
			return false;
		}
		if ( a.length != b.length ) {
			return false;
		}
		
		for ( int i=0; i<a.length; i++ ) {
			if ( a[i].equals(b[i]) == false ){
				return false;
			}
		}
		return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] a = { 0 , 1 , 2 , 3 , 4 , 5 };
		Integer[] b = { 0 , 1 , 2 , 3 , 4 , 5 };
		Integer[] c = { 0 , 1 , 0 , 3 , 4 , 5 };
		Utility.p("compareArraysSlow:" + compareArraysSlow(a,b));
		Utility.p("compareArraysMedium:" + compareArraysMedium(a,b));
		Utility.p("compareArraysSlow:" + compareArraysSlow(a,c));
		Utility.p("compareArraysMedium:" + compareArraysMedium(a,c));
		Utility.p("a and null:" + compareArraysMedium(a,null));
		Utility.p("a and a:" + compareArraysMedium(a,a));

	}

}
