/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.deadlock1;

/**
 * <pre>
 * From chapter 10 in Concurrency In Practice illustrating problems and 
 * solutions to causing and preventing deadlocks due to incorrect lock ordering
 * aquisition.
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class DynamicLockOrderExample {
	private final Object tieLock=new Object();
	/**
	 * 
	 */
	public DynamicLockOrderExample() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * <pre>
	 * This example from Concurrency In Practice shows the incorrect way to 
	 * use dynamically ordered locks. This method is capable of producing a deadlock
	 * depending on the order of the parameters passed to the method
	 * </pre>
	 * 
	 * @param fromAccount The account that we are taking money from
	 * @param toAccount The account that we are depositing money into
	 * @param amount The ammount we are depositing
	 * @throws InsufficientFundsException
	 */
	public void transferMoneyBad(Account fromAccount, Account toAccount, Long amount)
			throws InsufficientFundsException {
		synchronized (fromAccount) {
			synchronized (toAccount) {
				if (fromAccount.getBalance().compareTo(amount) < 0) {
					throw new InsufficientFundsException("fromAccount out of money");
				} else {
					fromAccount.debit(amount);
					toAccount.credit(amount);
				}
			}
		}
	}
	
	/**
	 * <pre>
	 * This code solves the problem of dead lock by assuring only one
	 * lock aquisition order by ordering it based on the System.identityHashCode and
	 * using a tie breaker lock when both accounts have the same hashcode. The 
	 * Tie breaker lock will simply only allow one thread at a time to acquire either
	 * lock.
	 * </pre>
	 * @param fromAcct The account we are transfering money from
	 * @param toAcct The account we are transferring money to
	 * @param amount The dollar amount of the transaction.
	 * @throws InsufficientFundsException
	 */
	public void transferMoney ( final Account fromAcct ,
			final Account toAcct,
			final Long amount ) throws InsufficientFundsException {
		class Helper {
			public void transfer() throws InsufficientFundsException {
				if (fromAcct.getBalance().compareTo(amount) < 0) {
					throw new InsufficientFundsException();
				} else {
					fromAcct.debit(amount);
					toAcct.debit(amount);
				}
			}
		} // end class Helper
		
		/** generate a hashcode from the two accounts to contorl lock ordering **/
		int fromHash = System.identityHashCode(fromAcct);
		int toHash =   System.identityHashCode(toAcct);
		
		if ( fromHash < toHash ){
			synchronized(fromAcct){
				synchronized(toAcct){
					new Helper().transfer();
				}
			}
		} else if (fromHash > toHash){
			synchronized(toAcct){
				synchronized(fromAcct){
					new Helper().transfer();
				}
			}
		} else {  /** when both accounts have an equal hash code **/
			synchronized(tieLock){ /** usage of a independent lock to break the tie **/
				synchronized(fromAcct){
					synchronized(toAcct){
						new Helper().transfer();
					}
				}
			}
		}
		
		
	} // end transferMoney

}
