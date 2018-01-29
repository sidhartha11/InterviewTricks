/**
 * 
 */
package com.georgecurington.functionalstudymod.practical.oop.inheritance;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 27, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public abstract class Animal {

	/**
	 * 
	 */
	public Animal() {
		// TODO Auto-generated constructor stub
	}
	
	protected boolean getIsMammal() {
		return false;
	}
	
	protected boolean getIsCarnivorous(){
		return false;
	}
	
	abstract protected String getGreeting();
	

}
