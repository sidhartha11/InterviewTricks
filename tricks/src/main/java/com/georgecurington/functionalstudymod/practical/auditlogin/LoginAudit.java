/**
 * 
 */
package com.georgecurington.functionalstudymod.practical.auditlogin;

import java.time.LocalDateTime;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 26, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class LoginAudit implements AuditQueue{
	private final String userId;
	private final String logmessage;
	private final LocalDateTime localDateTime;
	/**
	 * @param userId
	 * @param logmessage
	 * @param logtime
	 */
	public LoginAudit(String userId, String logmessage) {
		super();
		this.userId = userId;
		this.logmessage = logmessage;
		this.localDateTime = LocalDateTime.now();
	}
	
	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}
	@Override
	public String getLogmessage() {
		// TODO Auto-generated method stub
		return this.logmessage;
	}
	@Override
	public LocalDateTime getLogtime() {
		// TODO Auto-generated method stub
		return this.localDateTime;
	}

	/**
	 * 
	 */


}
