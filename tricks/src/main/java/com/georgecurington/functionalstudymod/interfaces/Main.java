/**
 * 
 */
package com.georgecurington.functionalstudymod.interfaces;

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
public class Main implements InterfaceExampleV8 {

	/**
	 * 
	 */
	public Main() {
		anAbstractMethod("calling overidden abstract method");
		InterfaceExampleV8.aStaticMethod("calling static method");
		aDefaultMethod("calling default method");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();

	}

	@Override
	public void anAbstractMethod(String name) {
		System.out.println("called abstract method:" + name);
	}
	

}
