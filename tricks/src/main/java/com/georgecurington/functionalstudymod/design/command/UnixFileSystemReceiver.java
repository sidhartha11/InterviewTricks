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
public class UnixFileSystemReceiver implements FileSystemReceiver {

	/**
	 * 
	 */
	public UnixFileSystemReceiver() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.command.FileSystemReceiver#openFile()
	 */
	@Override
	public void openFile() {
		System.out.println("Opening file in unix OS");
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.command.FileSystemReceiver#writeFile()
	 */
	@Override
	public void writeFile() {
		System.out.println("Writing file in unix OS");

	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.command.FileSystemReceiver#closeFile()
	 */
	@Override
	public void closeFile() {
		System.out.println("Closing file in unix OS");

	}

}
