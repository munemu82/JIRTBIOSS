package jirtbioss.core.shared;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/*
 * This class is used to perform much of the image procesing functions in this application
 * Any operations and/or manipulations required against any image will be perform here
 */
public class OpencvUtility {
	private String sourceImgPath;		//input image full path
	private String destImagPath;			//final processed image full path
	
	
	//CONSTRUCTOR OF THE OPENCVUTILITY OBJECT
	public OpencvUtility(String sourceImgPath, String destImagPath) {
		super();
		this.sourceImgPath = sourceImgPath;
		this.destImagPath = destImagPath;
		//load opencv object
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	}
	
	//GETTERS AND SETTERS OF THE OPENCVUTILITY OBJECT
	public String getSourceImgPath() {
		return sourceImgPath;
	}
	public void setSourceImgPath(String sourceImgPath) {
		this.sourceImgPath = sourceImgPath;
	}
	public String getDestImagPath() {
		return destImagPath;
	}
	public void setDestImagPath(String destImagPath) {
		this.destImagPath = destImagPath;
	}
	
	//CONVERT IMAGE TO GRAYSCALE
	public void imageToGrayscale() {
		   try {
		         
		         File input = new File(this.sourceImgPath);
		         BufferedImage image = ImageIO.read(input);	

		         byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		         Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
		         mat.put(0, 0, data);

		         Mat mat1 = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);
		         Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);

		         byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
		         mat1.get(0, 0, data1);
		         BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
		         image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

		         File ouptut = new File(this.destImagPath);
		         ImageIO.write(image1, "jpg", ouptut);
		         
		      } catch (Exception e) {
		         System.out.println("Error: " + e.getMessage());
		      }
		   
	   }
	
	

}
