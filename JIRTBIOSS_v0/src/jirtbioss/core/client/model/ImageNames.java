package jirtbioss.core.client.model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ImageNames {

public ImageNames(){
	
}
public ArrayList<String> getListOfName(){
	ArrayList<String> listOfNames = new ArrayList<>();
	try{
		Files.walk(Paths.get("/imagecaptures")).forEach(filePath -> {
		    if (Files.isRegularFile(filePath)) {
		        System.out.println(filePath);
		        listOfNames.add(filePath.toString());
		    }
		});
		}
		 catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	return listOfNames;
	
}
public ArrayList<String> getFileNames(){
	File f = new File("/imagecaptures");
	ArrayList<String> theFileNames = new ArrayList<>();
	String[] names = f.list();

	// At this point Java listed all files in c:/tmp and loaded their names into an array of Strings
	for (String name : names) {
		theFileNames.add(name);
	}
	return theFileNames;
	
}
}
