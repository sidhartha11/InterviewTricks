/**
 * 
 */
package com.georgecurington.functionalstudymod.strings;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre>
 * <p><b>INTERVIEW TRICKS</b></p>
 * </pre>
 * <p>
 * ================================================
 * </p>
 * 
 * <pre>
 * This trick question involves finding the largest identical sequece
 * of characters in n bodies of text. The usual case is to 
 * search two bodies of text and find the largest sequence of 
 * characters that are identical in each body of text.
 * This is an interesting problem in that it seemingly might
 * involve getting all the possible permutations of each body and 
 * comparing them. This sounds like it might be incredibably slow.
 * But ... let the quest begin
 * </pre>
 * 
 * <br>
 * 
 * @author George Curington
 * @version 1.0.0
 * @since Feb 15, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class LargestIdenticalSubstringInTwoTexts {
	private final String string1 = "GeorgeCurington";
	private final String string2 = "CassandraCurington";

	/**
	 * 
	 */
	public LargestIdenticalSubstringInTwoTexts() {

		String s = doLongestSubStringTest(string1, string2);
		Utility.p(s);
	}

	private String doLongestSubStringTest(String a, String b) {
		String out = "";
		emptyStringCheck(a, b);

		/** the rows represent elements of a and the columns elements of b **/
		int[][] cache = new int[a.length()][b.length()];

		for (int i = 0; i < a.length(); i++) {
			/** now go thru the columns of b **/
			for (int j = 0; j < b.length(); j++) {

				/** lets check the current character in each string **/
				if (a.charAt(i) == b.charAt(j)) {
					/**
					 * the line below appears to be incorrect , need to check
					 **/
					if (i == 0 || j == 0) {
						cache[i][j] = 1;
					} else {
						cache[i][j] = cache[i - 1][j - 1] + 1;
					}

					if (cache[i][j] > out.length()) {
						out = a.substring(i - cache[i][j] + 1, i + 1);
					}
				}
			}
		}
		return out;
	}

	private void emptyStringCheck(String string1, String string2) {
		if (string1 == null || string2 == null)
			throw new RuntimeException("Strings cannot be empty");
		if (string1.length() == 0 || string2.length() == 0)
			throw new RuntimeException("Strings cannot be size 0");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LargestIdenticalSubstringInTwoTexts l = new LargestIdenticalSubstringInTwoTexts();

	}

}
