/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.lambdaclosure;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * Author presents this class and does not give a single hint on 
 * how to use it .... totally worthless time spent reading that section
 * of his book
 * </pre>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 9, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
@FunctionalInterface
public interface FunIterator<E> extends Iterator<E> {

	Optional<E> nextElement ( boolean consume );
	
	@Override
	default boolean hasNext() {
		return nextElement(false).isPresent();
	}
	
	@Override
	default E next() {
		return nextElement(true).orElseThrow
		       (
				() -> new NoSuchElementException("Iterator is exhausted")
				);
	}
}

