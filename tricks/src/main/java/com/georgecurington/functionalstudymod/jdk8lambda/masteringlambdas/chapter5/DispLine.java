/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.masteringlambdas.chapter5;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * 
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 26, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class DispLine {
	final int disp;
	final String line;
	DispLine(int d , String l) {
		disp = d;
		line = l;
	}
	
	public String toString() {
		return disp + " " + line;
	}

}
