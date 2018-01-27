/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.masteringlambdas.chapter5;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.file.Path;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 26, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class LineSpliterator implements Spliterator<DispLine>{

	private static final int AVERAGE_LINE = 160;
	private static final int LENGTH = 160;
	private static final boolean DEBUG = false;
	private ByteBuffer bb;
	private int lo, hi;
	
	/** 
	 * <pre>
	 * LineSpliterator is constructed to cover a range of the ByteBuffer bounded
	 * by two indices, lo and hi, both inclusive, supplied at contruction time.
	 * </pre>
	 * 
	 */
	public LineSpliterator
	(
	ByteBuffer bb
	, int lo
	, int hi
	) {
		this.bb = bb ;
		this.lo = lo ;
		this.hi = hi ;
		System.out.println("hi = " + hi + ", lo=" + lo);
	}

	public LineSpliterator(MappedByteBuffer bb, int lo, int hi, Path start) {
		this.bb = bb ;
		this.lo = lo ;
		this.hi = hi ;
		if ( DEBUG ) {
		System.out.println("path=" + start.getFileName());
		System.out.println("hi = " + hi + ", lo=" + lo);
		}
	}

	/**
	 * returns the characteristics of this Spliterator, used by the 
	 * framework to choose appropriate optimizations (see §6.3). 
	 * Here is a suitable implementation of characteristics 
	 * for this problem:
	 * @see java.util.Spliterator#characteristics()
	 */
	@Override
	public int characteristics() {
		return  ORDERED | IMMUTABLE | NONNULL; 
	}

	/**
	 * returns an estimate, necessarily approximate in this case, 
	 * of the number of elements that would be returned by repeated 
	 * calls of tryAdvance.
	 * @see java.util.Spliterator#estimateSize()
	 */
	@Override
	public long estimateSize() {
		return ( hi - lo + 1)/AVERAGE_LINE-LENGTH;
	}

	/**
	 * <pre>
	 * The purpose of tryAdvance is to facilitate processing of the 
	 * next available DispLine instance. It searches for the next 
	 * newline character, creates a DispLine instance from the bytes 
	 * traversed in the search, and applies its supplied Consumer argument 
	 * to it. Finally, it reduces the spliterator’s range to exclude the 
	 * bytes just processed, and returns a boolean signifying the 
	 * availability of further input.
	 * 
	 * @see java.util.Spliterator#tryAdvance(java.util.function.Consumer)
	 */
	@Override
	public boolean tryAdvance(Consumer<? super DispLine> action) {
		int index = lo;
		StringBuilder sb = new StringBuilder();
		do {
			if (DEBUG) {
			System.out.println("index=" + index + ",char=" + (char)bb.get(index));
			}
			sb.append((char)bb.get(index));
		} while (bb.get(index++) != '\n' && index < hi);
		
		/** allow the consumer to perform an action upon the DispLine Object **/
		action.accept(new DispLine(lo, sb.toString()));
		
		/** move the pointer past the current DispLine object **/
		lo = lo + sb.length();
		
		/** return an indicator to determine if we have more data to process **/
		return lo <= hi ;
	}

	/**
	 * If this range is to be split, the aim will be to divide the buffer 
	 * into two approximately equal parts, each of them again 
	 * terminated by a newline. A suitable dividing point between the 
	 * parts will be found by making a linear search for a newline, 
	 * in either direction, starting from the midpoint of the buffer. 
	 * 
	 * @return
	 */
	@Override
	public Spliterator<DispLine> trySplit() {
		/** find the mid point of the buffer **/
		int mid = ( lo + hi ) >>> 1;
		
		/** find the closest newline character and let that be the real endpoint **/
		while (bb.get(mid) != '\n' ) mid++;
		
		LineSpliterator newSpliterator = null ;	
		/** if mid is == hi, then just return null , we did not find a new line **/
		if ( mid != hi )
		{
			newSpliterator = new LineSpliterator(bb,lo,mid);
			lo = mid + 1;
		}
		
		return newSpliterator;
	}

}
