/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.lambdaclosure;

import java.util.function.Function;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 9, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class Chapter2_2 {

	/**
	 * 
	 */
	public Chapter2_2() {
		dotest();
	}

	private void dotest() {
		Function<Integer,String> f = (Integer x) -> x.toString();
		Function<Integer, Object> f2  = f.andThen("10"::concat).andThen(Integer::parseInt);
		
		Function<String,Integer> b = Integer::parseInt;
		Function<Object,Integer> b2 = b.compose("10"::concat).compose(p -> String.valueOf(p));
		System.out.println(f2.apply(10));
		System.out.println(b2.apply("10") + 2);
	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Chapter2_2();

	}

}
