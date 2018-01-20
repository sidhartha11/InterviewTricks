/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.net.URL;
import java.util.List;

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
public class CustomWebCrawler extends WebCrawler {

	/**
	 * 
	 */
	public CustomWebCrawler() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.concurrent.cancellationshutdown.WebCrawler#processPage(java.net.URL)
	 */
	@Override
	protected List<URL> processPage(URL url) {
		return TestData.getAListOfFakeLinks(5);
	}
	
	public static void main(String...strings ){
		WebCrawler cwc = new CustomWebCrawler();
		Thread trd = new Thread(new Runnable(){
			public void run() {
				cwc.start();
			}
		});
		trd.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("stopping the crawler");
			cwc.stop();
			System.out.println("getting called tasks");
			List<Runnable> ct = cwc.getCancelledTasks();
			System.out.println("cancelled=" + ct);
			trd.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
