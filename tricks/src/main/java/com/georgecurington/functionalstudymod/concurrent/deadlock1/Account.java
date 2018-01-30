/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.deadlock1;

import java.util.concurrent.locks.Lock;

/**
 * @author george
 *
 */
public interface Account {

	

	Long getBalance();

	void debit(Long amount);

	void credit(Long amount);

	Lock getLock();

}
