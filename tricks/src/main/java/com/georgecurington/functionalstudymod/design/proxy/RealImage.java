/**
 * 
 */
package com.georgecurington.functionalstudymod.design.proxy;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 10, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class RealImage implements Image {
	private final String url;
	/**
	 * 
	 */
	public RealImage(String url) {
		this.url = url;
		loadImage(url);
	}

	private void loadImage(String url) {
		Image.saveImage(url);
		
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.proxy.Image#displayImage()
	 */
	@Override
	public void displayImage() {
		Utility.p("simulating displaying image:[" + url + "]");
		try {
			TimeUnit.MILLISECONDS.sleep(5000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
