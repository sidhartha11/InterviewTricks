/**
 * 
 */
package com.georgecurington.functionalstudymod.interfaces;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Mar 18, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public interface InterfaceExampleV8 {

	public static String STATICVARIABLE = "A static variable";
	default void aDefaultMethod(String name) {
		 Utility.p("This is a default method in an interface:" + name);
	}
	
	static void aStaticMethod(String name)	{
		Utility.p("This is a static method in an interface:" + name);
	}
	
	public void anAbstractMethod(String name);
	
	public static void main(String[] args) {
		System.out.println("calling a main method in an Interface!");
	    aStaticMethod("what!!!");
	    InterfaceExampleV8 v8 = new InterfaceExampleV8(){

			@Override
			public void anAbstractMethod(String name) {
				System.out.println("calling the abstract method inside the interface:" + name);
				aDefaultMethod("Now This cannot Be!:" + name);
			}
	    	
	    };
	    System.out.println("calling interface methods");
	    v8.aDefaultMethod("Help!!!");
	    v8.anAbstractMethod("Help even more!!");
	}
}
