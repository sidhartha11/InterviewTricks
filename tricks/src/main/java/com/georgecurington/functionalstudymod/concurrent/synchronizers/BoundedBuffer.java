/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.synchronizers;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> implements Buffer<V> {

	// CONDITION PREDICATE: not-full (!isFull)
	// CONDITION PREDICATE: not-empty(!isEmpty)
	/**
	 * @param capacity
	 */
	public BoundedBuffer(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	public synchronized void put(V v) throws InterruptedException {
		while (isFull()) {
			/** public final void wait() **/
			/**
			 * causes the current thread to wait until a notify or notifyall is
			 * called
			 **/
			wait();
		}
		
		boolean wasEmpty = isEmpty();
		doPut(v);
		/** public final void notifyAll() **/
		/** awakens all threads waiting on the monitor **/
		if (wasEmpty)
		    notifyAll();
	}
	
	public synchronized V take() throws InterruptedException {
		/**
		 * grab the lock and check for the isEmpty state. if it
		 * is still empty wait and release the lock.
		 */
		while (isEmpty()){
			wait();
		}
		/** here we reacquied the lock and the isEmpty is false **/
		/** while holding the lock, get an item from the queue **/
		boolean wasFull = isFull();
		V v = doTake();
		
		/** notifyAll -- send out a wake up notice to all threads waiting for this lock **/

		if ( wasFull )
			notifyAll();
		
		/** The lock is released when this method exists. And all
		 * threads notified via notifyAll will get a chance to compete for the lock
		 */
		return v;
	}
	

}
