/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.io.File;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre> 
 * This example from exercise 7.18 of Concurrency In Practice. Illustrating
 * the Poison Pill Idiom in Producer/Consumer application.
 * </pre>
 * <br>
 * <pre>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 19, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * </pre>
 */
public class CrawlerThread extends Thread {
    private final File root;
    private final BlockingQueue<File> queue;
	/**
	 * 
	 */
	public CrawlerThread(File root,BlockingQueue<File> queue) {
		super();
		this.root = root;
		this.queue = queue;
	}

	
	@Override
	public void run (){
		try {
			sneakyCrawl(root);
		} catch (InterruptedException e) { /* fall through */ }
		finally {
			while ( true ) {
				try {
					queue.put(ServerSocketClientUtil.POISON);
					break;
				} catch (InterruptedException e1) { /* retry */
			}
		}
	}

}

	/**
	 * <pre>
	 * Interesting way to traverse a directory tree by using
	 * a queue to store intermediary directory results.
	 * </pre>
	 * @param root
	 * @throws InterruptedException
	 */
	private void sneakyCrawl(File root) throws InterruptedException {
		Queue<File> q = new LinkedBlockingQueue<>();
		q.offer(root);
		while ( q.isEmpty()==false){
			File f = q.poll();
			if ( f.isDirectory() ) {
				Arrays.stream(f.listFiles()).forEach(p -> {
					if (p.isDirectory()){
						q.offer(p);
					} else {
						queue.offer(p);
					}
				});
			
			} else {
				queue.offer(f);
			}
		}
		
	}

	private void crawl(File root) throws InterruptedException {
		File[] f=root.listFiles();
		if ( f == null ) {
			throw new IllegalAccessError("cannot process:" + f);
		}
		for (File file : f ) {
			
			if ( file.isDirectory()) {
				crawl(file);
			} else {
				queue.put(file);
			}
			
		}
		
	}
}
