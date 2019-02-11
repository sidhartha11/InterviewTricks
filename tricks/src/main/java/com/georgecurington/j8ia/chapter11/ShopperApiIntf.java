/**
 * 
 */
package com.georgecurington.j8ia.chapter11;

import java.util.concurrent.Future;

/**
 * @author Owner
 *
 */
public interface ShopperApiIntf {

	public double getPriceDouble(String product);
	public String getPrice(String product);

	
	public Future<Double> getPriceAsync(String product);

	String getName();
}

