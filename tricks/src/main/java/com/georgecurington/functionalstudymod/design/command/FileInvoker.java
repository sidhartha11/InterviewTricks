/**
 * 
 */
package com.georgecurington.functionalstudymod.design.command;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 5, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class FileInvoker {
	
	private final Command command;
	
	/**
	 * 
	 */
	public FileInvoker(Command c) {
		this.command = c;
	}
	
	public void execute() {
		this.command.execute();
	}

}
