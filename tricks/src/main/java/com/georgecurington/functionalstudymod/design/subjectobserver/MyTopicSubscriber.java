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
public class MyTopicSubscriber implements Observer {

	private volatile String msg;
	private final String name;
	private final Subject topic;
	/**
	 * 
	 */
	public MyTopicSubscriber(String name , Subject topic) {
		this.topic = topic;
		this.name = name ; 
		this.topic.register(this);
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.subjectobserver.Observer#update()
	 */
	@Override
	public void update() {
		msg = (String) topic.getUpdate(this);
		msg = msg == null ? "null message" : "recieved:" + msg;
		Simulator.p(msg);
		
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.subjectobserver.Observer#setSubject(com.georgecurington.functionalstudymod.design.subjectobserver.Subject)
	 */
	@Override
	public void setSubject(Subject sub) {
		throw new IllegalArgumentException("ctr requirement");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MyTopicSubscriber [name=" + name + "]";
	}
	
	@Override
	public String getLastMessage(){
		return this.msg;
	}

}
