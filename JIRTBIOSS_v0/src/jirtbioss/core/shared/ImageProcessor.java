package jirtbioss.core.shared;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageProcessor {
	private BufferedImage image;
	private int width;
	private int height;
	
	public int getWidth() {
		return width;
	}

	//Constructor without fields
	public ImageProcessor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Constructor using the fields
	public ImageProcessor(BufferedImage image, int width, int height) {
		super();
		this.image = image;
		this.width = width;
		this.height = height;
	}
	
	//Getters and setters
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	//perform RGB to grayscale
	public void convertRGBtoGrayccale(String sourceImgPath, String destImgPath) {
		   
	      try {
	         File input = new File(sourceImgPath);
	         image = ImageIO.read(input);
	         width = image.getWidth();
	         height = image.getHeight();
	         
	         for(int i=0; i<height; i++){
	         
	            for(int j=0; j<width; j++){
	            
	               Color c = new Color(image.getRGB(j, i));
	               int red = (int)(c.getRed() * 0.299);
	               int green = (int)(c.getGreen() * 0.587);
	               int blue = (int)(c.getBlue() *0.114);
	               Color newColor = new Color(red+green+blue,
	               
	               red+green+blue,red+green+blue);
	               
	               image.setRGB(j,i,newColor.getRGB());
	            }
	         }
	         
	         File ouptut = new File(destImgPath);
	         ImageIO.write(image, "jpg", ouptut);
	         
	      } catch (Exception e) {}
	   }
	
	//Perform image resizing
	public BufferedImage resize(BufferedImage img) {
        Image tmp = img.getScaledInstance(this.width, this.height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	//Perform image histogram equalization - for contrast enhancement
	public BufferedImage imageEqualized(BufferedImage img) {
		BufferedImage equalizedImg = img;
		
		return equalizedImg;
	}

}
