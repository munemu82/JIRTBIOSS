package jirtbioss.core.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Imageidentity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//fields 
	private List<Imageidentity> speciesRecords;
	private List<Imageidentity> speciesRecordsFilteredName;
	private List<Imageidentity> speciesRecordsAdvancedFilters;
	private List<Imageidentity> speciesRecordsByStudyId;
	
	public List<Imageidentity> getSpeciesRecordsByStudyId() {
		return speciesRecordsByStudyId;
	}
	public void setSpeciesRecordsByStudyId(
			List<Imageidentity> speciesRecordsByStudyId) {
		this.speciesRecordsByStudyId = speciesRecordsByStudyId;
	}
	public List<Imageidentity> getSpeciesRecordsAdvancedFilters() {
		return speciesRecordsAdvancedFilters;
	}
	public void setSpeciesRecordsAdvancedFilters(
			List<Imageidentity> speciesRecordsAdvancedFilters) {
		this.speciesRecordsAdvancedFilters = speciesRecordsAdvancedFilters;
	}
	private long recordId;
	public String getStudyId() {
		return studyId;
	}
	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}
	private String behavior;
	private String imageid;
	private int number;
	private String studyId;
	public List<Imageidentity> getSpeciesRecordsFilteredName() {
		return speciesRecordsFilteredName;
	}
	public void setSpeciesRecordsFilteredName(
			List<Imageidentity> speciesRecordsFilteredName) {
		this.speciesRecordsFilteredName = speciesRecordsFilteredName;
	}
	public ArrayList<Imageidentity> getSpeciesObjects() {
		return speciesObjects;
	}
	public void setSpeciesObjects(ArrayList<Imageidentity> speciesObjects) {
		this.speciesObjects = speciesObjects;
	}
	private String pose;
	private String children;
	private String scale;
	private String species;
	private String color;
	private String looksLike;
	private String insertFlag;
	
	public List<Imageidentity> getSpeciesRecords() {
		return speciesRecords;
	}
	public void setSpeciesRecords(List<Imageidentity> speciesRecords) {
		this.speciesRecords = speciesRecords;
	}
	public String getImageid() {
		return imageid;
	}
	public void setImageid(String imageid) {
		this.imageid = imageid;
	}
	public String getInsertFlag() {
		return insertFlag;
	}
	public void setInsertFlag(String insertFlag) {
		this.insertFlag = insertFlag;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getLooksLike() {
		return looksLike;
	}
	public void setLooksLike(String looksLike) {
		this.looksLike = looksLike;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String[] speciesList;
	
	private ArrayList<String> speciesNames;
	private ArrayList<Imageidentity> speciesObjects;
	
	//public ArrayList<Imageidentity> getSpeciesObjects() {
	//	return speciesObjects;
	//}
	//public void setSpeciesObjects(ArrayList<Imageidentity> speciesObjects) {
		//this.speciesObjects = speciesObjects;
	//}
	public ArrayList<String> getSpeciesNames() {
		return speciesNames;
	}
	public void setSpeciesNames(ArrayList<String> speciesNames) {
		this.speciesNames = speciesNames;
	}
	public String[] getSpeciesList() {
		return speciesList;
	}
	public void setSpeciesList(String[] speciesList) {
		this.speciesList = speciesList;
	}
	public long getRecordId() {
		return recordId;
	}
	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}
	public String getBehavior() {
		return behavior;
	}
	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
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
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getChildren() {
		return children;
	}
	public void setChildren(String string) {
		this.children = string;
	}
		

}
