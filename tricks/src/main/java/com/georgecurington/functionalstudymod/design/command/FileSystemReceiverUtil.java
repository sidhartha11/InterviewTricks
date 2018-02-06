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
public class FileSystemReceiverUtil {

	
	/**
	 * 
	 */
	public FileSystemReceiverUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static FileSystemReceiver getUnderlyingFileSystem(){
		String osName = System.getProperty("os.name");
		System.out.println("Underlying OS is:" + osName);
		if (osName.contains("Windows")){
			return new WindowsFileSystemReceiver();
		} else {
			return new UnixFileSystemReceiver();
		}
		
	}

}
