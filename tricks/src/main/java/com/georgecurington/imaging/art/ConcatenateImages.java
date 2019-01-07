/**
 * 
 */
package com.georgecurington.imaging.art;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Owner
 *
 */
public class ConcatenateImages {

	/**
	 * 
	 */
	public ConcatenateImages() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		concate();

	}
	
	public static void concate() {
		int imagesCount = 4;
        BufferedImage images[] = new BufferedImage[imagesCount];
        for(int j = 0; j < images.length; j++) {
            images[j] = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = images[j].createGraphics();
            g2d.drawRect(10, 10, 80, 80);
            g2d.dispose();
        } 
        
        int heightTotal = 0;
        for(int j = 0; j < images.length; j++) {
            heightTotal += images[j].getHeight();
        }
        
        int heightCurr = 0;
        BufferedImage concatImage = new BufferedImage(100, heightTotal, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = concatImage.createGraphics();
        for(int j = 0; j < images.length; j++) {
            g2d.drawImage(images[j], 0, heightCurr, null);
            heightCurr += images[j].getHeight();
        }
        g2d.dispose();
        try {
			ImageIO.write(concatImage, "png", new File("C:\\images\\concat.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // export concat image
        try {
			ImageIO.write(images[0], "png", new File("C:\\images\\single.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // export single image
	}
	
	public static void concate2() {
		// 23 columns , 13 rows 
		// 590 x 590 
		
	}
	
	public static class IWriter {
		private final int nmbrImages;
		private final int width;
		private final int height;
		private final int rows;
		private final int cols;
		private final int type;
		
		public IWriter (int nmbrImages,int rows, int cols,int width, int height,int type) {
			this.nmbrImages = nmbrImages;
			this.width = width;
			this.height = height;
			this.rows = rows;
			this.cols = cols;
			this.type = type;
			createImage(nmbrImages,rows,cols, width,height,type);
		}
		
		private void createImage(int nmbrImages, int rows, int cols, int width, int height, int type) {
	        BufferedImage finalImg = new BufferedImage(width*cols, height*rows, type);

			
		}

		public void checkSizeAndWrite(int siz) {
			if (siz == nmbrImages) {
				dumpImage();
			}
		}

		private void dumpImage() {
			// TODO Auto-generated method stub
			
		}
	}

}

