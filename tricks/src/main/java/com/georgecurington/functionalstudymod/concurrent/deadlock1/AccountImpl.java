/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.deadlock1;

/**
 * @author george
 *
 */
public class AccountImpl implements Account {
	private Long balance;
	/**
	 * 
	 */
	public AccountImpl(Long balance) {
		super();
		this.balance = balance;
	}


	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.concurrent.deadlock1.Account#getBalance()
	 */
	@Override
	public Long getBalance() {
		// TODO Auto-generated method stub
		return this.balance;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.concurrent.deadlock1.Account#debit(java.lang.Long)
	 */
	@Override
	public void debit(Long amount) {
		balance = balance - amount;

	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.concurrent.deadlock1.Account#credit(java.lang.Long)
	 */
	@Override
	public void credit(Long amount) {
		balance = balance + amount;

	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountImpl [balance=" + balance + "]";
	}

}
