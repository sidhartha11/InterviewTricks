package com.georgecurington.functionalstudymod.concurrent.deadlock1;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

public class Worker3 implements Callable<Void> {

	private final CallType callType;
	private final ExplicitLockExample lock;
	private final Account myAccount;
	private final Account yourAccount;
	private final long timeout;
	private final TimeUnit timeunit;

	/**
	 * 
	 */
	public Worker3(CallType callType
			,ExplicitLockExample lock
			,Account myAccount
			, Account yourAccount
			,long timeout
			,TimeUnit timeunit
			) {
		super();
		this.callType = callType;
		this.lock = lock;
		this.myAccount = myAccount;
		this.yourAccount = yourAccount;
		this.timeunit = timeunit;
		this.timeout = timeout;
		
	}

	@Override
	public Void call() throws Exception {
		/** let the money transferred range between 0 and 20 dollars **/
		long money = ThreadLocalRandom.current().nextLong(0,21);
		Utility.p(callType + " processing " + money);
		try {
		switch ( callType ){
		case myAccountYourAccountExplicit:
			lock.transferMoney(myAccount,yourAccount,money,timeout,timeunit);
			Utility.p("result myAccount:" + myAccount );
			Utility.p("result yourAccount:" + yourAccount);
			break;
		case yourAccountMyAccountExplicit:
			lock.transferMoney(yourAccount,myAccount,money,timeout,timeunit);
			Utility.p("result yourAccount:" + yourAccount);
			Utility.p("result myAccount:" + myAccount );
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