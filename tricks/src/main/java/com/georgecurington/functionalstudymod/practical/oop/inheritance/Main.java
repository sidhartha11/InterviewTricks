/**
 * 
 */
package com.georgecurington.functionalstudymod.practical.oop.inheritance;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

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
public class Main {

	/**
	 * 
	 */
	public Main() {
		Animal dog = new anotherDog();
		Animal cow = new Cow();
		Animal duck = new Duck();
		Utility.p("dog greeting:" + dog.getGreeting());
		Utility.p("cow greeting:" + cow.getGreeting());
		Utility.p("duck greeting:" + duck.getGreeting());
		Utility.p("dog is canivorous:" + dog.getIsCarnivorous());
		Utility.p("dog is a mammal:" + dog.getIsMammal());
		Utility.p("cow is canivorous:" + cow.getIsCarnivorous());
		Utility.p("cow is a mammal:" + cow.getIsMammal());
		Utility.p("duck is canivorous:" + duck.getIsCarnivorous());
		Utility.p("duck is a mammal:" + duck.getIsMammal());
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();

	}

}
