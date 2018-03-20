/**
 * 
 */
package com.georgecurington.functionalstudymod.strings.palindrome;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class Palindrome {

	public Palindrome() {
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * <pre>
	 * This method checks for half of the string being equal the the other half to determine
	 * if it is a palindrome or not. 
	 * Since it is only looking at half the elements at most to make the determinatin,
	 * worst case must be O(n/2) 
	 * </pre>
	 * @param string The string to be determined if it is a palindrome
	 * @return
	 */
	public boolean isPalindrome(String string){
		
		/** check extreme cases **/
		if ( string == null || string.length() <= 1 ) {
			return true;
		} else if ( string.length() == 2 ) {
			return string.charAt(0) == string.charAt(1);
		}
		
		/** all we need to do is check half of the string for equality with the other half **/
			StringBuilder sb = new StringBuilder(string);
			int length = sb.length();
			int leftmid = 0;
			int rightmid = 0 ; 
			if ( length % 2 != 0  ) {
				rightmid = length/2 + 1;
				leftmid = length/2 -1 ;
			} else {
				leftmid = length/2 -1  ;
				rightmid = length/2;
			}
			boolean keepgoing=true;
			while ( keepgoing && (leftmid >= 0 ) ) {
				
				char ch1 = sb.charAt(rightmid++);
				char ch2 = sb.charAt(leftmid--);
				if ( ch1 != ch2 ) {
					keepgoing=false;
				}
			}
			if ( false && keepgoing ){
				System.out.println("got palindrome " + string );
			}
			return keepgoing;
	}
	
	public static void main(String...strings ){
		Palindrome p = new Palindrome();
		Utility.p("abbba:" + p.isPalindrome("abbba"));
		Utility.p("abb:" + p.isPalindrome("abb"));
		Utility.p("abba:" + p.isPalindrome("abba"));
		Utility.p("null:" + p.isPalindrome(null));
		Utility.p("<e>:" + p.isPalindrome(""));
	}
}
