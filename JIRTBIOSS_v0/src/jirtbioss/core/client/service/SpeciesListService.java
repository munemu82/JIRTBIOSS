package jirtbioss.core.client.service;

import java.util.ArrayList;
import java.util.List;

import jirtbioss.core.client.model.Imageidentity;
import jirtbioss.core.client.model.ImagesList;
import jirtbioss.core.client.model.Species;
import jirtbioss.core.client.model.SpeciesConfiguration;
import jirtbioss.core.client.model.SpeciesInfo;
import jirtbioss.core.client.model.Study;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("specieslistservice")
public interface SpeciesListService extends RemoteService{

	//complex objects RPC
	
		//SpeciesInfo getSpecies(String speciesName);
		ImagesList getImages();
		Species getSpeciesList();
		Species getSpeciesListLkLike(String looksLikeFilter);
		SpeciesConfiguration speciesLookslikeList();
		String InsertIdentification(String username, String behavior, String howMany, String youngPrent, String userCommment, String imageId, String species, String colour, String pose, String scale);
		// need to all the species and all the fileds then perform filter by scale, pose and color on the client side
		//Imageidentity getSpeciesListByFilter();
		Species getSpeciesNamesByFilter(String filteredString);
		//Species getSpeciesDescription(String speciesName);
		Study getActiveStudyColors();
		Study getActiveStudyBehaviors();
		Study getActiveStudyPoses();
		Study getActiveStudyScales();
		String getActiveStudy();
}
