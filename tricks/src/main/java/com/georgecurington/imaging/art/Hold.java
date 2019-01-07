package com.georgecurington.imaging.art;

public class Hold {

	public Hold() {
		// TODO Auto-generated constructor stub
	}
	
//	int rows = 2;   //we assume the no. of rows and cols are known and each chunk has equal width and height
//    int cols = 2;
//    int chunks = rows * cols;
//
//    int chunkWidth, chunkHeight;
//    int type;
//    //fetching image files
//    File[] imgFiles = new File[chunks];
//    for (int i = 0; i &lt; chunks; i++) {
//        imgFiles[i] = new File("archi" + i + ".jpg");
//    }
//
//   //creating a bufferd image array from image files
//    BufferedImage[] buffImages = new BufferedImage[chunks];
//    for (int i = 0; i &lt; chunks; i++) {
//        buffImages[i] = ImageIO.read(imgFiles[i]);
//    }
//    type = buffImages[0].getType();
//    chunkWidth = buffImages[0].getWidth();
//    chunkHeight = buffImages[0].getHeight();
//
//    //Initializing the final image
//    BufferedImage finalImg = new BufferedImage(chunkWidth*cols, chunkHeight*rows, type);
//
//    int num = 0;
//    for (int i = 0; i &lt; rows; i++) {
//        for (int j = 0; j &lt; cols; j++) {
//            finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * i, null);
//            num++;
//        }
//    }
//    System.out.println("Image concatenated.....");
//    ImageIO.write(finalImg, "jpeg", new File("finalImg.jpg"));


}
