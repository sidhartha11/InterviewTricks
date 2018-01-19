/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * <pre>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 19, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * </pre>
 */
public interface Log {

	void log(String msg) throws InterruptedException;

	void start();

	void stop();

}
