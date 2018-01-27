package com.georgecurington.functionalstudymod.jdk8lambda.masteringlambdas.chapter5;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Spliterator;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class LineSpliteratorTest {
	private static final String STARTDIR = "C:\\githubstuff\\javaprojs\\studystuff\\coreexamples\\"
			+ "simple-parent\\algorithmMod\\src\\main\\java\\org\\"
			+ "geo\\studies\\products\\sorting\\external\\threadmodule\\";
	private static final String MIXDECIMALNUMBERS_1 = "\\[-+\\]?\\[0-9\\]*\\.?\\[0-9\\]+";
	private static final String MIXDECIMALNUMBERS = "[-+]?[0-9]*\\.?[0-9]+";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * <pre>
	 * Simple version of a recursive grep that will walk the 
	 * directory and match files containing the input regular expression.
	 * Displaying the filename and the number of time the item occurred in
	 * the file. 
	 * </pre> 
	 * 
	 */
	@Test
	public void simpleGrep(){
			Path start = new File(STARTDIR).toPath();
			Pattern pattern = Pattern.compile(MIXDECIMALNUMBERS);
			PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{java,txt}");

			try
			/** return a lazily populated Stream of Path objects **/
			(Stream<Path> pathStream = Files.walk(start)) {

				pathStream

						.filter(Files::isRegularFile)

						.filter(pathMatcher::matches)

						.map(path -> {
							try {

								long matchCount = Files.readAllLines(path).stream()
										.filter(line -> pattern.matcher(line).find()).count();
								return matchCount == 0 ? "" : path + ": " + matchCount;
							} catch (IOException e) {
								return "";
							}
						}).filter(line -> !line.isEmpty()).forEach(System.out::println);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
	/**
	 * <pre>
	 * A slighly more interesting example of Grep using nio and a 
	 * Memory mapped buffer to hold the contents of the file. This 
	 * version should be applied to HUGE files to be effective. This 
	 * version displays the offset into the file where the match occurred.
	 * Note that this version is using a custom Spliterator that uses
	 * parallel fork/join processing.
	 * The code below only processes one file. Note that FileChannel.open 
	 * does not work on directories in Windows. The next version will use
	 * part of simpleGrep to feed files to the Spliterator.
	 * </pre>
	 */
	@Test
	public void testLineSpliterator() {
		Path start = new File(STARTDIR + "QueueDuplicator.java").toPath();
		Pattern pattern = Pattern.compile(MIXDECIMALNUMBERS);
		PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{java,txt}");
	
		/** now the interesting nio part **/
		try (FileChannel fc = FileChannel.open(start)) {
			
			MappedByteBuffer bB = 
					fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
			Spliterator<DispLine> ls = 
					new LineSpliterator(bB,0,bB.limit() - 1,start);
//			        new LineSpliterator(bB,0,bB.limit() ,start);
			
			StreamSupport.stream(ls, true)
			.filter(dl -> pattern.matcher(dl.line).find())
			.forEachOrdered(System.out::println);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLineSpliteratorFull(){
			Path start = new File(STARTDIR).toPath();
			Pattern pattern = Pattern.compile(MIXDECIMALNUMBERS);
			PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{java,txt}");

			try
			/** return a lazily populated Stream of Path objects **/
			(Stream<Path> pathStream = Files.walk(start)) {

				pathStream

						.filter(Files::isRegularFile)

						.filter(pathMatcher::matches)

						.forEach(path -> {
							System.out.println("processing a file:" + path.getFileName());
							/** now the interesting nio part **/
							try (FileChannel fc = FileChannel.open(path)) {
								
								MappedByteBuffer bB = 
										fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
								Spliterator<DispLine> ls = 
										new LineSpliterator(bB,0,bB.limit() - 1);
								
								StreamSupport.stream(ls, true)
								.filter(dl -> pattern.matcher(dl.line).find())
								.forEachOrdered(lin -> {
									
								});
								
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							

						});
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

}
