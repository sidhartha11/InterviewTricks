/**
 * 
 */
package com.georgecurington.functionalstudymod.design.proxy;

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
public class ProxyImage implements Image {
	private final String url;
	/**
	 * 
	 */
	public ProxyImage(String url) {
		this.url = url;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.proxy.Image#displayImage()
	 */
	@Override
	public void displayImage() {
		RealImage realImage = new RealImage(url);
		realImage.displayImage();
	}

}
