/**
 * 
 */
package com.georgecurington.functionalstudymod.mathstuff.matrix.multiplication;

import java.util.Arrays;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * This class computes the dot product of two matrixes that 
 * are comformable to multiplication:
 * Number of rows in the first matrix = number columns in the 
 * second matrix. 
 * </pre>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 31, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class MatrixConformable {


	/**
	 * 
	 */
	public MatrixConformable() {
		doDotMultiplicationExample();
	}

	private int[][] doDotMultiplicationExample() {
		/** 3 rows **/
		int[][] myMatrixA = { 
				{ 3, 5, 7 }, 
				{ 9, 17, 12 }, 
				{ 32, 21, 5 } 
				};
		
		/** 3 columns **/
		int[][] myMatrixB = { 
				{ 1, 3, 5 }, 
				{ 7, 9, 11 }, 
				{ 13, 15, 17 } 
				};
		
		// counters
		int i, j, k, m, n;
		// rows and columns for each matrix
		/**
		 * The first dimension in java is the row length
		 */
		int rowsA = myMatrixA.length;
		/**
		 * The second dimension in java is the column length
		 */
		int colsA = myMatrixA[0].length;
		
		/**
		 * The first dimension is the row length in java
		 */
		int rowsB = myMatrixB.length;
		/**
		 * The second dimension is the column length
		 */
		int colsB = myMatrixB[0].length;
		
		/**
		 * The result matrix must be comformable to matrix
		 * multiplication. This means that
		 * 
		 * A[m,n] B[o,p]
		 *     |----|
		 *   |--------|
		 * C[m,p] = dimension of new C
		 * 
		 * This means that the number of columns of A must 
		 * equal the number of rows of B
		 * 
		 * Now, see here:
		 * C [i,j]   =  A[i,j] + B[i,j] for each row/col pair of A
		 * and B
		 * To calculate the result matrix C we need to perform
		 * a multiplication and addition of the rows of A against
		 * the columns of B like so:
		 * 
		 * 
		 * 
		 */
		int[][] myMatrixC = new int[rowsA][colsB];
		// start across rows of A
		for (i = 0; i < rowsA; i++) { /** traverse each row of myMatrixA **/
			// work across cols of B
			for (j = 0; j < colsB; j++) { /** traverse each column of myMatrixB **/
				// now complete the addition and multiplication
				for (k = 0; k < colsA; k++) { /** traverse each column of myMatricA **/
					/**
					 * 1. take every element in a row of MatrixA and multiply against 
					 * the corresponding element in a column of MatrixB 
					 * Hence the colunm in MatrixB remains constant while the row in MatrixA remains 
					 * constant. 
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
		MatrixConformable dot = new MatrixConformable();
		int[][] dotProduct = dot.doDotMultiplicationExample();
		for ( int[] columns : dotProduct ){
			System.out.println(Arrays.toString(columns));
		}
		
		

	}

}