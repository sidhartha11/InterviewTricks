/**
 * 
 */
package com.georgecurington.functionalstudymod.generics.gac;

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
public interface Utils {

	public static <T> void 
	copy (List<? super T> dst, List<? extends T> src){
		
		
		for (int i = 0 ; i < src.size(); i++){
			dst.set(i, src.get(i));
		}
		
	}
}
