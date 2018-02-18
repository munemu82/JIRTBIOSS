package jirtbioss.core.shared;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.NonSparseToSparse;



public class CsvFeatureExtractor {
	
	private FileWriter fileWriter;
	private String fileName;
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private String theImagesFolderPath = DBUtility.getImageFolderPath();
	private String tempStorageFolder = DBUtility.getTempStoreFolder();
	
	public CsvFeatureExtractor(String fileName) {
		super();
		this.fileName = fileName;
		try {
			this.fileWriter = new FileWriter(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	//Save the features into csv file
	public void featuresToCsv(ArrayList<ArrayList<Double>> featuresList) {
		//populate the CSV
		try {
			
			for(int j=0; j < featuresList.size(); j++)
			{
				for(int i=0; i < featuresList.get(j).size();i++) {
					try {
						this.fileWriter.append(String.valueOf(featuresList.get(j).get(i)));
						this.fileWriter.append(COMMA_DELIMITER);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				//Add folder name as a class for the image - means it is good idea for user to specify a folder that represents the containing images 
				this.fileWriter.append(this.getClassName(theImagesFolderPath));
				this.fileWriter.append(NEW_LINE_SEPARATOR);
			}
		}	catch (Exception e) {
			//will modify this to be writing to a log file
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				this.fileWriter.flush();
				this.fileWriter.close();
			} catch (IOException e) {
				//will modify this to be writing to a log file
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	}
	//THIS IS EXTENSION TO EXTRACT ALSO ARFF FILE FOR MODEL TRAINING
	public Instances createArff(ArrayList<ArrayList<Double>> featureVecData, Boolean isForPredict) {
		//1- Create List of features 
		ArrayList<Attribute> attributeList = new ArrayList<Attribute>(2);
		 final List<String> classes = new ArrayList<String>() {
	            {
	                add("None");
	                add(getClassName(theImagesFolderPath));
	                add("Dingo");
	            }
	        };
		for(int i=0; i < featureVecData.get(1).size(); i++) {
			attributeList.add(new Attribute("feature"+Integer.toString(i)));
		}
		if(isForPredict==true) {
			 classes.add(this.getClassName(theImagesFolderPath));
			 Attribute attributeClass = new Attribute("@@class@@", classes);
			 attributeList.add(attributeClass);
		}
		//2- create DenseInstance object
		Instances dataSet = new Instances("JIRTBIOSS", attributeList, 0);
		for(int f=0; f < featureVecData.size(); f++) {
			DenseInstance arffInstance;
			if(isForPredict==false) {				// for creating a Arff instance that has no class for prediction
				arffInstance = new DenseInstance(featureVecData.get(f).size());
				for(int j=0; j < arffInstance.numAttributes(); j++) {
					arffInstance.setValue(attributeList.get(j), featureVecData.get(f).get(j));
				}
			}else {
				arffInstance = new DenseInstance(attributeList.size());  // for arff instance that can be used for training a classifier
				for(int k=0; k < arffInstance.numAttributes(); k++) {
					if(k < arffInstance.numAttributes() - 1){
						arffInstance.setValue(attributeList.get(k), featureVecData.get(f).get(k));
						
					}else {
						arffInstance.setValue(attributeList.get(arffInstance.numAttributes()-1), classes.get(1));
					}
				}
											
			}
			//add the instance from 
			dataSet.add(arffInstance);
		}
		
		
		
		//4- return the instance
		return dataSet;
	}
	
	//THIS METHOD IS TO SAVE ARFF FILE GIVEN A LIST OF INSTANCES
	public void saveArff(Instances dataInstances) {
		//process feature vector into an ARFF file
        NonSparseToSparse nonSparseToSparseInstance = new NonSparseToSparse(); 

        try {
			nonSparseToSparseInstance.setInputFormat(dataInstances);
			Instances sparseDataset = Filter.useFilter(dataInstances, nonSparseToSparseInstance);

	         ArffSaver arffSaverInstance = new ArffSaver(); 

	         arffSaverInstance.setInstances(sparseDataset); 

	         arffSaverInstance.setFile(new File(tempStorageFolder+"/JIRTBIOSS.arff")); 

	         arffSaverInstance.writeBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	//Extract the last folder name from the folder path and make it as a class
	public String getClassName(String imageFolderPath) {
		String[] imageFolderPathSplits= imageFolderPath.split("/");
		String className = imageFolderPathSplits[imageFolderPathSplits.length-1];

		return className;
	}

}
