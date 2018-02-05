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
	protected boolean isMammal;
	protected boolean isCarnivorous;
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

class Dog extends Animal {

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.oop.inheritance.Animal#getIsMammal()
	 */
	@Override
	protected boolean getIsMammal() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.oop.inheritance.Animal#getIsCarnivorous()
	 */
	@Override
	protected boolean getIsCarnivorous() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 
	 */
	public Dog() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.oop.inheritance.Animal#getGreeting()
	 */
	@Override
	protected String getGreeting() {
		// TODO Auto-generated method stub
		return "bow Freaking wow";
	}

}
