package jirtbioss.core.client.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.opencv.core.Core;

public class ImagesList implements Serializable{

	//Fields
	private String[] images;
	private ArrayList<String> imageNames = new ArrayList<String>();
	public ArrayList<String> getImageNames() {
		return imageNames;
	}

	public void setImageNames(ArrayList<String> imageNames) {
		this.imageNames = imageNames;
	}

	private ArrayList<String> speciesImages;
	

	public ArrayList<String> getSpeciesImages() {
		return speciesImages;
	}

	public void setSpeciesImages(ArrayList<String> speciesImages) {
		this.speciesImages = speciesImages;
	}

	public String[] getImages() {
		//System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}
	
	
}
