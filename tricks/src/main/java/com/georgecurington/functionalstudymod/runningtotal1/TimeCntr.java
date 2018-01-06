package com.georgecurington.functionalstudymod.runningtotal1;

class Timecntr {
	private long cntr;
	public Timecntr(long cntr){
		this.cntr = cntr;
	}
	public long getAndUpdate(long cntr){
		this.cntr += cntr;
		return this.cntr;
	}
}
