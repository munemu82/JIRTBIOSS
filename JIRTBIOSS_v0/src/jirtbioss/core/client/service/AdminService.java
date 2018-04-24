package jirtbioss.core.client.service;

import java.util.ArrayList;
import java.util.Date;

import jirtbioss.core.client.model.ImagesList;
import jirtbioss.core.client.model.Species;
import jirtbioss.core.client.model.SpeciesConfiguration;
import jirtbioss.core.client.model.Study;
import jirtbioss.core.client.model.Users;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("adminservice")
public interface AdminService extends RemoteService{
	Users getListOfusers();
	//Study getStudyIds();
	Study getListOfstudies();
	
	public String saveUser(String username, String password, String firstname, String lastname, String email, int userId, String userAccessLevel, String typeOfSave);
	public String saveStudy(String studyId, String studyTitle, String studyDesc, String studyStartDate, String studyEndDate, String studySpecies, String typeOfSave);
	public SpeciesConfiguration getAllConfigurations();
	public Species importCsv(String fileName);
	public String saveSpecies(String speciesID, String speciesName, String speciesDesc, String speciesLookslike1, String speciesLookslike2, String speciesLookslike3);
	public Species getAllSpecies();
	public String saveEditedSpecies(String speciesID, String speciesName, String speciesDesc, String speciesLookslike1, String speciesLookslike2, String speciesLookslike3);
	public String saveLooksLike(String looksLikeValue);
	public Study getStudyBehavConfigs(String studyId);
	public Study getStudyPoseConfigs(String studyId);
	public Study getStudyColourConfigs(String studyId);
	public Study getStudyScaleConfigs(String studyId);
	public String saveStudyBehavior(int behaviorId, String behaviorStatus);
	public String saveStudyPose(int poseId, String poseStatus);
	public String saveStudyColour(int colourId, String colourStatus);
	public String saveStudyScale(int scaleId, String scaleStatus);
	public String saveStudyConfig(String studyConfigType, String studyId, String ConfigName);
	public String deleteLookslikeConfig(int lookslikeId);
	public 	ImagesList getImageNames();
	public String activateStudy(String studyId);
	public String deActivateStudy(String studyId);
	public String deactivateUser(int userId);
	public String activateUser(String username);
	public String extractFeatures(String featureType, String classLabel);
	
}
