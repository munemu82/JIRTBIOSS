package jirtbioss.core.client.service;

import java.util.ArrayList;
import java.util.List;

import jirtbioss.core.client.model.Imageidentity;
import jirtbioss.core.client.model.SpeciesInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SpeciesListServiceAsync {
	 //Complex Objects
	 // void getSpecies(String speciesName, AsyncCallback callback);
	 void getImages(AsyncCallback callback);
	 void getSpeciesList(AsyncCallback callback);
	 //void getSpeciesObjects(AsyncCallback callback);
	 void getSpeciesListLkLike(String looksLikeFilter, AsyncCallback callback);
	 void speciesLookslikeList(AsyncCallback callback);
	 void InsertIdentification(String username, String behavior, String howMany, String youngPrent, String userCommment, String imageId, String species, String colour, String pose, String scale, AsyncCallback callback);
	// void getSpeciesListByFilter(AsyncCallback callbackr);
	void getSpeciesNamesByFilter(String filteredString, AsyncCallback callback);
	//void getSpeciesDescription(String speciesName, AsyncCallback callback);
	void getActiveStudyColors(AsyncCallback callback);
	void getActiveStudyBehaviors(AsyncCallback callback);
	void getActiveStudyPoses(AsyncCallback callback);
	void getActiveStudyScales(AsyncCallback callback);
	void getActiveStudy(AsyncCallback callback);
}
