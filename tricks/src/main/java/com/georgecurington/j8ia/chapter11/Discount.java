/**
 * 
 */
package com.georgecurington.j8ia.chapter11;
import static com.georgecurington.j8ia.chapter11.Java8utils.*;
/**
 * @author Owner
 *
 */
public class Discount {

	/**
	 * 
	 * @author Owner
	 * TidBit JQ:
	 * Does the enclosing class have access to private members of a enum class 
	 * that it encloses? YES
	 *
	 */
	public enum Code {
		NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
		
		private final int percentage;
		
		Code(int percentage) {
			this.percentage = percentage;
		}
	}
	/**
	 * 
	 */
	public static String applyDiscount(Quote quote) {
		return quote.getShopName()  + " price is " + Discount.apply(quote.getPrice(), 
				quote.getDiscountCode());
	}
	
	private static double apply(double price, Code code) {
		delay();
		return format(price * (100 - code.percentage)/ 100 ); 
	}
	public Discount() {
	
	}

}
