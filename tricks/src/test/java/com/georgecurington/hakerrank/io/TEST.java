/**
 * 
 */
package com.georgecurington.hakerrank.io;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Mar 2, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
abstract interface bob {
	
}

class A {
//	void overl//oad();
	static String str = "STRING_IN_A";
	
}

class B extends A {
//	void overload();
	static String str = "STRING_IN_B";
}
public final class TEST implements Cloneable {

	/**
	 * 
	 */
	public TEST() {
		// TODO Auto-generated constructor stub
	}
static void method() throws Exception{
	Class<?> cl  = null;
	Object o = null;
//	Collection c = o;
	Utility.p("hllo");
}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 3 ; 
		switch ( i ) { 
		default:
			break;
		case 0:
			break;
		}
		B b = new B();
		Utility.p(b.str);
//		InputStreamReader ir = new BufferedReader(new FileReader(new File("file.txt")));

	}
	private static void testTwo() {
	List<String> a = java.util.Arrays.asList("a" , "b");
	for ( String s : a){
		Utility.p(s);
	}
	}
	/**
	 * 
	 */
	private static void method3() {
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				Utility.p("b");
				
			}
			
		});
		System.out.println("hello");
		t.start();
		t.start();
//		List <Object> b = new ArrayList<String>();
		//System.exit(1);
	}
	/**
	 * 
	 */
	private static void testOne() {
		try {
			System.out.println("hello");
			method();
			//System.exit(1);
			//throw new RuntimeException();
		} catch (Throwable e) {
			System.out.println("hello");
		} finally {
			System.out.println("finall");
		}
	}
	
	 private  class bob {
		
	}
}
