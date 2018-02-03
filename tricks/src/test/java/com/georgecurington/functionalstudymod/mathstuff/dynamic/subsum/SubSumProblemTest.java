/**
 * 
 */
package com.georgecurington.functionalstudymod.mathstuff.dynamic.subsum;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.utilities.Pair;
import com.georgecurington.functionalstudymod.utilities.PairImpl;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 2, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class SubSumProblemTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.mathstuff.dynamic.subsum.SubSumProblem#SubSumProblem()}.
	 */
	@Test
	public void testSubSumProblem() {
		SubSumProblem subsum = new SubSumProblem();
		assertNotNull("cannot create instance", subsum);
	}
	
	@Test
	public void testGetMemoizationMatrix() {
		SubSumProblem subsum = new SubSumProblem();
		assertNotNull("cannot create instance", subsum);
		int solutionsize = 5;
		List<Integer> ravailableSet = new ArrayList<>();
		List<Pair<Integer, Integer>> availableSet = new ArrayList<>();
		ravailableSet.addAll(Arrays.asList(1, 3, 9, 2));
		ravailableSet.add(0, 0);
		
		/** note , sorting the elements producing a different matrix **/
		Collections.sort(ravailableSet);

		IntStream.range(0, ravailableSet.size()).forEach(p -> {
			availableSet.add(new PairImpl<Integer, Integer>(ravailableSet.get(p), p));
		});

		Utility.p(availableSet.toString());
		boolean[][] memoizationMatrix = new boolean[availableSet.size()][solutionsize + 1];
		boolean[][] mmatrix = subsum.getMemoizationMatrix(availableSet, solutionsize, memoizationMatrix);
	}
	
	@Test
	public void displayMemoizationMatrix(){
		
		SubSumProblem subsum = new SubSumProblem();
		assertNotNull("cannot create instance", subsum);
		int solutionsize = 6;
		List<Integer> ravailableSet = new ArrayList<>();
		List<Pair<Integer, Integer>> availableSet = new ArrayList<>();
		ravailableSet.addAll(Arrays.asList(1, 3, 5, 11));
		ravailableSet.add(0, 0);
		
		/** note , sorting the elements producing a different matrix **/
		Collections.sort(ravailableSet);

		IntStream.range(0, ravailableSet.size()).forEach(p -> {
			availableSet.add(new PairImpl<Integer, Integer>(ravailableSet.get(p), p));
		});

		// The availbleSet is initialized to this:
//		Utility.p(availableSet.toString());
		availableSet.stream().forEach(p -> {
			System.out.println(p);
		});
		Utility.p("solutionsize=" + solutionsize);
		boolean[][] memoizationMatrix = new boolean[availableSet.size()][solutionsize + 1];
		boolean[][] mmatrix = subsum.getMemoizationMatrix(availableSet, solutionsize, memoizationMatrix);
		System.out.print("         ");
		for (int i = 0 ; i <= solutionsize ; i++ ){
			System.out.print(Utility.centerNumber(5,i));
		}
		System.out.println();
		for (int i = 0; i < memoizationMatrix.length; i++) {
			
			/** print available set element **/
			System.out.print("[" + Utility.centerNumber(3,availableSet.get(i).getLeft()) + "," + Utility.centerNumber(3,availableSet.get(i).getRight()) + "]" );
			
			for ( int x = 0 ; x < memoizationMatrix[i].length; x++ ){
				System.out.print(Utility.convertBol(5,1,memoizationMatrix[i][x]));
			}
			System.out.println();
		}
	}
	
	@Test
	public void centerNumber() {
		SubSumProblem subsum = new SubSumProblem();
		assertNotNull("cannot create instance", subsum);
		String s=Utility.centerNumber(5,5);
		System.out.println("123456789");
		System.out.println("===" + s + "===");
	}

}
