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
public class UserImpl implements User {

	private final  String userId;

	/**
	 * 
	 */
	public UserImpl(String userId) {
		this.userId = userId ; 
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserImpl [userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}
