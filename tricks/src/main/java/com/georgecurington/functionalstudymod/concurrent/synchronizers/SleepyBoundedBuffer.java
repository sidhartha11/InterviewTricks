/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.synchronizers;
import static com.georgecurington.functionalstudymod.concurrent.synchronizers.TestHarness.*;

/**
 * @author george
 *
 */
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> implements Buffer<V> {

	/**
	 * @param capacity
	 */
	public SleepyBoundedBuffer(int capacity) {
		super(capacity);
	}

	/**
	 * <pre>
	 * checking on the condition variables are now taken care of 
	 * by the API while the lock is held
	 * </pre>
	 * @param v
	 * @throws InterruptedException
	 */
	public void put(V v) throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (!isFull()) {
					doPut(v);
					return;
				}
			}
			/** just sleep until the condition finally becomes true **/
			Thread.sleep(SLEEP_GRANULARITY);
		}
	}
	
	public V take() throws InterruptedException {
		while (true) {
			synchronized(this) {
				if (!isEmpty()){
					return doTake();
				}
			}
			Thread.sleep(SLEEP_GRANULARITY);
		}
	}
}
