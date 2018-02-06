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
public class WindowsFileSystemReceiver implements FileSystemReceiver {

	/**
	 * 
	 */
	public WindowsFileSystemReceiver() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.command.FileSystemReceiver#openFile()
	 */
	@Override
	public void openFile() {
		System.out.println("Opening file in Windows");
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.command.FileSystemReceiver#writeFile()
	 */
	@Override
	public void writeFile() {
		System.out.println("Opening file in Windows");
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.command.FileSystemReceiver#closeFile()
	 */
	@Override
	public void closeFile() {
		System.out.println("Closing file in Windows");
	}

}
