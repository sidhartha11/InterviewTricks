/**
 * 
 */
package com.georgecurington.functionalstudymod.niov2;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * review of information presented in chapter 1 or Pro NIOV2
 * </pre>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 18, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class Chapter1 {
	public final String base = System.getProperty("user.dir");
	/**
	 * 
	 */
	public Chapter1() {
		doConvertPathToOther();
	}

	private void doConvertPathToOther() {
		Path path = Paths.get("/rafaelnadal/tournaments/2009","BNP.txt");
		/** convert a Path to a String **/
		String path_to_string = path.toString();
		Utility.p("path.toString()=" + path_to_string);
		
		/** Convert a Path to a URI **/
		Utility.p("converting path to URI = " + path.toUri());
		
		/** Convert a Path to a Absolute Path **/
		Path path_to_absolute_path = path.toAbsolutePath();
		Utility.p("path.toAbsolutePath()=" + path_to_absolute_path);
		
		/** convert to real path **/
		try {
			Path real_path = path.toRealPath();
			
			Utility.p("path.toRealPath()=" + real_path);
		} catch ( IOException e) {
			Utility.p("error = " + e.getMessage());
		}
		 
		/** convert Path to File **/
		File path_to_file = path.toFile();
		Utility.p("path_to_file=" + path_to_file.toString());
		/** convert File to Path */
		Path file_to_path = path_to_file.toPath();
		Utility.p("file_to_path = " + file_to_path.toString());
		
		/** combining two paths **/
		Path base = Paths.get("C:/rafaelnadal/tournaments/2009");
		
		/** resolve BNP.txt file **/
		Path path_1 = base.resolve("BNP.txt");
		Utility.p("path resolve=" + path_1.toString());
		
		/** try to resolve nonexistant file **/
		Path path_2 = base.resolve("AEGON.txt");
		Utility.p("path resolve nonexistent=" + path_2.toString());
		
		/** resolve sibling **/
		base = Paths.get("C:/rafaelnadal/tournaments/2009/BNP.txt");
		Utility.p("base=" + base.toString());
		/** resolve sibling AEGON.txt file **/
		path = base.resolveSibling("AEGON.txt");
		Utility.p("using resolveSibling=" + path.toString());
		
		/** constructing a path between two locations **/
		Path path01 = Paths.get("BNP.txt");
		Path path02 = Paths.get("AEGON.txt");
		Path path01_to_path02 = path01.relativize(path02);
		Utility.p("path01 to path02=" + path01_to_path02.toString());
		
		Path path02_to_path01 = path02.relativize(path01);
		Utility.p(path02_to_path01);
		
		path01 = Paths.get("/tournaments/2009/BNP.txt");
		path02 = Paths.get("/tournaments/2011");
		Utility.p("path01=" + path01.toString());
		path01_to_path02 = path01.relativize(path02);
		Utility.p("path01_to_path02=" + path01_to_path02.toString());
		
		path02_to_path01 = path02.relativize(path01);
		Utility.p("path02_to_path01=" + path02_to_path01.toString());
		
		/** using equals **/
		
		path01 = 
				Paths.get("/rafaelnadal/tournaments/2009/BNP.txt");
		path02 = 
				Paths.get("C:/rafaelnadal/tournaments/2009/BNP.txt");
		Utility.p("are they equal:" + path01.equals(path02));
		try {
			Utility.p("is same file:" + Files.isSameFile(path01, path02));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/** compareTo **/
		int compare = path01.compareTo(path02);
		Utility.p("path01.compareTo(path02)=" + compare );
		
		/** startsWith and endsWith **/
		boolean sw = path01.startsWith("/rafaelnadal/tournaments");
		boolean ew = path01.endsWith("BNP.txt");
		Utility.p("path01 = " + path01.toString());
		Utility.p("path02 = " + path02.toString());
		Utility.p("path01.startsWith(\"/rafaelnadal/tournaments\")=" + sw);
		Utility.p("path01.endsWith(\"BNP.txt\")=" + ew);
		path = Paths.get("C:", "rafaelnadal/tournaments/2009");
		
		for ( Path name : path ){
			Utility.p(name);
		}
	}

	private void doInformationAboutPaths() {
		Path path = Paths.get("C:", "rafaelnadal/tournaments/2009","BNP.txt");
		
		Utility.p("path.toString():" + path.toString());
		/** get Path File/directory Name **/
		Utility.p("The file/directory indicated by the path:" + 
		path.getFileName());
		
		/** get the root **/
		Utility.p("the root of the path (path.getRoot())= " + 
		path.getRoot());
		
		/** get the parent as opposed to the root element **/
		Utility.p("The parent component is " + path.getParent());
		
		/**
		 * Get the number of path components and display each one
		 */
		for ( int i = 0 ; i < path.getNameCount(); i++	){
			Utility.p(i + ":" + path.getName(i));
		}
		
		/** subpath of a path using a begin element position and end element **/
		
		Utility.p("path.subpath(0,3)=" + path.subpath(0, 3));
		Utility.p("path.getName(path.getnameCount()-1))=" + 
		path.getName(path.getNameCount()-1));
		Utility.p("path.getNameCount()= " +  path.getNameCount());
		Utility.p("all paths=" + path.subpath(0,path.getNameCount()));
		
	}
	private void doHomeDirectoryFromPaths() {
		Path path = Paths.get(System.getProperty("user.home"),"Downloads","Untitled");
		Utility.p("path=" + path.toString());
	}

	private void doDefaultFromPaths() {
		Path path = 
				FileSystems.getDefault().getPath("/rafaelnadal/tournaments/2009", "BNP.txt");
		Utility.p("path = " + path.toString());
		
		path = FileSystems.getDefault().getPath("/rafaelnadal/tournaments/2009/BNP.txt");
		Utility.p("path = " + path.toString());
		
		path = FileSystems.getDefault().getPath("rafaelnadal/tournaments/2009","BNP.txt");
		Utility.p("path = " + path.toString());
		
		path = FileSystems.getDefault().getPath
		("/rafaelnadal/tournaments/./2009/BNP.txt").normalize();
		Utility.p("path = " + path.toString());
		
	}

	private void doUriFromPaths() {
		String fileUri="file:///C:/githubstuff/javaprojs/studystuff/InterviewTricks/README.md";
		Utility.p("fileUri=" + fileUri);
		URI fileUriObject = URI.create(fileUri);
		Utility.p("URI OBJECT=" + fileUriObject.toString());
		Path path = Paths.get(fileUriObject);
		Utility.p("after URI.create=" + path.toString());
	}

	private void doRelativefilepaths() {
		/** locate the current working directory **/
//		System.getProperties().entrySet().stream().forEach((e) -> {
//			System.out.println("key=" + e.getKey() + ",value=" + e.getValue());
//		});
		/**
		 * Note the current folder is:
		 * C:\githubstuff\javaprojs\studystuff\InterviewTricks\tricks
		 */
		Path path = Paths.get("README.md");
		Utility.p(String.format("path=%s",path.toString()));
		
		/**
		 * base dir is
		 * C:\githubstuff\javaprojs\studystuff\InterviewTricks\tricks
		 * and inside InterviewTricks there is a README.md file
		 */
		path = Paths.get(
				"C:\\githubstuff\\javaprojs\\studystuff\\.\\InterviewTricks\\tricks\\..\\README.md");
		Utility.p(String.format("path=%s",path.toString()));
		
		Path normalizepath = Paths.get(
				"C:\\githubstuff\\javaprojs\\studystuff\\.\\InterviewTricks\\tricks\\..\\README.md").normalize();
		Utility.p(String.format("normalizepath=%s",normalizepath.toString()));
		
	}

	private void dofilepaths() {
		Utility.p("executing Paths.get(\"C:/books/pro-java-7-nio.2/README.md\")" );
		Path path = Paths.get("C:/books/pro-java-7-nio.2/README.md");
		Utility.p(String.format("path=%s",path.toString()));
		
		/** using chunks to define the file path **/
		path = Paths.get("C:", "books" , "pro-java-7-nio.2", "README.md");
		Utility.p(String.format("path=%s",path.toString()));
		
		path = Paths.get("/books/pro-java-7-nio.2/README.md");
		Utility.p(String.format("path=%s",path.toString()));

		/** locate the current working directory **/
		System.getProperties().entrySet().stream().forEach((e) -> {
			System.out.println("key=" + e.getKey() + ",value=" + e.getValue());
		});
		
		
	}

	public static void main(String[] args) {
		Chapter1 chapter1 = new Chapter1();
	}
}
