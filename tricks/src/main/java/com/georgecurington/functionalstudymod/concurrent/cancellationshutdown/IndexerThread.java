/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre> see Example 7.18 - 7.20 of Concurrency In Practice </pre>
 * <br>
 * <pre>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 19, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * </pre>
 */
public class IndexerThread extends Thread {
	private final BlockingQueue<File> queue;
	AtomicInteger ai = new AtomicInteger(0);
	/**
	 * 
	 */
	public IndexerThread(BlockingQueue<File> queue) {
		this.queue = queue;
	}

	@Override 
	public void run() {
		try {
			while (true) {
				File file = queue.take();
				if (file == ServerSocketClientUtil.POISON){
					break;
				} else {
					indexFile(file);
				}
			}
		} catch (InterruptedException consumed) { }
	}

	private void indexFile(File file) {
		System.out.println(ai.incrementAndGet() + ":Indexing " + file);
		
	}

}
