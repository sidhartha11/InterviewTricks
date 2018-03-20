/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.completablefuture;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 12, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public interface UserService {

	static User getUserDetails(String userId) {
		return new UserImpl(userId);
		
	}

	static Double getCreditRating(User user) {
		return 1000.00;
		
	}

}
