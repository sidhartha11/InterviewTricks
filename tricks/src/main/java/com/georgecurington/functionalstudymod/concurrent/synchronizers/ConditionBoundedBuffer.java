/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.synchronizers;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * Slightly altered version of the bounded buffer example
 * in "Concurrency In Practice"
 * The basic change was the implementing Buffer<V>.
 * This was done in order to use this class in a functional
 * test harness:
 * </pre>
 * @see com.georgecurington.functionalstudymod.concurrent.synchronizers.BoundedBufferCustomLambda
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class ConditionBoundedBuffer<V> implements Buffer<V> {
	
	/** create a reentrant lock and associate condition queues with it **/
	protected final Lock lock = new ReentrantLock();
	
	// Condition Predicate: notFull ( count < items.length  )
	private final Condition notFull = lock.newCondition();
	
	// Condition Predicate: notEmpty ( count > 0 )
	private final Condition notEmpty = lock.newCondition();
	
	private int tail;
	private int head;
	private int count;
	private final V[] items ;
	/**
	 * 
	 */
	public ConditionBoundedBuffer(int capacity) {
		items = (V[])new Object[capacity];
	}

	@Override
	// BLOCKS-UNTIL: notEmpty
	public V take() throws InterruptedException {
		// TODO Auto-generated method stub
		lock.lock();
		
		try {
			while (count == 0){
				notEmpty.await();
			}
			
			V v= items[head]; 
			items[head] = null;
			
			if (++head == items.length) {
				head = 0;
			}
			--count;
			notFull.signal();
			return v;
			
		} finally {
			lock.unlock();
		}
		
	}

	@Override
	// BLOCKS-UNTIL: notFull
	public void put(V v) throws InterruptedException {
		lock.lock();
		try {
			while ( count == items.length) {
				notFull.await();
			}
			
			items[tail] = v;
			if (++tail == items.length) {
				tail = 0;
			}
			++count;
			notEmpty.signal();
			
		} finally {
			lock.unlock();
		}
		
	}

}
