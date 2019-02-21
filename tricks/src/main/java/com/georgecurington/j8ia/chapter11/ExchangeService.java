package com.georgecurington.j8ia.chapter11;
import static com.georgecurington.j8ia.chapter11.Java8utils.*;
public class ExchangeService {


	
	public static double getRate(Money source, Money destination) {
		return getRateWithDelay(source,destination);
	}
	
	private static double getRateWithDelay(Money source, Money destination) {
		delay();
		return destination.rate / source.rate ;
	}
	public ExchangeService() {
		// TODO Auto-generated constructor stub
	}

}
