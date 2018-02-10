/**
 * 
 */
package com.georgecurington.functionalstudymod.design.proxy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;


/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * Example of Proxy Pattern using Image as the subject to be proxied.
 * Both the proxy and the real subject, the RealImage  will implement this interface
 * The client will only have access to the proxy.
 * </pre>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 10, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public interface Image {
	public final String PNGFILE="C:\\temp\\dummy.png";
	public final String IMAGEFILE = 
	"https://github.com/sidhartha11/InterviewTricks/blob/master/tricks/readmeimages/observer-pattern.png";
	public void displayImage();
	
	public static void saveImage(String imageUrl)  {
		/** for testing purposes putting a static PNG file name **/
		Utility.p("loading real image:" + imageUrl.toString());
		URL url = null;
		try {
			url = new URL(imageUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	    
	    try 
	    (

	    		InputStream is = url.openStream();
	    	    OutputStream os = new FileOutputStream(PNGFILE);
	    ) {
		    byte[] b = new byte[2048];
		    int length;

		    while ((length = is.read(b)) != -1) {
		        os.write(b, 0, length);
		    }
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String...strings ) {
		Utility.p("simulating loading image");
		ProxyImage proxyImage = new ProxyImage(IMAGEFILE);
		Utility.p("simulating displaying image");
		proxyImage.displayImage();
	}
}
