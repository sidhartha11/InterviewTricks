/**
 * 
 */
package com.georgecurington.functionalstudymod;

import java.util.function.Function;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Mar 20, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class TestClass {

	public static Function<Integer,Double> factorial;

	static {
	    factorial = n -> {
	        assert n >= 0;
	        return (n == 0) ? 1.0 : n * factorial.apply(n - 1);
	    };
	}
	/**
	 * 
	 */
	public TestClass() {
		System.out.println(factorial.apply(5));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestClass();

	}

}
