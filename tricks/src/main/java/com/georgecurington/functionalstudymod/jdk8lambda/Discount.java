/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda;

import com.georgecurington.functionalstudymod.jdk8lambda.java8inaction.Utils;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 11, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class Discount {

	public enum Code {
		NONE(0)
		,SILVER(5)
		,GOLD(20)
		,PLATINUM(15)
		,DIAMOND(20);
		private final int percentage;
		Code(int percentage) {
			this.percentage = percentage;
		}
	}
	/**
	 * 
	 */
	public static String applyDiscount ( Quote quote ){
		return quote.getShopName() + " price is " + 
	Discount.apply(quote.getPrice(), quote.getDiscountCode());
	}
	
	private static double apply(double price , Code code) {
		/** discount is acting as a service, the delay is simulated **/
		Utils.delay();
		
		return Double.parseDouble(String.format("%.2f",price * (100 - code.percentage) / 100));
	}
	public Discount() {
		// TODO Auto-generated constructor stub
	}

}
