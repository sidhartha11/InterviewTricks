/**
 * 
 */
package com.georgecurington.functionalstudymod.listprocessing;

import java.util.List;


/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 20, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * @param <T>
 */
public interface GSqueezeIntf<T> {
	public List<T> squeeze();

	List<T> squeezeIterTemp();
}
