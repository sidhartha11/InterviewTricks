/**
 * 
 */
package com.georgecurington.functionalstudymod.generics.gac;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 7, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class Chapter2 {

	/**
	 * 
	 */
	public Chapter2() {
		doexample_1();
	}

	private void doexample_1() {
		List<Number> nums = new ArrayList<>();
		nums.add(2);
		nums.add(3.14);
		System.out.println(nums.toString());
		assert nums.equals("[2, 3.14]");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Chapter2();

	}

}
