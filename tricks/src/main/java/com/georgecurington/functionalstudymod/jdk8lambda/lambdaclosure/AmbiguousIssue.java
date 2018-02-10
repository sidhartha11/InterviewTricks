/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.lambdaclosure;

import java.util.function.Function;
import java.util.function.IntFunction;

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
public class AmbiguousIssue {

	static void methodBeingCalled(Function<Integer,String> function){
		
	}
	
	static void methodBeingCalled(IntFunction<String> function){
		
	}
	/**
	 * 
	 */
	public AmbiguousIssue() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// this will throw an exception
		//methodBeingCalled(i -> Integer.toString(i));
		
		// this will not 
		methodBeingCalled((int i) -> Integer.toString(i));

	}

}
