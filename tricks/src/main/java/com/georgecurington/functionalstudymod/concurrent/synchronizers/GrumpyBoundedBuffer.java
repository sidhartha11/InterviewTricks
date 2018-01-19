/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.synchronizers;

/**
 * <pre>
 * <b>propagating precondition failure to callers</b>
 * 
 * This is a crude first attempt at implementing a bounded buffer. 
 * The put and take methods are synchronized to ensure exclusive 
 * access to the buffer state, since both employ check-then-act 
 * logic in accessing the buffer.
 * This class extends the base BaseBoundedBuffer and wraps
 * both doPut and doTake in a put and take method; both synchronized.
 * The condition variables located in the BaseBoundedBuffer class
 * can safely be inspected since the Intrinsic lock will have been
 * grabbed before doing so.
 * Note:
 * put and take are synchronized here and at the same time, isFull
 * and isEmpty is also synchronized within the BaseBoundedBuffer; just
 * as doPut and doTake are synchronized. This is something that should
 * be inspected further to wash out the actual semantics involved.
 * Is it truly necessary to synchronized put and take since the methods
 * they call are also synchronized ? OH --- check-then-act is the reason.
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> implements Buffer<V> {

	/**
	 * @param capacity
	 */
	public GrumpyBoundedBuffer(int capacity) {
		super(capacity);
	}

	public synchronized void put(V v) throws BufferFullException {
		if (isFull()) {
			throw new BufferFullException();
		}
		doPut(v);
	}
	
	public synchronized V take() throws BufferEmptyException {
		if (isEmpty()){
			throw new BufferEmptyException();
		}
		return doTake();
	}
}
