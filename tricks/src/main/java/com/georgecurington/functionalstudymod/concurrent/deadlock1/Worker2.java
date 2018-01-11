package com.georgecurington.functionalstudymod.concurrent.deadlock1;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

public class Worker2 implements Callable<Void> {

	private final CallType callType;
	private final DynamicLockOrderExample lock;
	private final Account myAccount;
	private final Account yourAccount;

	/**
	 * 
	 */
	public Worker2(CallType callType
			,DynamicLockOrderExample lock,Account myAccount, Account yourAccount) {
		super();
		this.callType = callType;
		this.lock = lock;
		this.myAccount = myAccount;
		this.yourAccount = yourAccount;
	}

	@Override
	public Void call() throws Exception {
		/** let the money transferred range between 0 and 20 dollars **/
		long money = ThreadLocalRandom.current().nextLong(0,21);
		Utility.p(callType + " processing " + money);
		try {
		switch ( callType ){
		case myAccountYourAccount:
			lock.transferMoneyBad(myAccount,yourAccount,money);
			Utility.p("result myAccount:" + myAccount );
			Utility.p("result yourAccount:" + yourAccount);
			break;
		case yourAccountMyAccount:
			lock.transferMoneyBad(yourAccount,myAccount,money);
			Utility.p("result yourAccount:" + yourAccount);
			Utility.p("result myAccount:" + myAccount );
			break;
		case myAccountYourAccountHash:
			lock.transferMoney(myAccount,yourAccount,money);
			Utility.p("result myAccount:" + myAccount );
			Utility.p("result yourAccount:" + yourAccount);
			break;
		case yourAccountMyAccountHash:
			lock.transferMoney(yourAccount,myAccount,money);
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