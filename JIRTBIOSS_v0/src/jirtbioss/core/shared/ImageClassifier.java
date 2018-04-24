package jirtbioss.core.shared;

import java.util.ArrayList;

import weka.classifiers.Classifier;
import weka.core.Instances;

public class ImageClassifier {
	
	//Classifier properties
	private String classifierPath;
	private String classifierName;
	private String classPredictionLabel;
	private ArrayList<Double> predictProbabilities;
	
	//The constructor with fields
	public ImageClassifier(String classifierPath, String classifierName) {
		super();
		this.classifierPath = classifierPath;
		this.classifierName = classifierName;
	}
	
	//Constructor without fields
	public ImageClassifier() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Getters and Setters
	public String getClassifierPath() {
		return classifierPath;
	}

	public void setClassifierPath(String classifierPath) {
		this.classifierPath = classifierPath;
	}

	public String getClassifierName() {
		return classifierName;
	}

	public void setClassifierName(String classifierName) {
		this.classifierName = classifierName;
	}

	public String getClassPredictionLabel() {
		return classPredictionLabel;
	}

	public void setClassPredictionLabel(String classPredictionLabel) {
		this.classPredictionLabel = classPredictionLabel;
	}

	public ArrayList<Double> getPredictProbabilities() {
		return predictProbabilities;
	}

	public void setPredictProbabilities(ArrayList<Double> predictProbabilities) {
		this.predictProbabilities = predictProbabilities;
	}
	
	//Clear the current probabilities
	public ArrayList<Double> clearpredictProbabilities(ArrayList<Double> predictProbabilities) {
		predictProbabilities.clear();
		return predictProbabilities;
	}
	//Perform prediction
	public String getPredictedClass(Instances featureVector) {
		//1- Define required variables
		String predictedClass = "";
		Classifier theClassifier = null;
		double predictedValue;
		double[] predictionProbs;
		ArrayList<Double> thisPredictionProbs = new ArrayList<>();

		
		//THE PREDICTION PROCESS
		try {
			//2- Load the model
			theClassifier = (Classifier) weka.core.SerializationHelper.read(this.getClassifierPath()+this.getClassifierName());
			
			//3- Perform the prediction
			predictedValue = theClassifier.classifyInstance(featureVector.instance(0));
			
			 //get the prediction percentage or distribution
			predictionProbs=theClassifier.distributionForInstance(featureVector.instance(0));
			
			//5- get the name of the class value
	        predictedClass = featureVector.classAttribute().value((int)predictedValue); 
	        
	        for(int i=0; i < predictionProbs.length; i=i+1) {
	        	thisPredictionProbs.add(predictionProbs[i]);
	        }
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Set the probabilities
		this.setPredictProbabilities(thisPredictionProbs);
		
		
		return predictedClass;
	}
	
	
	
}
