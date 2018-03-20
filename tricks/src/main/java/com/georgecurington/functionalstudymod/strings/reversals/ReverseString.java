/**
 * 
 */
package com.georgecurington.functionalstudymod.strings.reversals;

import java.util.Arrays;
import java.util.Stack;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class ReverseString {

	/**
	 * 
	 */
	public ReverseString() {
		// TODO Auto-generated constructor stub
	}
	
	public String reverse(String string) {
		if ( string == null ){
			throw new IllegalAccessError("illegal string");
		} 
		
		StringBuilder sb = new StringBuilder(string);
		int mid = sb.length()/2;
		int length=sb.length();
		for ( int i = 0 ; i < mid ; i++ ){
			char ch = sb.charAt(length-i-1);
			sb.setCharAt(length-i-1, sb.charAt(i));
			sb.setCharAt(i, ch);
		}
		return sb.toString();	
	}
	
	public String reverseArr(String string) {
		char[] arr = string.toCharArray();
		int length = arr.length;
		int mid = length/2;
		for ( int i = 0 ; i < mid ; i++ ){
			char ch = arr[length-i-1];
			arr[length-i-1]=arr[i];
			arr[i]= ch;
		}
		return String.valueOf(arr);
	}
	
	public String reverseByte(String string) {

		byte[] arr = string.getBytes();
		int length = arr.length;
		int mid = length/2;
		for ( int i = 0 ; i < mid ; i++ ){
			byte ch = arr[length-i-1];
			arr[length-i-1]=arr[i];
			arr[i]= ch;
		}
		return new String(arr);
	}
	
	public String reverseRecur(String string) {
		return revRecur(string,"");
	}

	private String revRecur(String string, String string2) {
		if ( string.length()==0 ) {
			return string2;
		}
		return revRecur(string.substring(1),string.substring(0, 1) + string2);
	}
	
	public static void main(String...strings ){
		String s = ""	;
		ReverseString rev = new ReverseString();
		Utility.p(rev.reverse(s));
	}

}
