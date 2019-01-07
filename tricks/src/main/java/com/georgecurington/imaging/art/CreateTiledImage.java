package com.georgecurington.imaging.art;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;



public class CreateTiledImage 
{
    BufferedImage image;
    Dimension size;
 
    public CreateTiledImage(BufferedImage image)
    {
        this.image = image;
        size = new Dimension(image.getWidth(), image.getHeight());
	System.out.println("CreateTiledImage:size:" + size);

	clipImage();
    }
    public CreateTiledImage(BufferedImage image,double w, double h,String name)
    {
        this.image = image;
        size = new Dimension(image.getWidth(), image.getHeight());
	System.out.println("CreateTiledImage:size:" + size);

	clipImage(w,h,name);
    }
 
    private void clipImage(double w, double h,String name)
    {
        System.out.println("start clipImage");
        System.out.println("size:" + size );
        try
        {
//            double x = size.getWidth() / 2 - ( w/2 ) ; 
//            double y = size.getHeight() / 2 - ( h/2 ); 
            double x = size.getWidth() / 4 - ( w/2 ) ; 
            double y = size.getHeight() / 4 - ( h/2 ); 
	    int counter = 0 ; 
	    double wd = (double)w;
	    String dst = null; 
	    boolean stop = false;
	    double thesize = size.getWidth();
	    double repititions = size.getWidth() / w ; 
	    int xx = (int)x;
	    int yy = (int)y;
	    int ww = (int)w;
	    int hh = (int)h;
	    dst = "workarea/" + name  ;
	    System.out.println("x:" + x );
	    System.out.println("y:" + y );
	    System.out.println("dst:" + dst );
       	    BufferedImage bi = image.getSubimage(xx, yy, ww, hh);
            File outputfile = new File(dst);
            ImageIO.write(bi, "jpg", outputfile);
	    outputfile = null ;
        }
        catch(Exception rfe)
        {
            System.out.println("raster format error: " + rfe.getMessage());
            return;
        }
        System.out.println("end clipImage");
    }
    private void clipImage()
    {
        System.out.println("start clipImage");
        System.out.println("size:" + size );
        try
        {
            int w =  500; 
            int h =  500; 
            int x = 0; 
            int y = 0; 
	    int counter = 0 ; 
	    double wd = (double)w;
	    String dst = "testone.jpg";	
	    boolean stop = false;
	    double thesize = size.getWidth();
	    double repititions = size.getWidth() / w ; 
	    System.out.println("repetitions:" + repititions );
	for ( int ystart = 0 ; ystart < repititions ; ystart++ ) {	
	for ( int xstart = 0 ; xstart < repititions ; xstart++ ) {	
//	    dst = "image_" + x + y + ".jpg" ;
//
//
	    String scnt = String.valueOf(counter);	
	    int ii = 4 - scnt.length();
	    for ( int j = 0 ; j < ii ; j++ )
	    	scnt = "0" + scnt ; 
	    dst = "workarea/" + scnt + ".jpg" ;
	    counter++;
       	    BufferedImage bi = image.getSubimage(x, y, w, h);
            File outputfile = new File(dst);
            ImageIO.write(bi, "jpg", outputfile);
	    outputfile = null ;
	    x = x + w;
	}
	x = 0;
	y = y + h;
//	if ( y > size.getHeight() )
//		stop = true;
	}
        }
        catch(Exception rfe)
        {
            System.out.println("raster format error: " + rfe.getMessage());
            return;
        }
        System.out.println("end clipImage");
    }
 
    public static void main(String[] args) throws IOException
    {
//        File file = new File("sq_face_500.jpg");
	if ( args.length == 2 ) {
    		String SrcImageDir  = args[0];
    		double width  = Double.valueOf(args[1]);
		int counter=0;
    		List<File> thefiles=null;
		File tempDir=null;
	// get number of files currently in the destination
		tempDir = new File(SrcImageDir);
		thefiles = GetDirListing.getGetDirListing( tempDir );
		for ( File tf : thefiles ) {
			if ( tf.getName().endsWith(".jpg") )
			{
			counter++;
			System.out.println(counter + ". file:" + tf.getName());
        		CreateTiledImage test = new CreateTiledImage(ImageIO.read(tf), width, width , tf.getName() );
			}
		}
		System.out.println("width=" + width);
	}
	else if ( args.length == 1 ) {
        File file = new File(args[0]);
        CreateTiledImage test = new CreateTiledImage(ImageIO.read(file));
	}
    }
}
