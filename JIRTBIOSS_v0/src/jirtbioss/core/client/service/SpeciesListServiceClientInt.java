package jirtbioss.core.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SpeciesListServiceClientInt {

	 //Complex Objects
	// void getSpecies(String speciesName);
	 void getImages();
	 void getSpeciesList();
	 void getSpeciesListLkLike(String looksLikeFilter);
	 void speciesLookslikeList();
	 void InsertIdentification(String username, String behavior, String howMany, String youngPrent, String userCommment, String imageId, String species, String colour, String pose, String scale);
	// void getSpeciesListByFilter();
	void getSpeciesNamesByFilter(String filteredString);
	//void getSpeciesDescription(String speciesName);
	void getActiveStudyColors();
	void getActiveStudyBehaviors();
	void getActiveStudyPoses();
	void getActiveStudyScales();
	void getActiveStudy();
}
