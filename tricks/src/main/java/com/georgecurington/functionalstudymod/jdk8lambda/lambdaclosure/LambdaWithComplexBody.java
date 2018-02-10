/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.lambdaclosure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 9, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class LambdaWithComplexBody {

	/**
	 * 
	 */
	public LambdaWithComplexBody() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Function<File, Byte> firstByte = file -> {
			try ( InputStream is = new FileInputStream(file)) {
				return (byte)is.read();
			}catch ( IOException ioe ) {
				throw new RuntimeException("could not read " + file, ioe);
			}
		} ;
		
		for ( String filename : args ) {
			File file = new File(filename);
			int byte1 = firstByte.apply(file);
			System.out.println(filename + "\t=>\t" + byte1);
		}
	}
	
	}
