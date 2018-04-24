package jirtbioss.core.server;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public class ImageDirectory {
	
	private DirectoryStream<Path> listOfInImagesInDir;
	
	

	public ImageDirectory(DirectoryStream<Path> listOfInImagesInDir) {
		super();
		this.listOfInImagesInDir = listOfInImagesInDir;
	}

	public DirectoryStream<Path> getListOfInImagesInDir() {
		return listOfInImagesInDir;
	}

	public void setListOfInImagesInDir(DirectoryStream<Path> listOfInImagesInDir) {
		this.listOfInImagesInDir = listOfInImagesInDir;
	}
	

}
