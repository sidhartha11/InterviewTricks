/**
 * 
 */
package com.georgecurington.functionalstudymod.design.builder;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * <b> BUILDER PATTERN </b>
 * Example from Effective Java, 3rd Edition , Joshua Bloch
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 10, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class NutritionFacts {
	
	/** strange that Eclipse marks these as not being used when they are in the private
	 * constructor below.
	 */
	private final int servingSize;
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;
	
	/**
	 * This static instance class will be reposible for actually 
	 * building the immutable instance of NutritionFacts
	 */
	
	public static class Builder {
		// Required parameters
		private final int servingSize;
		private final int servings;
		
		// Optional parameters - initialized to default values 
		
		private int calories = 0 ; 
		private int fat      = 0 ;
		private int sodium   = 0 ; 
		private int carbohydrate = 0 ; 
		
		// expose a public constructor here to set up all the real NutritionFacts Object
		
		public Builder(int servingSize , int servings ) {
			this.servingSize = servingSize;
			this.servings = servings;
		}
		
		/** create a mutator method for every instance variable of NutritionFacts **/
		/** each mutator returns its Object via this so that calls can be connected **/
		
		public Builder calories(int val) {
			calories = val;
			return this;
		}
		
		public Builder fat(int val) {
			fat = val;
			return this;
		}
		
		public Builder sodium(int val) {
			sodium = val;
			return this;
		}
		
		public Builder carbohydrate(int val) {
			carbohydrate = val;
			return this;
		}
		
		/** expose a build method that creates the final NutritionFacts Object **/
		public NutritionFacts build() {
			return new NutritionFacts(this);
		}
	}

	/**
	 * 
	 */
	private NutritionFacts(Builder builder) {
		servingSize  = builder.servingSize;
		servings     = builder.servings;
		calories     = builder.calories;
		fat          = builder.fat;
		sodium       = builder.sodium;
		carbohydrate = builder.carbohydrate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("NutritionFacts [servingSize=");
		builder2.append(servingSize);
		builder2.append(", servings=");
		builder2.append(servings);
		builder2.append(", calories=");
		builder2.append(calories);
		builder2.append(", fat=");
		builder2.append(fat);
		builder2.append(", sodium=");
		builder2.append(sodium);
		builder2.append(", carbohydrate=");
		builder2.append(carbohydrate);
		builder2.append("]");
		return builder2.toString();
	}

	/**
	 * Example of using the Builder Pattern
	 * @param args
	 */
	public static void main(String[] args) {
		NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
				.calories(100).sodium(35).carbohydrate(27).build();
		
		/** lets printit **/
		Utility.p(cocaCola);
	}

}
