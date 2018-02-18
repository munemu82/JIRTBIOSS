package jirtbioss.core.server;

import java.util.ArrayList;

import boofcv.abst.feature.dense.DescribeImageDense;
import boofcv.factory.feature.dense.ConfigDenseHoG;
import boofcv.factory.feature.dense.ConfigDenseSift;
import boofcv.factory.feature.dense.ConfigDenseSurfFast;
import boofcv.factory.feature.dense.FactoryDescribeImageDense;
import boofcv.struct.feature.TupleDesc_F64;
import boofcv.struct.image.GrayF32;

/*
 * This class use Boofcv features extraction API to extract some of the most important features of interests thats HOG, SIFT and LBP
 */
public class FeatureExtractor {
	
	private DescribeImageDense<GrayF32,TupleDesc_F64> featureDescriptor;
	private ArrayList<Double> featureVector = new ArrayList<>();


	public FeatureExtractor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//COMPUTE AND EXTRACT HOG FEATURES
	public ArrayList<Double> ComputeDenseFeatures(GrayF32 input, String featureType){
		//preprocessing the input image
		//input.setHeight(imageSize[0]);
		//input.setWidth(imageSize[1]);
		
		//this.featureVector = new ArrayList<>();
		featureType = featureType.toLowerCase();
		if(featureType.equals("hog")) {
			this.featureDescriptor = FactoryDescribeImageDense.
					hog(new ConfigDenseHoG(),input.getImageType());
		}else if(featureType.equals("sift")) {
			this.featureDescriptor = FactoryDescribeImageDense.
					sift(new ConfigDenseSift(),GrayF32.class);
		}else {
			this.featureDescriptor = FactoryDescribeImageDense.
					surfFast(new ConfigDenseSurfFast(),GrayF32.class);
		}
		// process the image and compute the dense image features
		this.featureDescriptor.process(input);
		
		//populate a final feature vector
		for (int i = 0; i < this.featureDescriptor.getDescriptions().size(); i++) {
		   this.featureVector.add(this.featureDescriptor.getDescriptions().get(i).value[0]);
		}
		return this.featureVector;
		
	}

}
