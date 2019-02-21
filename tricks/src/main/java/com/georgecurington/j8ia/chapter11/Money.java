package com.georgecurington.j8ia.chapter11;

public enum Money {
	USD(1.0)
	,EUR(1.35387)
	,GBP(1.69715)
	,CAD(.92106)
	,MXN(.07683)
	;
	
	public final double rate;
	
	Money(double rate) {
		this.rate = rate;
	}
}