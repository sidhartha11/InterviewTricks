/**
 * 
 */
package com.georgecurington.hakerrank.io;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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
 * @author George Curington
 * @version 1.0.0
 * @since Feb 26, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class HankerRankTest {

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

	@Ignore
	public void test() {
		int INITIAL = 10_000;
		List<String> list = new ArrayList<>(INITIAL);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			int n = 0, c = 0;
			String str = null;
			while ((str = reader.readLine()) != null) {
				list.add((++n) + " " + str);
			}
			list.stream().forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getSmallestAndLargest(String s, int k) {
		if ( k <= 0 ) {
			throw new IllegalAccessError("k is invalid:" + k);
		} else if ( s == null || s.equals("")) {
			throw new IllegalAccessError("string is invalid:" + s);
		} else if ( s.length() < k ) {
			throw new IllegalAccessError("string is too small:" + s.length());
		} 
		
		boolean debug=false;

        if ( s.length() == k) {
			return s + "\n" + s;
		}
		List<String> l = new ArrayList<>(10_000);
		StringBuilder sb = new StringBuilder(s);
		
		
		int i = sb.length();
		int x = 0;
		Utility.p(sb.toString());
		while ( i >= k ){

			l.add(sb.substring(x, k+x));
			x++;
			i--;
		}
		Collections.sort(l);
		l.stream().forEach(System.out::println);
		return l.get(0) + "\n" + l.get(l.size()-1);
	}
	public static String OgetSmallestAndLargest(String s, int k) {
		if ( k <= 0 ) {
			throw new IllegalAccessError("k is invalid:" + k);
		} else if ( s == null || s.equals("")) {
			throw new IllegalAccessError("string is invalid:" + s);
		} else if ( s.length() < k ) {
			throw new IllegalAccessError("string is too small:" + s.length());
		} 
		

        if ( s.length() == k) {
			return s + "\n" + s;
		}
		List<String> l = new ArrayList<>(10_000);
		StringBuilder sb = new StringBuilder(s);
		
		
		int i = sb.length();
		int x = 0;
		Utility.p(sb.toString());
		while ( i >= k ){
			Utility.p("i = " + i );
			Utility.p("x = " + x);
			String str = sb.substring(x, k+x);
			Utility.p(str);
			l.add(str);
			x++;
			i--;
		}
		Collections.sort(l);
//	    l.stream().forEach(System.out::println);
		// Complete the function
		// 'smallest' must be the lexicographically smallest substring of length
		// 'k'
		// 'largest' must be the lexicographically largest substring of length
		// 'k'
		String r = l.get(0) + "\n" + l.get(l.size()-1);
		System.out.println("dumpin");
		return r;
	}
	@Ignore
	public void testSmallestAndLargest() {
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		int k = scan.nextInt();
		scan.close();

		System.out.println(getSmallestAndLargest(s, k));
	}
	@Ignore
	public void testStuff () {
		try {
			System.out.println("hello");
//			System.exit(1);
		} catch (Exception e) {
			System.out.println("hello");
		} finally {
			System.out.println("finall");
		}
	}
	@Test
	public void testArrayCovariance(){
		String[] a1 = { "abc" };
		Object[] a2 = a1;
		a2[0] = new Integer(17);
		String s = a1[0];
	}
}
