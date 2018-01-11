/**
 * 
 */
package com.georgecurington.functionalstudymod.design.subjectobserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.georgecurington.functionalstudymod.testdata.TestData;

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
public class Simulator implements Callable<Void> {
	private static final int NUMBEROFCALLS = 10;
	private final int numberObservers;
	/**
	 * 
	 */
	public Simulator(int numberObservers) {
		this.numberObservers = numberObservers;
	}

	@Override
	public Void call() throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		
		/** set up the subject first **/
		Subject subject = new MyTopic();
		SubjectCaller subjectCaller = new SubjectCaller(subject);
		
		/** submit the subjectCaller to the executor **/
		exec.submit(subjectCaller);
		
		/** create a number of subjects to be notified, each in a thread **/
		List<ObserverCaller> observerCallers = new ArrayList<>();
		for ( int i=0; i < numberObservers; i++){
			Observer o = new MyTopicSubscriber("observer-" + i, subject);
			observerCallers.add(new ObserverCaller(o));
		}
		
		/** submit all the observerCallers in one call **/
		exec.invokeAll(observerCallers);
		try {
		performShutdownServices(exec);
		} finally {
			p("Simulator exiting ....");
		}
		return null;
	}
	
	static class SubjectCaller implements Callable<Void> {
		
		private final Subject subject;
		public SubjectCaller(Subject subject){
			this.subject = subject;
		}
		@Override
		public Void call() throws Exception {
			int cntr=0;
			while (true) {
			/** generate a random string **/
				String s = TestData.getSaltString(8,14,-1);
				p("subject caller #" + cntr + ":" + s);
				subject.postMessage(s);
				long r = ThreadLocalRandom.current().nextLong(250,5000);
				Thread.sleep(r);
				cntr++;
				if ( cntr > NUMBEROFCALLS) break;
			}
			return null;
		}
		
	}
	
	static class ObserverCaller implements Callable<Void> {
		private final Observer observer;
		public ObserverCaller(Observer observer){
			this.observer = observer;
		}
		@Override
		public Void call() throws Exception {
			int cntr=0;
			while (true) {
			/** just sleep for random periods and do nothing in this simple example **/
				String s = 
				observer.getLastMessage();
				p("obververCaller #" + cntr + ":" +  s);
				long r = ThreadLocalRandom.current().nextLong(250,5000);
				Thread.sleep(r);
				cntr++;
				if ( cntr > NUMBEROFCALLS) break;
			}
			return null;
		}
		
	}
	
	static public void p(String msg){
		String s = 
				String.format("thread %s posted: %s", Thread.currentThread().getName(), msg);
		System.out.println(s);
	}
	
	private static void performShutdownServices(ExecutorService exec) {
		exec.shutdown();
		try {
			while ( !exec.awaitTermination(1000, TimeUnit.MILLISECONDS)){
				p("waiting for shutdown");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String ...args){
		p("running simulation...");
		ExecutorService exec = Executors.newCachedThreadPool();
		int numberTasks = 10;
		exec.submit( new Simulator(numberTasks));
		performShutdownServices(exec);
		
		
		
	}
}
