/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.deadlock1;

/**
 * @author george
 *
 */
public interface Account {

	Long getBalance();

	void debit(Long amount);

	void credit(Long amount);

}
