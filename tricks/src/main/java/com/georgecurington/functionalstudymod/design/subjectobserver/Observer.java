/**
 * 
 */
package com.georgecurington.functionalstudymod.design.subjectobserver;

/**
 * <pre>
 * Simple multi threaded similator for testing the Subject
 * Observer pattern.
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public interface Observer {
	//method to update the observer, used by subject
	public void update();
	
	//attach with subject to observer
	public void setSubject(Subject sub);

	String getLastMessage();
}
