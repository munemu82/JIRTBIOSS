package jirtbioss.core.client.service;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import jirtbioss.core.client.model.Study;
import jirtbioss.core.client.model.Users;

public interface AdminServiceAsync {
	void getListOfusers(AsyncCallback callback);
	//void getStudyIds(AsyncCallback callback);
	void saveUser(String username, String password, String firstname, String lastname, String email, int userId, String userAccessLevel,String typeOfSave, AsyncCallback callback);
	void getListOfstudies(AsyncCallback callback);
	void saveStudy(String studyId, String studyTitle, String studyDesc, String studyStartDate, String studyEndDate, String studySpecies, String typeOfSave, AsyncCallback callback);
	void getAllConfigurations(AsyncCallback callback);
	void  importCsv(String fileName, AsyncCallback callback);
	void  saveSpecies(String speciesID, String speciesName, String speciesDesc, String speciesLookslike1, String speciesLookslike2, String speciesLookslike3, AsyncCallback callback);
	void getAllSpecies(AsyncCallback callback);
	void saveEditedSpecies(String speciesID, String speciesName, String speciesDesc, String speciesLookslike1, String speciesLookslike2, String speciesLookslike3, AsyncCallback callback);
	void saveLooksLike(String looksLikeValue, AsyncCallback callback);
	void getStudyBehavConfigs(String studyId, AsyncCallback callback);
	void getStudyPoseConfigs(String studyId, AsyncCallback callback);
	void getStudyColourConfigs(String studyId, AsyncCallback callback);
	void getStudyScaleConfigs(String studyId, AsyncCallback callback);
	void saveStudyBehavior(int behaviorId, String behaviorStatus, AsyncCallback callback);
	void saveStudyPose(int poseId, String poseStatus, AsyncCallback callback);
	void saveStudyColour(int colourId, String colourStatus, AsyncCallback callback);
	void saveStudyScale(int scaleId, String scaleStatus, AsyncCallback callback);
	void saveStudyConfig(String studyConfigType, String studyId, String ConfigName, AsyncCallback callback);
	void deleteLookslikeConfig(int lookslikeId, AsyncCallback callback);
	void getImageNames(AsyncCallback callback); 
	void activateStudy(String studyId, AsyncCallback callback);
	void deActivateStudy(String studyId, AsyncCallback callback);
	void deactivateUser(int userId, AsyncCallback callback);
	void activateUser(String username, AsyncCallback callback);
}
