/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.synchronizers;

/**
 * <pre>
 * A bounded buffer provides put and take operations, 
 * each of which has preconditions: you cannot take an element 
 * from an empty buffer, nor put an element into a full buffer. 
 * State dependent operations can deal with precondition 
 * failure by throwing an exception or returning an error status 
 * (making it the caller's problem), or by blocking until the 
 * object transitions to the right state.
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public abstract class BaseBoundedBuffer<V> {
	
	/** buffer state variables must be guarded by the buffers intrinsic lock **/
	private final V[] buf;
	private int tail;
	private int head;
	private int count;
	/**
	 * 
	 */
	protected BaseBoundedBuffer(int capacity) {

		/** must cast the internal Object array to the generic type **/
		this.buf = (V[])new Object[capacity];
	}
	
	protected synchronized final void doPut(V v){
		Main.p(" doPut tail=" + tail);
		Main.p(" doPut head=" + head);
		Main.p(" doPut count=" + count);
		/**
		 * tail is initially 0, so the first put will fill 
		 * the zeroth position. The tail pointer will be incremented
		 * by one. It the increment moves to a position that is 
		 * equal to buf.length .. then we are at the end of the buffer
		 * at which point we reset the tail counter to the beggining
		 * location:0
		 * count is updated unconditionally within this method.
		 */
		buf[tail] = v;
		if (++tail == buf.length) {
			tail = 0;
		}
		++count;
	}
	
	protected synchronized final V doTake() {
		/**
		 * head will be initially 0, if we take the head buf 
		 * position before anything happens, we will return what is
		 * at location 0. This is dependent upon something having 
		 * been put there by a previous doPut command. We move 
		 * head position by one increment and check to see if it 
		 * has moved passed the limit. If it has moved past the limit
		 * we reset head to zero. Also, note that we always null out 
		 * the location that is retrieved.
		 * count is decremented unconditionally in this method. 
		 */
		Main.p(" doTake tail=" + tail);
		Main.p(" doTake head=" + head);
		Main.p(" doTake count=" + count);
		V v = buf[head];
		buf[head]=null;
		if (++head == buf.length) {
			head = 0;
		}
		--count;
		return v;
	}
	
	public synchronized final boolean isFull() {
		return count == buf.length;
	}
	
	public synchronized final boolean isEmpty() {
		return count == 0;
	}

}
