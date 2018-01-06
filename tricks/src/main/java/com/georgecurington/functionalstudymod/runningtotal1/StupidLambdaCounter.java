package com.georgecurington.functionalstudymod.runningtotal1;

public class StupidLambdaCounter {
	private int cntr;
	public StupidLambdaCounter(int cntr){
		this.cntr = cntr;
	}
	public int getAndUpdate(int cntr){
		this.cntr += cntr;
		return this.cntr;
	}
	public int get(){
		return this.cntr;
	}
}