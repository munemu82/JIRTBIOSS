package jirtbioss.core.shared;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/*
 * This class is used to perform much of the image procesing functions in this application
 * Any operations and/or manipulations required against any image will be perform here
 */
public class OpencvUtility {
	private String sourceImgPath;		//input image full path
	private String destImagPath;			//final processed image full path
	private int width;
	private int height;
	
	//Logging properties
	private String loggingLevelProperty = DBUtility.getLoggingLevel();
	private JirtbiossLogger jirbiossLogging = new JirtbiossLogger("JIRTBIOSS", loggingLevelProperty);
	
	
	//CONSTRUCTOR OF THE OPENCVUTILITY OBJECT
	public OpencvUtility(String sourceImgPath, String destImagPath) {
		super();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		this.sourceImgPath = sourceImgPath;
		this.destImagPath = destImagPath;
	}
	
	//Consutructor with all fields
	public OpencvUtility(String sourceImgPath, String destImagPath, int width, int height) {
		super();
		 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		this.sourceImgPath = sourceImgPath;
		this.destImagPath = destImagPath;
		this.width = width;
		this.height = height;
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
			   	// System.loadLibrary( Core.NATIVE_LIBRARY_NAME );  // Linux issue with Native runtime execution Need further investigation
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
	//PERFORM IMAGE HISTOGRAM EQUALIZATION
	public void imageHistEqualize() {
		try {
			Mat source = Imgcodecs.imread(this.sourceImgPath, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
			Mat destination = new Mat(source.rows(),source.cols(),source.type());
			
			Imgproc.equalizeHist(source, destination);
			Imgcodecs.imwrite(this.destImagPath, destination);
				
		} catch (Exception e) {
	         System.out.println("Error: " + e.getMessage());
		}
		
	}
	
	//IMAGE PRE-PROCESSING TASKS
	//---------------------------------------------------------------------------
	//Image resize
	public BufferedImage getResizedImg() {
		Mat sourceImg = Imgcodecs.imread(this.sourceImgPath, Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Size size = new Size(this.width, this.height);
		Mat resized = new Mat();
		Imgproc.resize(sourceImg, resized, size, 0, 0, Imgproc.INTER_NEAREST);
		byte[] data1 = new byte[resized.rows() * resized.cols() * (int)(resized.elemSize())];
		resized.get(0, 0, data1);
		BufferedImage finalBufferedImg = new BufferedImage(resized.cols(),resized.rows(), BufferedImage.TYPE_3BYTE_BGR);
		finalBufferedImg.getRaster().setDataElements(0, 0, resized.cols(), resized.rows(), data1);
		return finalBufferedImg;
	}
	//image file format/extension conversion and copy to the live folder destination
	public void copyImage(String fileName, String destFolderPath) {
		Imgcodecs imageCodecs = new Imgcodecs(); 
		Mat matrix;
		List<String> supportedImageFormats = new ArrayList<String>();
		supportedImageFormats.add("jpg");supportedImageFormats.add("jpeg");supportedImageFormats.add("gif");
		supportedImageFormats.add("png");supportedImageFormats.add("tif");supportedImageFormats.add("tiff");
		try {
						
			 if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
				String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
				if(supportedImageFormats.contains(fileExtension)) {
					matrix = imageCodecs.imread(this.sourceImgPath);
					if(!fileExtension.toUpperCase().equals("JPG")&& !fileExtension.toUpperCase().equals("JPEG")) {
						Imgcodecs.imwrite(destFolderPath+fileName+".jpg", matrix);
					}else {
						System.out.println(fileExtension);
						Imgcodecs.imwrite(destFolderPath+fileName, matrix);
					}
				}else {
					//log the processing error here
					this.jirbiossLogging.performLoggig("OpenCvUtility", "copyImage", "Image reading file as is not supported");	
				}
				
			} 
			 
		}catch (Exception e) {
			 e.printStackTrace();
			//log the file loading and processing error here
            this.jirbiossLogging.performLoggig("OpenCvUtility", "copyImage", "Image reading and writing failed due to " +e.getMessage());
            //this.jirbiossLogging.performLoggig("OpenCvUtility", "copyImage", "DETAILED ERROR:"+ e);
		}
		
	}
	public String getJpgFileExtension(String fileName) {
		List<String> supportedImageFormats = new ArrayList<String>();
		supportedImageFormats.add("jpg");supportedImageFormats.add("jpeg");supportedImageFormats.add("gif");
		supportedImageFormats.add("png");supportedImageFormats.add("tif");supportedImageFormats.add("tiff");
		String formatedFileName ="";
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
			if(supportedImageFormats.contains(fileExtension)) {
				
				if(!fileExtension.toUpperCase().equals("JPG")&& !fileExtension.toUpperCase().equals("JPEG")) {
					formatedFileName = fileName+".jpg";
				}else {
					formatedFileName = fileName;
				}
			}
		}
		return formatedFileName;
	}
	//END OF IMAGE PRE-PROCESSING TASKS

}
