package jirtbioss.core.client.service;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AdminServiceClientInt {
	void getListOfusers();
	//void getStudyIds();
	void saveUser(String username, String password, String firstname, String lastname, String email, int userId, String userAccessLevel, String typeOfSave);
	void getListOfstudies();
	void saveStudy(String studyId, String studyTitle, String studyDesc, String studyStartDate, String studyEndDate, String studySpecies, String typeOfSave);
	void getAllConfigurations();
	void importCsv(String fileName);
	void saveSpecies(String speciesID, String speciesName, String speciesDesc, String speciesLookslike1, String speciesLookslike2, String speciesLookslike3);
	void getAllSpecies();
	void saveEditedSpecies(String speciesID, String speciesName, String speciesDesc, String speciesLookslike1, String speciesLookslike2, String speciesLookslike3);
	void saveLooksLike(String looksLikeValue);
	void getStudyBehavConfigs(String studyId);
	void getStudyPoseConfigs(String studyId);
	void getStudyColourConfigs(String studyId);
	void getStudyScaleConfigs(String studyId);
	void saveStudyBehavior(int behaviorId, String behaviorStatus);
	void saveStudyPose(int poseId, String poseStatus);
	void saveStudyColour(int colourId, String colourStatus);
	void saveStudyScale(int scaleId, String scaleStatus);
	void saveStudyConfig(String studyConfigType, String studyId, String ConfigName);
	void deleteLookslikeConfig(int lookslikeId);
	void getImageNames();
	void activateStudy(String studyId);
	void deActivateStudy(String studyId);
	void deactivateUser(int userId);
	void activateUser(String username);
}
