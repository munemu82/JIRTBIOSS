package jirtbioss.core.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Study implements Serializable{
private String studyId;
private String studyTitle;
private String studyDetails;
private Date studyDateStart;
private Date studyDateFinish;
private String speciesName;
private String colour;
private String status;
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
private int scaleId;
private String scaleStatus;
private int behaviorId;
public int getScaleId() {
	return scaleId;
}
public void setScaleId(int scaleId) {
	this.scaleId = scaleId;
}
public String getScaleStatus() {
	return scaleStatus;
}
public void setScaleStatus(String scaleStatus) {
	this.scaleStatus = scaleStatus;
}
private int poseId;
private int colourId;
private String colourStatus;
public int getColourId() {
	return colourId;
}
public void setColourId(int colourId) {
	this.colourId = colourId;
}
public String getColourStatus() {
	return colourStatus;
}
public void setColourStatus(String colourStatus) {
	this.colourStatus = colourStatus;
}
public int getPoseId() {
	return poseId;
}
public void setPoseId(int poseId) {
	this.poseId = poseId;
}
public int getBehaviorId() {
	return behaviorId;
}
public void setBehaviorId(int behaviorId) {
	this.behaviorId = behaviorId;
}
private String poseStatus;
public String getPoseStatus() {
	return poseStatus;
}
public void setPoseStatus(String poseStatus) {
	this.poseStatus = poseStatus;
}
private String behavior;
private String pose;
private String behaviorStatus;
public String getBehaviorStatus() {
	return behaviorStatus;
}
public void setBehaviorStatus(String behaviorStatus) {
	this.behaviorStatus = behaviorStatus;
}
private String scale;
private ArrayList<String> colours;
private ArrayList<String> behaviors;
private ArrayList<String> poses;
private ArrayList<String> scales;
private List<Study> listOfStudyConfig;

public List<Study> getListOfStudyConfig() {
	return listOfStudyConfig;
}
public void setListOfStudyConfig(List<Study> listOfStudyConfig) {
	this.listOfStudyConfig = listOfStudyConfig;
}
public String getColour() {
	return colour;
}
public void setColour(String colour) {
	this.colour = colour;
}
public String getBehavior() {
	return behavior;
}
public void setBehavior(String behavior) {
	this.behavior = behavior;
}
public String getPose() {
	return pose;
}
public void setPose(String pose) {
	this.pose = pose;
}
public String getScale() {
	return scale;
}
public void setScale(String scale) {
	this.scale = scale;
}
public ArrayList<String> getColours() {
	return colours;
}
public void setColours(ArrayList<String> colours) {
	this.colours = colours;
}
public ArrayList<String> getBehaviors() {
	return behaviors;
}
public void setBehaviors(ArrayList<String> behaviors) {
	this.behaviors = behaviors;
}
public ArrayList<String> getPoses() {
	return poses;
}
public void setPoses(ArrayList<String> poses) {
	this.poses = poses;
}
public ArrayList<String> getScales() {
	return scales;
}
public void setScales(ArrayList<String> scales) {
	this.scales = scales;
}
public String getSpeciesName() {
	return speciesName;
}
public void setSpeciesName(String speciesName) {
	this.speciesName = speciesName;
}
public List<Study> getListOfStudies() {
	return listOfStudies;
}
public void setListOfStudies(List<Study> listOfStudies) {
	this.listOfStudies = listOfStudies;
}
private ArrayList<String> studyIds;
private List<Study> listOfStudies;

public String getStudyId() {
	return studyId;
}
public void setStudyId(String studyId) {
	this.studyId = studyId;
}
public String getStudyTitle() {
	return studyTitle;
}
public void setStudyTitle(String studyTitle) {
	this.studyTitle = studyTitle;
}
public String getStudyDetails() {
	return studyDetails;
}
public void setStudyDetails(String studyDetails) {
	this.studyDetails = studyDetails;
}
public Date getStudyDateStart() {
	return studyDateStart;
}
public void setStudyDateStart(Date studyDateStart) {
	this.studyDateStart = studyDateStart;
}
public Date getStudyDateFinish() {
	return studyDateFinish;
}
public void setStudyDateFinish(Date studyDateFinish) {
	this.studyDateFinish = studyDateFinish;
}
public ArrayList<String> getStudyIds() {
	return studyIds;
}
public void setStudyIds(ArrayList<String> studyIds) {
	this.studyIds = studyIds;
}
}
