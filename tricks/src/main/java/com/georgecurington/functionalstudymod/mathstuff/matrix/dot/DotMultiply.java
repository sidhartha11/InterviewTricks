/**
 * 
 */
package com.georgecurington.functionalstudymod.mathstuff.matrix.dot;

import java.util.Arrays;

/**
 * <pre>
 * <p><b>INTERVIEW TRICKS</b></p>
 * </pre>
 * <pre> 
 * simple example of matrix dot multiplication
 * Something that has always confused me. Hopefully I can find 
 * time to study this further. I am already wondering how I 
 * might create some type of multi threaded approach to multiplyiing 
 * Matrices together.
 * </pre>
 * <p>
 * ================================================
 * </p>
 * <br>
 * 
 * @author George Curington
 * @version 1.0.0
 * @since Jan 27, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class DotMultiply {


	/**
	 * 
	 */
	public DotMultiply() {
		doDotMultiplicationExample();
	}

	private int[][] doDotMultiplicationExample() {
		int[][] myMatrixA = { 
				{ 3, 5, 7 }, 
				{ 9, 17, 12 }, 
				{ 32, 21, 5 } 
				};
		int[][] myMatrixB = { 
				{ 1, 3, 5 }, 
				{ 7, 9, 11 }, 
				{ 13, 15, 17 } 
				};
		
		// counters
		int i, j, k, m, n;
		// rows and columns for each matrix
		int rowsA = myMatrixA.length;
		int colsA = myMatrixA[0].length;
		int rowsB = myMatrixB.length;
		int colsB = myMatrixB[0].length;
		// new matrix to hold result
		int[][] myMatrixC = new int[rowsA][colsB];
		// start across rows of A
		for (i = 0; i < rowsA; i++) { /** traverse each row of myMatrixA **/
			// work across cols of B
			for (j = 0; j < colsB; j++) { /** traverse each column of myMatrixB **/
				// now complete the addition and multiplication
				for (k = 0; k < colsA; k++) { /** traverse each column of myMatricA **/
					/**
					 * This section will create the resulting Matrix
					 * index i is varying with all the rows of myMatrixA
					 * index j is varying with all the columns of myMatrixB
					 * Geeeesh hat a HEADACHE 
					 */
					myMatrixC[i][j] += myMatrixA[i][k] * myMatrixB[k][j];
				}
			}
		}
		
		return myMatrixC;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DotMultiply dot = new DotMultiply();
		int[][] dotProduct = dot.doDotMultiplicationExample();
		for ( int[] columns : dotProduct ){
			System.out.println(Arrays.toString(columns));
		}
		
		

	}

}
