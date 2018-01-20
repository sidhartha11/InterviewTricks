/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * Driver program for the Poison Pill Exercise ( chapter 7, CIP )
 * <br>
 * </pre>
 * <pre>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 19, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * </pre>
 */
public class IndexingService {
	private final IndexerThread consumer;
	private final CrawlerThread producer;
	private final BlockingQueue<File> queue;
	private final File root;
	/**
	 * 
	 */
	public IndexingService(String root) {
		this.root = new File(root);
		this.queue = new LinkedBlockingQueue<>();
		consumer = new IndexerThread(queue);
		producer = new CrawlerThread(this.root,queue);
	}
	
	public void start(){
		producer.start();
		consumer.start();
	}

	public void stop(){ producer.interrupt(); }
	
	public void awaitTermination() throws InterruptedException {
		consumer.join();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("running IndexingService");
		new IndexingService("C:\\teeshirts\\").start();

	}

}
