/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.java8inaction;

import java.util.List;
import java.util.concurrent.Future;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 10, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public interface ShopIntf {
	Future<Double> getP(String product);
	double getPD(String product);
	String getName();
}
