package com.georgecurington.functionalstudymod.niov2;

/**
 * 
 */

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;

import static java.nio.file.LinkOption.*;
import java.nio.file.attribute.FileTime;
import java.util.Set;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 20, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class Chapter2 {
	private static String FILEPATH = "C:/rafaelnadal/tournaments/2009";
	private static String FILENAME= "BNP.txt";
	/**
	 * 
	 */
	public Chapter2() {
		filestores_1();
	}

	private void filestores_1() {
		FileSystem fs = FileSystems.getDefault();
		
		for ( FileStore store : fs.getFileStores()){
			try {
				Utility.p("configuration info for " + store.toString());
			    long total_space = store.getTotalSpace()/1024;
			    long total_inall = store.getTotalSpace();
			    
			    long used_space = 
			    		(store.getTotalSpace() - store.getUnallocatedSpace())/1024;
			    
			    long available_space = 
			    		store.getUsableSpace() / 1024 ;
			    
			    boolean is_read_only = store.isReadOnly();
			    
			    Utility.p("total inall = "+ total_inall);
			    Utility.p("total space = "+ total_space);
			    Utility.p("used space  = " + used_space);
			    Utility.p("available space = " + available_space);
			    Utility.p("Is read only ? " + is_read_only);
			} catch ( IOException e ) {
				Utility.p("err:" + e);
			}
		}
		
	}

	private void simpleattributes_1() {
		DosFileAttributes attr = null ; 

		Path path = 
				Paths.get(FILEPATH, FILENAME);
		try { 
			attr = Files.readAttributes(path,DosFileAttributes.class);
		} catch ( IOException e){
			Utility.p("err=" + e);
		}
		
		Utility.p("Is read only ? " + 
		attr.isReadOnly());
		
		Utility.p("Is Hidden ? " + 
		attr.isHidden());
		
		Utility.p("Is archive ? " + 
		attr.isArchive());
		
		Utility.p("Is system ? " + 
		attr.isSystem());
		
	}

	private void doch2_5() {
		Path path = Paths.get("C:/rafaelnadal/tournaments/2009","TEST.txt");
		long time = System.currentTimeMillis();
		FileTime fileTime = FileTime.fromMillis(time);
		
		try {
			Files.setAttribute(path, "basic:lastModifiedTime", fileTime, NOFOLLOW_LINKS);
			Files.setAttribute(path, "basic:creationTime", fileTime, NOFOLLOW_LINKS);
			Files.setAttribute(path, "basic:lastAccessTime", fileTime, NOFOLLOW_LINKS);
		} catch ( IOException e) {
			Utility.p("err=" + e);
		}
		
		try {
			FileTime lastModifiedTime = 
					(FileTime)Files.getAttribute(path
							,"basic:lastModifiedTime"
							,NOFOLLOW_LINKS);
			FileTime creationTime = 
					(FileTime)Files.getAttribute(path
							,"basic:creationTime"
							,NOFOLLOW_LINKS);
			FileTime lastAccessTime = 
					(FileTime)Files.getAttribute(path
							,"basic:lastAccessTime"
							,NOFOLLOW_LINKS);
			Utility.p("New last modified time: " + 
					lastModifiedTime );
			Utility.p("New creation time: " + 
					creationTime);
			Utility.p("New last access time: "  + 
					lastAccessTime ) ; 
		} catch ( IOException e) {
			Utility.p("err:" + e);
		}
		
	}

	private void doch2_4(){
	Path path = Paths.get("C:/rafaelnadal/tournaments/2009","TEST.txt");
	long time = System.currentTimeMillis();
	FileTime fileTime = FileTime.fromMillis(time);
	try { 
	FileTime ft = Files.getLastModifiedTime(path);
	Utility.p("before=" + ft.toMillis());
	Files.setLastModifiedTime(path,fileTime);
	ft = Files.getLastModifiedTime(path);
	Utility.p("after=" + ft.toMillis());
	} catch ( Exception e ) {
	Utility.p("err:" + e);
	
	}
	}
	private void doch2_3() {
		BasicFileAttributes attr = null;
		Path path = Paths.get("C:/rafaelnadal/tournaments/2009","BNP.txt");
		
		try { 
			attr = Files.readAttributes(path,BasicFileAttributes.class);
		} catch ( IOException e){
			Utility.p("err:" + e);
		}
		
		Utility.p("File size:" + attr.size());
		Utility.p("File creation time: " + attr.creationTime());
		Utility.p("File was last accessed at: " + attr.lastAccessTime());
		Utility.p("File was last modified at: " + attr.lastModifiedTime());
		Utility.p("Is regular file? " + attr.isRegularFile());
		Utility.p("Is symbolic link? " + attr.isSymbolicLink());
		Utility.p("Is other? " + attr.isOther());
		
		try { 
			long size = (Long)Files.getAttribute(path, "basic:size", NOFOLLOW_LINKS);
		    Utility.p("file size = " + size);
		}catch ( Exception e ) {
			Utility.p("err:" + e);
		}
	}

	private void doch2_2() {
		FileSystem fs = FileSystems.getDefault();
		
		/* check all filesstores available  for basic view **/
		for ( FileStore store : fs.getFileStores()){
			
			Utility.p(store + " supports? , " + store.supportsFileAttributeView(BasicFileAttributeView.class));
		}
		
		Path path = Paths.get("C:/rafaelnadal/tournaments/2009","BNP.txt");
	    try { 
	    	FileStore store = Files.getFileStore(path);
	    	boolean supported = 
	    			store.supportsFileAttributeView("basic");
	    	Utility.p(store.name() + " ---" + supported);
	    } catch ( IOException e){
	    	Utility.p(e);
	    }
	}

	private void doch2_1() {
		/** show file views **/
		/** get the default supported file system **/
		FileSystem fs = FileSystems.getDefault();
		
		Set<String> views = fs.supportedFileAttributeViews();
		
		for ( String view : views ) {
			Utility.p(view.toString());
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter2 chapter2 = new Chapter2();

	}

}
