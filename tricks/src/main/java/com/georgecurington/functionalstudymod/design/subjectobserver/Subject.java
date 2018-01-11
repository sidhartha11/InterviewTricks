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
public interface Subject {
	
	// methods to register and unregister observers
	public void register (Observer obj);
	public void unregister(Observer obj);
	
	// method to notify observers of change
	public void notifyObservers();
	
	// method to get updates from subject 
	public Object getUpdate(Observer obj);
	void postMessage(String msg);

}
