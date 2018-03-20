/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda;

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
public class Quote {

	private final String shopName;
	private final double price;
	private final Discount.Code discountCode ;
	/**
	 * 
	 */
	public Quote(String shopName, double price, Discount.Code code) {
		this.shopName = shopName ;
		this.price = price;
		this.discountCode = code;
	}
	
	public static Quote parse(String s) {
		String[] split = s.split(":");
		String shopName = split[0] ;
		double price = Double.parseDouble(split[1]);
		Discount.Code discountCode = Discount.Code.valueOf(split[2]);
		return new Quote(shopName , price , discountCode);
	}

	/**
	 * @return the shopName
	 */
	public final String getShopName() {
		return shopName;
	}

	/**
	 * @return the price
	 */
	public final double getPrice() {
		return price;
	}

	/**
	 * @return the discountCode
	 */
	public final Discount.Code getDiscountCode() {
		return discountCode;
	}
}
