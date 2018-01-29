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
public class Cow extends Animal {

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.oop.inheritance.Animal#getIsMammal()
	 */
	@Override
	protected boolean getIsMammal() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 
	 */
	public Cow() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.oop.inheritance.Animal#getGreeting()
	 */
	@Override
	protected String getGreeting() {
		return "Moo Freaking Moo";
	}

}
