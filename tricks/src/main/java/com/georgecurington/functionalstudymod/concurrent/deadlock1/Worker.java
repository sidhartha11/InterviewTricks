package com.georgecurington.functionalstudymod.concurrent.deadlock1;

import java.util.concurrent.Callable;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
public class Worker implements Callable<Void> {

	private final CallType callType;
	private final LeftRightDeadLock lock;

	/**
	 * 
	 */
	public Worker(CallType callType
			,LeftRightDeadLock lock) {
		super();
		this.callType = callType;
		this.lock = lock;
	}

	@Override
	public Void call() throws Exception {
		try {
		switch ( callType ){
		case callLeft:
			lock.leftRight();
			break;
		case callRight:
			lock.rightLeft();
			break;
		default:
			Utility.p("illegal type:" + callType);
			throw new IllegalArgumentException();
		}
		return null;
	}finally {
		Utility.p("exiting");
	}
	}	
}