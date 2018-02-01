/**
 * 
 */
package com.georgecurington.functionalstudymod.mathstuff.matrix.multiplication;

import java.util.Arrays;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre>
 * <p><b>INTERVIEW TRICKS</b></p>
 * </pre>
 * <p>
 * ================================================
 * </p>
 * <br>
 * 
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * <pre>
 * Two arrays are comformable for multiplication if the following rule
 * holds:
 * a[p,q]
 * b[r,s] 
 * where the number of columns of a are equal to the number of rows of b
 * a[m,n]         b[n,p]
 *   | |            | |
 *   | -------------- |
 *   |----------------|
 *   so 
 *   c = a x b means 
 *   c[m,p] or the number of rows in a and the number of columns in b
 *   
 * </pre>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 1, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * 
 */
public class MatrixConformable {
	/**
	 * two arrays a,b are conformable for multiplication if the number of
	 * columns in a are equal to the number of rows in b.
	 */

	private static final boolean DEBUG = false;

	/**
	 * a = p rows by 3 columns
	 */
	int[][] a = { { 2, 3, 4 }, { 5, 6, 7 }, { 8, 9, 10 } };

	/**
	 * b = 3 rows by q columns
	 */
	int[][] b = { { 2, 3, 10, 20 }, { 5, 6, 10, 20 }, { 8, 9, 10, 20 } };

	/**
	 * The result array will have to be of size: new [p,q]
	 */

	public MatrixConformable() {
		try {
			int c[][] = matrixDotProduct(a, b);
			for (int i = 0; i < c.length; i++) {
				Utility.p(Arrays.toString(c[i]));
			}

		} catch (IncompatibleScalers e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int[][] matrixDotProduct(int[][] a, int[][] b) throws IncompatibleScalers {

		int rowsA = a.length;
		int colsA = a[0].length;
		int rowsB = b.length;
		int colsB = b[0].length;
		int colsBaux[] = new int[rowsB];
		if (colsA != rowsB) {
			throw new IncompatibleScalers("columns and rows incompatible");
		}

		int[][] c = new int[rowsA][colsB];
		Utility.p("c[" + rowsA + ":" + colsB + "]");

		/**
		 * for every row in column a, apply each row to all the columns of b.
		 * This will create accomodating row in matrix c c(11) = a(row1) x
		 * b(col1)
		 */
		for (int i = 0; i < a.length; i++) {
			/** get a row from matrix a **/
			int[] rowScaler = getRow(i, a);

			/** multiply that row against each column of matrix b **/
			/** each entry will represent an element in matrix c **/
			for (int j = 0; j < b[0].length; j++) {

				/** get a column from matrix b **/
				int[] colScaler = getCol(colsBaux, j, b);

				/** generate the dot product of the row and column data **/
				c[i][j] = dotProductOfVectors(rowScaler, colScaler);

			}
		}
		return c;
	}

	public int dotProductOfVectors(int[] column, int[] row) throws IncompatibleScalers {
		if (column.length != row.length) {
			throw new IncompatibleScalers("row and column Lenght must be equal");
		}
		int total = 0;
		for (int i = 0; i < column.length; i++) {
			total += (column[i] * row[i]);
		}
		return total;
	}

	public int[] getRow(int rowId, int[][] matrix) throws IncompatibleScalers {

		if (rowId > matrix.length || rowId < 0) {
			throw new IncompatibleScalers("row id not valid");
		}
		return matrix[rowId];
	}

	public int[] getCol(int[] colsBaux, int colId, int[][] matrix) throws IncompatibleScalers {

		if (colId > matrix[0].length || colId < 0) {
			throw new IncompatibleScalers("col id not valid");
		}

		/** scan thru the matrix, selecting the target column **/
		for (int i = 0; i < matrix.length; i++) {
			colsBaux[i] = matrix[i][colId];
		}
		return colsBaux;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MatrixConformable matrix = new MatrixConformable();
	}

}
