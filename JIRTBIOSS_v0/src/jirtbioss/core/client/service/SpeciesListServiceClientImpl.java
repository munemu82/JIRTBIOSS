package jirtbioss.core.client.service;

import jirtbioss.core.client.IdentifyPage;
import jirtbioss.core.client.model.Imageidentity;
import jirtbioss.core.client.model.ImagesList;
import jirtbioss.core.client.model.Species;
import jirtbioss.core.client.model.SpeciesConfiguration;
import jirtbioss.core.client.model.SpeciesInfo;
import jirtbioss.core.client.model.Study;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;


public class SpeciesListServiceClientImpl implements SpeciesListServiceClientInt{

	private IdentifyPage identifyUI;
	private SpeciesListServiceAsync service;
	//private CellTableExample1 cellTableUI;
	
	
	public SpeciesListServiceClientImpl(String url){
		//just a debug
		System.out.println(url);
		this.service = GWT.create(SpeciesListService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		//initialize client main GUI
		this.identifyUI = new IdentifyPage(this);	//this here means give me the copy of the MainGUI
		//this.cellTableUI = new CellTableExample1(this);
	}
	/*@Override
	public void getSpecies(String speciesName) {
		this.service.getSpecies(speciesName, new DefaultCallback());
		
	}*/

	@Override
	public void getImages() {
		this.service.getImages(new DefaultCallback());
		
	}
	@Override
	public void getSpeciesList() {
		this.service.getSpeciesList(new DefaultCallback());		
	}



@Override
public void getSpeciesListLkLike(String looksLikeFilter) {
	this.service.getSpeciesListLkLike(looksLikeFilter, new DefaultCallback());
	
}

//implement insert identification record 
@Override
public void InsertIdentification(String username, String behavior, String howMany,
		String youngPrent, String userCommment, String imageId, String species, String colour, String pose, String scale) {
		this.service.InsertIdentification(username, behavior, howMany, youngPrent, userCommment, imageId, species, colour, pose, scale, new DefaultCallback());
	
}
@Override
public void getSpeciesNamesByFilter(String filteredString) {
	this.service.getSpeciesNamesByFilter(filteredString, new SpeciesDetailsCallback());
}
@Override
public void speciesLookslikeList() {
	this.service.speciesLookslikeList(new DefaultCallback());
}

@Override
public void getActiveStudyColors() {
	this.service.getActiveStudyColors(new SpeciesDetailsCallback());
}

@Override
public void getActiveStudyBehaviors() {
	this.service.getActiveStudyBehaviors(new DefaultCallback());
}
@Override
public void getActiveStudyPoses() {
	this.service.getActiveStudyPoses(new StudyActivePosesCallback());
	
}
@Override
public void getActiveStudyScales() {
	this.service.getActiveStudyScales(new StudyActiveScalesCallback());
}
@Override
public void getActiveStudy() {
	this.service.getActiveStudy(new StudyActivePosesCallback());
}
//Create a gui so that can be attached in the Identity class, this is the only way we can add client implementation
		public IdentifyPage getIdentifyUI(){
			return this.identifyUI;
		}

	
private class DefaultCallback implements AsyncCallback{

		
		@Override
		public void onFailure(Throwable caught) {
		//this method is triggered when something goes wrong	
			System.out.println("Error has occurred");
		}

		@Override
		public void onSuccess(Object result) {
			 if(result instanceof Species){
				 Species list = (Species) result;
				 	identifyUI.displaySpeciesListInfo(list);
				 }
			 else if(result instanceof ImagesList){
					ImagesList list = (ImagesList) result;
					identifyUI.displayNextImage(list);
				}
			 else if(result instanceof String){
				 String list = (String) result;
					identifyUI.displaySpeciesListInfoLooksLike(list);
			}else if(result instanceof String){
				String insertList = (String) result;
				identifyUI.insertIdentification(insertList);
			}else if(result instanceof SpeciesConfiguration){
				SpeciesConfiguration listOfLookslike = (SpeciesConfiguration) result;
				identifyUI.displayLookslikeList(listOfLookslike);
			}else if(result instanceof Study){
				Study studyConfigs = (Study) result;
				identifyUI.setStudyBehaviors(studyConfigs);
			}
			
		}
		
	}

	private class SpeciesDetailsCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			//this method is triggered when something goes wrong	
			System.out.println("Error has occurred");
		}

		@Override
		public void onSuccess(Object result) {
			 if(result instanceof Species){
				Species speciesSQL = (Species) result;
				identifyUI.displaySpeciesListByFilter(speciesSQL);
			}else if(result instanceof Study){
				Study studyConfigs = (Study) result;
				identifyUI.setStudyColors(studyConfigs);
			}
			
		}
		
	}
	private class StudyActivePosesCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			//this method is triggered when something goes wrong	
			System.out.println("Error has occurred");
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Study){
				Study studyConfigs = (Study) result;
				identifyUI.setStudyPoses(studyConfigs);
			}else if(result instanceof String){
				String aStudyId = (String) result;
				identifyUI.setActiveStudyId(aStudyId);
			}
			
		}
		
	}
	private class StudyActiveScalesCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			//this method is triggered when something goes wrong	
			System.out.println("Error has occurred");
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Study){
				Study studyConfigs = (Study) result;
				identifyUI.setStudyScales(studyConfigs);
			}
			
		}
		
	}

}
