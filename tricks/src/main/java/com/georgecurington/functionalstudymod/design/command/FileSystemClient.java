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
public class FileSystemClient {

	/**
	 * 
	 */
	public FileSystemClient() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileSystemReceiver fs = 
				FileSystemReceiverUtil.getUnderlyingFileSystem();
		
		// creating command and associating with receiver
		
		OpenFileCommand openFileCommand = new 
				OpenFileCommand(fs);
		FileInvoker file = new 
				FileInvoker(openFileCommand);
		file.execute();
		
		WriteFileCommand writeFileCommand = new 
				WriteFileCommand(fs);
		file = new FileInvoker(writeFileCommand);
		file.execute();
		
		CloseFileCommand closeFileCommand = new 
				CloseFileCommand(fs);
		file = new FileInvoker(closeFileCommand);
		file.execute();
		
	}

}
