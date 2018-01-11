/**
 * 
 */
package com.georgecurington.functionalstudymod.design.subjectobserver;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

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
public class MyTopic implements Subject {

	private final Set<Observer> observers;
	private volatile String message;
	private volatile boolean changed;
	/**
	 * 
	 */
	public MyTopic() {
		this.observers = new CopyOnWriteArraySet<>();
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.subjectobserver.Subject#register(com.georgecurington.functionalstudymod.design.subjectobserver.Observer)
	 */
	@Override
	public void register(Observer obj) {
		observers.add(obj);
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.subjectobserver.Subject#unregister(com.georgecurington.functionalstudymod.design.subjectobserver.Observer)
	 */
	@Override
	public void unregister(Observer obj) {
		observers.remove(obj);
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.subjectobserver.Subject#notifyObservers()
	 */
	@Override
	public void notifyObservers() {
		Simulator.p("notifying observers");
		observers.stream().forEach(Observer::update);

	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.subjectobserver.Subject#getUpdate(com.georgecurington.functionalstudymod.design.subjectobserver.Observer)
	 */
	@Override
	public Object getUpdate(Observer obj) {
		// TODO Auto-generated method stub
		return this.message;
	}
	
	@Override
	public void postMessage(String msg) {
		Simulator.p("Message Posted to Topic:" + msg);
		this.message=msg;
		this.changed=true;
		notifyObservers();
	}

}
