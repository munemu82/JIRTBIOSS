package jirtbioss.core.shared;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;


public class DBUtility {
 private static Connection connection = null;
 private static String imageFolderPath = null;
 private static String liveImageDirPath = null;
 private static String identifiedImgpath = null;
 private static String jirtbiossLogPath = null;
 private static String jirtbiossAuthLogPath = null;
 private static String loggingLevel = null;
 private static String histogramEqualizaton=null;
 private static String imagegrayscale=null;
 private static String extractForTraining = null;
 private static String tempStorageFolder = null;
 
 private static int imageHeight = 0;
 private static int imageWidth = 0;
 private static int[] imageSizeProperties = new int[2];
 private static HashMap<String, String> loggingPathProperties = new HashMap<>();

 //Global properties
 private static Properties prop = new Properties();
 private static InputStream inputStream = DBUtility.class.getClassLoader().getResourceAsStream("/db.properties");

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
            	prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
    
    public static String getImageFolderPath() {
        if (imageFolderPath != null)
            return imageFolderPath;
        else {
            try {
            	prop.load(inputStream);
            	imageFolderPath = prop.getProperty("imagesFolder");
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return imageFolderPath;
        }

    }
    //This method is used to get the live images folder path from the db.properties configuration file
    public static String getLiveImagesFolderPath() {
        if (liveImageDirPath != null)
            return liveImageDirPath;
        else {
            try {
            	prop.load(inputStream);
            	liveImageDirPath = prop.getProperty("liveImagesFolder");
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return liveImageDirPath;
        }

    }
    
    //Define temporarary file storage folder location
    public static String getTempStoreFolder() {
        if (tempStorageFolder != null)
            return tempStorageFolder;
        else {
            try {
            	prop.load(inputStream);
            	tempStorageFolder = prop.getProperty("tempStorageFolder");
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tempStorageFolder;
        }

    }
    //This method is used to the identified images folder path from the db.properties configuration file
    public static String getIdentifiedImagesFolderPath() {
        if (identifiedImgpath != null)
            return identifiedImgpath;
        else {
            try {
            	prop.load(inputStream);
            	identifiedImgpath = prop.getProperty("identifiedImagesFolder");
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return identifiedImgpath;
        }

    }
  //This method is used to get images size properties (height and width) from the db.properties configuration file
    public static int[] getImageSizeProperties() {
        if (imageHeight > 0 && imageWidth > 0) {
        	imageSizeProperties[0] = imageHeight;
        	imageSizeProperties[1] =  imageWidth;
            return imageSizeProperties;
        }else {
            try {
            	prop.load(inputStream);
            	imageHeight = Integer.parseInt(prop.getProperty("imgHeight"));
            	imageWidth = Integer.parseInt(prop.getProperty("imgWidth"));
            	imageSizeProperties[0] = imageHeight;
            	imageSizeProperties[1] =  imageWidth;
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return imageSizeProperties;
        }

    }
    //Method to get JIRTBIOSS userdefined logging path folder
    public static HashMap<String, String> getLoggingPaths() {
    	if(jirtbiossLogPath != null && jirtbiossAuthLogPath != null) {
    		loggingPathProperties.put("jirtbiossLogPath", jirtbiossAuthLogPath);
    		loggingPathProperties.put("jirtbiossAuthLogPath", jirtbiossAuthLogPath);
    		return loggingPathProperties;
    	}else {
            try {
            	prop.load(inputStream);
            	jirtbiossLogPath = prop.getProperty("jirtbiossLogPath");
            	jirtbiossAuthLogPath = prop.getProperty("jirtbiossAuthLogPath");
            	loggingPathProperties.put("jirtbiossLogPath", jirtbiossAuthLogPath);
        		loggingPathProperties.put("jirtbiossAuthLogPath", jirtbiossAuthLogPath);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return loggingPathProperties;
        }
    }
    //This method is used to copy image from one the uploaded folder to the application live folder
    public static void copyImage(String srcDir, String distFolder, String file, Boolean isCopyMove) throws IOException {
    	InputStream is = null;
        OutputStream os = null;
    	try {
    		Path dir = FileSystems.getDefault().getPath(srcDir);
	        //File sourceFile = new File(dir+"/"+file.getFileName());
    		File sourceFile = new File(dir+"/"+file);
	        File destFile = new File(distFolder + sourceFile.getName());
	        is = new FileInputStream(sourceFile);			//Source file
	        os = new FileOutputStream(destFile);			//Destination file
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	      //try to delete the file
	       if(isCopyMove==true) {
	    	   if(sourceFile.delete()){
		            System.out.println(file+" File deleted successfully");
		       }else{
		            System.out.println(file+" Failed to delete the file");
		        }
	       }
	       
    	}finally {
	        is.close();
	        os.close();
	    }
    }
       
    //LOGGING PROPERTIES
    public static String getLoggingLevel() {
        if (loggingLevel!= null)
            return loggingLevel;
        else {
            try {
            	prop.load(inputStream);
            	loggingLevel = prop.getProperty("loggingLevel");
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return loggingLevel;
        }

    }
    //IMAGE PROCESSING AND FEATURE EXTRACTION PROPERTIES
    public static String getImageHistProperty() {
        if (histogramEqualizaton!= null)
            return histogramEqualizaton;
        else {
            try {
            	prop.load(inputStream);
            	histogramEqualizaton = prop.getProperty("histogramEqualizaton");
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return histogramEqualizaton;
        }

    }
    public static String getImageGrayScaleProperty() {
        if (imagegrayscale!= null)
            return imagegrayscale;
        else {
            try {
            	prop.load(inputStream);
            	imagegrayscale = prop.getProperty("imagegrayscale");
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return imagegrayscale;
        }

    }
    public static boolean isForTraining() {
    	boolean isforTrain = false;
    	 if (extractForTraining!= null)
    		 if(extractForTraining.toUpperCase().equals("Y")) {
    			 isforTrain = true;
    		 }else {
    			 isforTrain =false;
    		 }
         else {
             try {
             	prop.load(inputStream);
             	extractForTraining = prop.getProperty("extractForTraining");
            	if(extractForTraining.toUpperCase().equals("Y")) {
            		isforTrain = true;
       		 	}else {
       		 		isforTrain =false;
       		 	} 
             }catch (FileNotFoundException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             }
             
         }
		return isforTrain;
    	
    }
    
}
