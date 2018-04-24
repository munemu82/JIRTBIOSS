package jirtbioss.core.client.service;

import java.util.Date;

import jirtbioss.core.client.Administration;
import jirtbioss.core.client.model.ImagesList;
import jirtbioss.core.client.model.Species;
import jirtbioss.core.client.model.SpeciesConfiguration;
import jirtbioss.core.client.model.SpeciesInfo;
import jirtbioss.core.client.model.Study;
import jirtbioss.core.client.model.Users;
import jirtbioss.core.client.ui.AddLooksLike;
import jirtbioss.core.client.ui.SpeciesForms;
import jirtbioss.core.client.ui.StudyConfig;
import jirtbioss.core.client.ui.StudyDetails;
import jirtbioss.core.client.ui.UserDetails;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class AdminServiceClientImpl implements AdminServiceClientInt{

	//Global variables
	private Administration adminGui;
	private AdminServiceAsync service;
	private UserDetails userFormGui;
	private StudyDetails studyFormGui;
	private SpeciesForms speciesFormGui;
	private AddLooksLike speciesLooksLikeFormGui;
	private StudyConfig studyConfigGui;
	
	//Constructor
	public AdminServiceClientImpl(String url){
		//just a debug
		System.out.println(url);
		this.service = GWT.create(AdminService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		//initialize client main GUI
		this.adminGui = new Administration(this);	//this here means give me the copy of the MainGUI
		this.userFormGui = new UserDetails(this);
		this.studyFormGui = new StudyDetails(this);
		this.speciesFormGui = new SpeciesForms(this);
		this.speciesLooksLikeFormGui = new AddLooksLike(this);
		this.studyConfigGui = new StudyConfig(this);
	}
	@Override
	public void getListOfusers() {
		this.service.getListOfusers(new AdminCallback());
		
	}
	@Override
	public void saveUser(String username, String password, String firstname,
			String lastname, String email, int userId, String userAccessLevel, String typeOfSave) {
			this.service.saveUser(username, password, firstname, lastname, email, userId, userAccessLevel, typeOfSave, new AdminCallback());
		
	}
	@Override
	public void getListOfstudies() {
		this.service.getListOfstudies(new AdminCallback());
		
	}
	@Override
	public void saveStudy(String studyId, String studyTitle, String studyDesc, String studyStartDate,
			String studyEndDate, String studySpecies, String typeOfSave) {
			this.service.saveStudy(studyId, studyTitle,studyDesc, studyStartDate, studyEndDate, studySpecies, typeOfSave, new AdminAsyncImpl());
	}
	@Override
	public void getAllConfigurations() {
		this.service.getAllConfigurations(new AdminCallback());
		
	}
	@Override
	public void importCsv(String fileName) {
		this.service.importCsv(fileName, new AdminAsyncImpl());
	}
	@Override
	public void saveSpecies(String speciesID, String speciesName,
			String speciesDesc, String speciesLookslike1, String speciesLookslike2,
			String speciesLookslike3) {
		this.service.saveSpecies(speciesID, speciesName, speciesDesc, speciesLookslike1, speciesLookslike2, speciesLookslike3, new AdminAsyncConfiguration());
		
	}
	@Override
	public void getAllSpecies() {
		this.service.getAllSpecies(new AdminAsyncConfiguration());
	}
	@Override
	public void saveEditedSpecies(String speciesID, String speciesName,
			String speciesDesc, String speciesLookslike1,
			String speciesLookslike2, String speciesLookslike3) {
		this.service.saveEditedSpecies(speciesID, speciesName, speciesDesc, speciesLookslike1, speciesLookslike2, speciesLookslike3, new editSpeciesAsyc());
		
	}
	@Override
	public void saveLooksLike(String looksLikeValue) {
		this.service.saveLooksLike(looksLikeValue, new SaveLooksLikeAsyc());		
	}
	@Override
	public void saveStudyBehavior(int behaviorId, String behaviorStatus) {
		this.service.saveStudyBehavior(behaviorId, behaviorStatus, new StudyConfigAsync());
		
	}
	@Override
	public void getStudyBehavConfigs(String studyId) {
		this.service.getStudyBehavConfigs(studyId, new StudyConfigAsync());
		
	}

	@Override
	public void getStudyPoseConfigs(String studyId) {
	this.service.getStudyPoseConfigs(studyId,new AdminAsyncConfiguration());
		
	}
	@Override
	public void saveStudyPose(int poseId, String poseStatus) {
		this.service.saveStudyPose(poseId, poseStatus, new StudyConfigAsync());
	}
	@Override
	public void getStudyColourConfigs(String studyId) {
		this.service.getStudyColourConfigs(studyId, new StudyColourConfigAsync());
		
	}
	@Override
	public void getStudyScaleConfigs(String studyId) {
		this.service.getStudyScaleConfigs(studyId, new StudyScaleConfigAsync());
	}
	

	@Override
	public void saveStudyColour(int colourId, String colourStatus) {
		this.service.saveStudyColour(colourId, colourStatus, new StudyColourConfigAsync());
	}
	@Override
	public void saveStudyScale(int scaleId, String scaleStatus) {
		this.service.saveStudyScale(scaleId, scaleStatus, new StudyScaleConfigAsync());	
	}
	@Override
	public void saveStudyConfig(String studyConfigType, String studyId,
			String ConfigName) {
		this.service.saveStudyConfig(studyConfigType, studyId, ConfigName, new StudyConfigSaveAsync());
	}
	@Override
	public void deleteLookslikeConfig(int lookslikeId) {
		this.service.deleteLookslikeConfig(lookslikeId, new deleteLookslikeConfig());
	}
	@Override
	public void getImageNames() {
		this.service.getImageNames(new AdminAsyncConfiguration());
	}
	@Override
	public void activateStudy(String studyId) {
		this.service.activateStudy(studyId, new activateStudyAsync());
	}

	@Override
	public void deActivateStudy(String studyId) {
		this.service.deActivateStudy(studyId, new DeactivateStudyAsync());
	}
	@Override
	public void deactivateUser(int userId) {
		this.service.deactivateUser(userId, new DeactivateUserAsync());
	}
	
	@Override
	public void activateUser(String username) {
		this.service.activateUser(username, new ActivateUserAsync());
	}
	@Override
	public void extractFeatures(String featureType, String classLabel) {
		this.service.extractFeatures(featureType, classLabel, new ExtractFeaturesAsync());
		
	}
	
	//ADMIN GUIs
	public Administration getAdminGui(){
		return this.adminGui;
	}
	public UserDetails getUserFormGui(){
		return this.userFormGui;
	}
	public StudyDetails getStudyFormGui(){
		return this.studyFormGui;
	}
	public SpeciesForms getSpeciesFormGui(){
		return this.speciesFormGui;
	}
	public AddLooksLike getSpeciesLookslikeFormGui(){
		return this.speciesLooksLikeFormGui;
	}
	public StudyConfig getStudyConfigGui(){
		return this.studyConfigGui;
	}

	
	//class to implement result from the server implementations on the client
private class AdminCallback implements AsyncCallback{

		
		@Override
		public void onFailure(Throwable caught) {
		//this method is triggered when something goes wrong	
			System.out.println("Error has occurred");
		}

		@Override
		public void onSuccess(Object result) {
	 	 if(result instanceof Users){						//1 get all users and their information
				 Users userList = (Users) result;
				adminGui.displayUsers(userList);
			
			}else if(result instanceof String){				//2 save user details as per the form in UserDetails.java file
				String saveFlag = (String) result;
				userFormGui.saveUser(saveFlag);
			}else if(result instanceof String){					//3 same as 2, use same RPC call but change it to update instead of save
				String updateFlag = (String) result;
				adminGui.displayUpdateUserResult(updateFlag);
			}else if(result instanceof Study){
				Study listOfStudies = (Study) result;
				adminGui.displayStudyDetails(listOfStudies);
			}else if(result instanceof SpeciesConfiguration){
				SpeciesConfiguration currentConfigurations = (SpeciesConfiguration) result;
				adminGui.displayAllConfigurations(currentConfigurations);
			}
			
		}
		
	}
//another implementation of callback for duplicate instanceof where we have more than instanceof String, object, etc...
private class AdminAsyncImpl implements AsyncCallback{

	@Override
	public void onFailure(Throwable caught) {
		//this method is triggered when something goes wrong	
		System.out.println("Error has occurred");
		
	}
	@Override
	public void onSuccess(Object result) {
		if(result instanceof String){
			String saveStatus = (String) result;
			studyFormGui.saveStudy(saveStatus);
		}else if(result instanceof Species){
			Species csvList = (Species) result;
			speciesFormGui.displayCsvImport(csvList);
		}
		
	}
	}
	private class AdminAsyncConfiguration implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			//this method is triggered when something goes wrong	
			System.out.println("Error has occurred");
						
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String){
				String serverMessage = (String) result;
				speciesFormGui.displaySaveSpeciesResult(serverMessage);
			}else if(result instanceof Species){
				Species speciesList = (Species) result;
				adminGui.displaySpecies(speciesList);
				adminGui.populateSpeciesListBox(speciesList);
			}else if(result instanceof Study){
				Study posesConfigs = (Study) result;
				studyConfigGui.displayPoses(posesConfigs);
			}else if(result instanceof ImagesList){
				ImagesList currentList = (ImagesList) result;
				adminGui.displayLoadedImages(currentList);
			}
			
		}
		
	}

	private class editSpeciesAsyc implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			//this method is triggered when something goes wrong	
			System.out.println("Error has occurred");
						
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String){
				String editSpeciesStatus = (String) result;
				adminGui.displayChangeSaveResult(editSpeciesStatus);
			}
			
		}
	}
	private class SaveLooksLikeAsyc implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			//this method is triggered when something goes wrong	
			System.out.println("Error has occurred");
						
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String){
				String saveStatus = (String) result;
				speciesLooksLikeFormGui.displaySaveLookslike(saveStatus);
			}
			
		}
	}
	private class StudyConfigAsync implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			//this method is triggered when something goes wrong	
			System.out.println("Error has occurred");
						
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Study){
				Study studyConfiglist = (Study) result;
				studyConfigGui.displayBehavior(studyConfiglist);
			}else if(result instanceof String){
				String theresult = (String) result;
				studyConfigGui.displaySaveBehaviorResult(theresult);
			}
			else if(result instanceof String){
				String theresult=(String) result;
				studyConfigGui.displaySavePoseResult(theresult);
			}
			
		}
	}
	private class StudyColourConfigAsync implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Error occurred communicating with the server");
		}

		@Override
		public void onSuccess(Object result) {
		  if(result instanceof Study){
				Study theresult = (Study) result;
				studyConfigGui.displayColour(theresult);
			}else if(result instanceof String){
				String theresult = (String) result;
				studyConfigGui.displaySaveColourResult(theresult);
			}
		}
		
	}

	private class StudyScaleConfigAsync implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Error occurred communicating with the server");			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Study){
				Study theresult = (Study) result;
				studyConfigGui.displayScales(theresult);
			}else if(result instanceof String){
				String theresult = (String) result;
				studyConfigGui.displaySaveScaleResult(theresult);
			}
		}
		
	}
	private class StudyConfigSaveAsync implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Error occurred communicating with the server");
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String){
				String saveStatus = (String) result;
				studyConfigGui.displaySaveConfigStatus(saveStatus);
			}else if(result instanceof Study){
				Study listofstudies = (Study) result;
				studyConfigGui.displayStudySuggestion(listofstudies);
			}
		}
		
	}
	private class deleteLookslikeConfig implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Error occurred communicating with the server");
		}

		@Override
		public void onSuccess(Object result) {
			 if(result instanceof String){
				String deleteResult = (String) result;
				adminGui.displayDeleteConfigConfirm(deleteResult);
		}
	}
		
	}
	private class activateStudyAsync implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Error occurred communicating with the server");	
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String){
				String activeStudyStatus = (String) result;
				adminGui.displayStudyActive(activeStudyStatus);
			}
						
		}
		
	}
	private class DeactivateStudyAsync implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Error occurred communicating with the server");	
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String){
				String deactiveStudyStatus = (String) result;
				adminGui.displayStudyDeActive(deactiveStudyStatus);
			}
						
		}
		
	}
	private class DeactivateUserAsync implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Error occurred communicating with the server");	
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String){
				String deactiveUserStatus = (String) result;
				adminGui.displayUserDeActive(deactiveUserStatus);
			}
						
		}
		
	}
	private class ActivateUserAsync implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Error occurred communicating with the server");	
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String){
				String activeUserStatus = (String) result;
				adminGui.displayUserActive(activeUserStatus);
			}
						
		}
		
	}
	
	//FEATURE EXTRACTION CLIENT IMPLEMENTATION
	private class ExtractFeaturesAsync implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Error occurred extracting features from image with the server");
			adminGui.featureExtractStatus("Server Error occurred extracting features from image");
			
		}

		@Override
		public void onSuccess(Object result) {
			// TODO Auto-generated method stub
			if(result instanceof String){
				String featExtractStatus = (String) result;
				adminGui.featureExtractStatus(featExtractStatus);
			}
		}
		
	}


}
