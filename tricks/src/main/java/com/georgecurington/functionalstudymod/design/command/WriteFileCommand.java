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
public class WriteFileCommand implements Command {

	private final FileSystemReceiver fileSystem;
	/**
	 * 
	 */
	public WriteFileCommand(FileSystemReceiver fs) {
		this.fileSystem = fs;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.command.Command#execute()
	 */
	@Override
	public void execute() {
		this.fileSystem.writeFile();
	}

}
