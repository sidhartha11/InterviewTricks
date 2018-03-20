/**
 * 
 */
package com.georgecurington.functionalstudymod.numerical;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author george
 *
 */
public class GAddTwoNumberSummingToNTest {

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
	 * Test method for
	 * {@link com.georgecurington.functionalstudymod.numerical.GAddTwoNumberSummingToN#getSolutionPairSetFromSortedArray(java.lang.Integer[], int)}.
	 */
	@Ignore
	public void testGetSolutionPairSetFromSortedArray() {
		GAddTwoNumberSummingToN gadd = new GAddTwoNumberSummingToN();
		assertNotNull("Cannot Instantiate", gadd);

		Integer[] input = { 1, 4, 5, 70, 1, 4};
		Integer[] input2 = { 3, 6, 3, 0, 4, 2, 2, 2, 2, -8, 4, 10, 1, 1, 88, 1, 98, 5, -10, 4, 4, -8, 30, -6, 12, 11,
				11 };

		Set<List<Integer>> set = gadd.getSolutionPairSetFromSortedArray(input, 5);
		System.out.println("input size:" + input.length);
		System.out.println(set.toString());
		assertEquals("[[-1, 6]]", set.toString());
	}
	@Test
	public void testGetSolutionPairSetFromSortedArray2() {
		GAddTwoNumberSummingToN gadd = new GAddTwoNumberSummingToN();
		assertNotNull("Cannot Instantiate", gadd);

		Integer[] input = { 1, 4, 5, 70, 1, 4};
		Integer[] input2 = { 3, 6, 3, 0, 4, 2, 2, 2, 2, -8, 4, 10, 1, 1, 88, 1, 98, 5, -10, 4, 4, -8, 30, -6, 12, 11,
				11 };

		gadd.printPairsUsingSet(input2, 2);
		System.out.println("input size:" + input.length);

	}
	@Ignore
	public void SimplifyingExpression() {

		System.out.println("Enter an Expression :: ");
		// String exp = scn.nextLine();
		String exp = "a-(b-((c+d)+(e-f)+g)";
		String exp2 = "-x + (f) +  (a + d)  * -((b + (z + e) +  ( c - d)))";
		System.out.println(exp2);
		String simplifiedexp = simplyfingexp(exp2);
		System.out.println(simplifiedexp);
	}
	
	@Ignore
	public void Simplify() {

		// String exp = scn.nextLine();
		String exp = "a-(b-((c+d)+(e-f)+g)";
		String exp2 = "-x + (a + d)  * -((b + (z + e) +  ( c - d)))";
		System.out.println(exp2);
		String acc;
		String simplifiedexp = expression(exp2,"");
		System.out.println(simplifiedexp);
	}

	public static String simplyfingexp(String exp) {
		String sExp = null;
		int first = exp.lastIndexOf('(');
		int last = exp.indexOf(')', first);
		if ((first != -1 && last == -1) || (first == -1 && last != -1)) {
			return "Invalid expression";
		}
		if (first != -1 && last != -1) {
			String firstPart = exp.substring(0, first);
			String midPart = null;
			if (first == 0) {
				midPart = "+" + exp.substring(first, last + 1);
			} else {
				midPart = exp.substring(first - 1, last + 1);
			}
			String lastPart = exp.substring(last + 1, exp.length());
			String midSimplifidePart = null;
			Character sign = midPart.charAt(0);
			if (sign == '-') {
				String middle = midPart.substring(2, midPart.length() - 1);
				midSimplifidePart = changeNegativeAndPositiveSign(middle);
				if (midSimplifidePart.charAt(0) == '-' || midSimplifidePart.charAt(0) == '+') {
					firstPart = firstPart.substring(0, firstPart.length() - 1);
				}
			} else {
				midSimplifidePart = midPart.substring(2, midPart.length() - 1);
				if (midSimplifidePart.charAt(0) == '-' || midSimplifidePart.charAt(0) == '+') {
					firstPart = firstPart.substring(0, firstPart.length() - 1);
				}
			}
			String finalString = firstPart + midSimplifidePart + lastPart;
			sExp = simplyfingexp(finalString);
		} else {
			sExp = exp;
		}
		return sExp;
	}

	public static String changeNegativeAndPositiveSign(String middle) {
		char[] midExpCharArray = middle.toCharArray();
		for (int i = 0; i < midExpCharArray.length; i++) {
			if (midExpCharArray[i] == '-') {
				midExpCharArray[i] = '+';
			} else if (midExpCharArray[i] == '+') {
				midExpCharArray[i] = '-';
			}
		}
		return new String(midExpCharArray);
	}

	public String  expression(String  expr, String acc) {
		if (expr == null || expr.length() == 0 ) {
			return acc;
		} else if ( expr.charAt(0) == '(' || expr.charAt(0) == ')') {
			return expression(expr.substring(1), acc);
		} else 
			return expression(expr.substring(1), acc + expr.charAt(0));
	}
}
