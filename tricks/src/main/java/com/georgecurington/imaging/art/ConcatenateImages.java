/**
 * 
 */
package com.georgecurington.imaging.art;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

/**
 * @author Owner
 *
 */
public class ConcatenateImages {
	private static final String dir =
			// "C:\\workspaces\\java\\imaging\\artrelated\\InterviewTricks\\trick\\workarea\\";

//			"C:\\aaaolddrive\\images\\";
	        "C:\\workarea\\fixed\\";

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
		concate2();

	}

	public static void concate() {
		int imagesCount = 4;
		BufferedImage images[] = new BufferedImage[imagesCount];
		for (int j = 0; j < images.length; j++) {
			images[j] = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = images[j].createGraphics();
			g2d.drawRect(10, 10, 80, 80);
			g2d.dispose();
		}

		int heightTotal = 0;
		for (int j = 0; j < images.length; j++) {
			heightTotal += images[j].getHeight();
		}

		int heightCurr = 0;
		BufferedImage concatImage = new BufferedImage(100, heightTotal, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = concatImage.createGraphics();
		for (int j = 0; j < images.length; j++) {
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
		// 13 columns , 13 rows
		// 590 x 590
		/** first look up one image to get the correct type **/
		String si = String.format("%s%04d.jpg", dir, 0);
		System.out.println(":" + si + ":");
//		si = "C:\\aaaolddrive\\newebay_3\\image_68brushTopPlas2.jpg";
//		si = "C:\\aaaolddrive\\newebay_3\\0000.jpg";

		System.out.println(si);
		File fi = new File(si);
		System.out.println(fi.toString());
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File(si));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int type = bi.getType();
		System.out.println(si + ":" + type);
		int nmbrImages = 4;
		int nmbrCols = 2;
		int nmbrRows = 2;
//		int chunkWidth = 500;
//		int chunkHeight = 500;
		int chunkWidth = 8134;
		int chunkHeight = 8134;

		/** create IWriter Object **/
		IWriter iWriter = new IWriter(nmbrImages, nmbrRows, nmbrCols, chunkWidth, chunkHeight, type);
		System.out.println(iWriter);

		/** scan thru all files **/
		IntStream.range(0, nmbrRows).forEach(r -> {
			IntStream.range(0, nmbrCols).forEach(c -> {
				System.out.println((nmbrCols * r + c) + "= filenumber");
				System.out.println("[" + chunkWidth * c + "," + chunkHeight * r + "]");

				/** add a rect to the underlying image **/
//				String fileno = String.format("%s%04d.jpg", dir, nmbrCols * r + c);
				String fileno = String.format("%senhancedA%d.jpg", dir, nmbrCols * r + c);
				iWriter.addRect(fileno, chunkWidth * c, chunkHeight * r);
			});
		});
		
		iWriter.writeImage("newenhanced.jpg");

	}

	public static class IWriter {
		private final int nmbrImages;
		private final int width;
		private final int height;
		private final int rows;
		private final int cols;
		private final int type;
		private final BufferedImage finalImg;

		public IWriter(int nmbrImages, int rows, int cols, int width, int height, int type) {
			this.nmbrImages = nmbrImages;
			this.width = width;
			this.height = height;
			this.rows = rows;
			this.cols = cols;
			this.type = type;
			finalImg = createImage(nmbrImages, rows, cols, width, height, type);
		}

		public void addRect(String fileno, int x, int y) {
			/** read in the image **/
			System.out.println("adding " + fileno + ",x=" + x + ",y=" + y);
			BufferedImage bi = null;
			try {
				bi = ImageIO.read(new File(fileno));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int type = bi.getType();
//		    for (int i = 0; i &lt; chunks; i++) {
//		        buffImages[i] = ImageIO.read(imgFiles[i]);
			finalImg.createGraphics().drawImage(bi, x, y, null);
		}

		public void writeImage(String filename) {
			System.out.println("Image concatenated.....");
			try {
				ImageIO.write(finalImg, "jpeg", new File(dir + filename));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private BufferedImage createImage(int nmbrImages, int rows, int cols, int width, int height, int type) {
			return new BufferedImage(width * cols, height * rows, type);

		}

		public void checkSizeAndWrite(int siz) {
			if (siz == nmbrImages) {
				dumpImage();
			}
		}

		private void dumpImage() {
			// TODO Auto-generated method stub

		}

		@Override
		public String toString() {
			return "IWriter [nmbrImages=" + nmbrImages + ", width=" + width + ", height=" + height + ", rows=" + rows
					+ ", cols=" + cols + ", type=" + type + ", finalImg=" + finalImg + "]";
		}
	}

}
