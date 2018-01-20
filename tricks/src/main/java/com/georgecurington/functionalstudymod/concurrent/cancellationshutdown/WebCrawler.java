/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.georgecurington.functionalstudymod.testdata.TestData;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 20, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public abstract class WebCrawler {

	private volatile TrackingExecutor exec;
	private final Set<URL> urlsToCrawl = new HashSet<>();
	/**
	 * 
	 */
	public WebCrawler() {
		urlsToCrawl.addAll(TestData.getAListOfFakeLinks(10));
		System.out.println("created urls to work with");
		System.out.println("urlsToCrawl=" + urlsToCrawl);
	}
	
	public synchronized void start() {
		exec = new TrackingExecutor(Executors.newCachedThreadPool());
		
		for (URL url : urlsToCrawl){
			System.out.println("submitting " + url);
			submitCrawlTask(url);
		}
		urlsToCrawl.clear();
	}
	
	public synchronized void stop() throws InterruptedException {
		try {
			saveUncrawled(exec.shutdownNow());
			while (!exec.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
				System.out.println("waiting for webcrawler to stop");
			}
			
				saveUncrawled(exec.getCancelledTasks());
			
		} finally {
			System.out.println("finally called for stop()");
			// exec = null;
		}
	}
	
	protected abstract List<URL> processPage(URL url);
	
	private void saveUncrawled (List<Runnable> uncrawled) {
		for ( Runnable task : uncrawled ) {
			urlsToCrawl.add(((CrawlTask) task).getPage());
		}
	}
	
	private void submitCrawlTask ( URL u ) {
		exec.execute(new CrawlTask(u));
	}
	
	private class CrawlTask implements Runnable {
		private final URL url;
		
		public CrawlTask(URL url){
			this.url = url ;
		}

		@Override
		public void run() {
			for ( URL link : processPage(url)) {
				System.out.println("processing " + link);
				if ( Thread.currentThread().isInterrupted()) {
					System.out.println("thread was interrupted");
					return ;
				}
				submitCrawlTask(link);
			}
		}
		
		public URL getPage() {
			return url;
		}
		
	}
	
	protected List<Runnable> getCancelledTasks(){
		return exec.getCancelledTasks();
	}
}
