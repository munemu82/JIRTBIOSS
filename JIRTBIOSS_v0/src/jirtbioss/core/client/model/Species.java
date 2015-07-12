package jirtbioss.core.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Species implements Serializable {
private String speciesId;
private String speciesName;
private String speciesDescription;
private String speciesLooklike1;
private String speciesLooklike2;
private String speciesLooklike3;
private String pose;
private String colour;

private List<Species> filterdSpeciesList;
public List<Species> getFilterdSpeciesList() {
	return filterdSpeciesList;
}
public void setFilterdSpeciesList(List<Species> filterdSpeciesList) {
	this.filterdSpeciesList = filterdSpeciesList;
}
public String getPose() {
	return pose;
}
public void setPose(String pose) {
	this.pose = pose;
}
public String getColour() {
	return colour;
}
public void setColour(String colour) {
	this.colour = colour;
}
public String getScale() {
	return scale;
}
public void setScale(String scale) {
	this.scale = scale;
}
private String scale;
private ArrayList<String> speciesNames;
public ArrayList<String> getSpeciesNames() {
	return speciesNames;
}
public void setSpeciesNames(ArrayList<String> speciesNames) {
	this.speciesNames = speciesNames;
}
private List<Species> listOfSpecies;

//Constructor with 6 fields
public Species(String speciesId, String speciesName, String speciesDescription,
		String speciesLooklike1, String speciesLooklike2,
		String speciesLooklike3) {
	super();
	this.speciesName = speciesName;
	this.speciesDescription = speciesDescription;
	this.speciesLooklike1 = speciesLooklike1;
	this.speciesLooklike2 = speciesLooklike2;
	this.speciesLooklike3 = speciesLooklike3;
	this.speciesId = speciesId;
}
//Constructor with 9 fields
public Species(String speciesId, String speciesName, String speciesDescription , String pose, String colour, String scale,
		String speciesLooklike1, String speciesLooklike2,
		String speciesLooklike3) {
	super();
	this.speciesId = speciesId;
	this.speciesName = speciesName;
	this.speciesDescription = speciesDescription;
	this.speciesLooklike1 = speciesLooklike1;
	this.speciesLooklike2 = speciesLooklike2;
	this.speciesLooklike3 = speciesLooklike3;
	this.pose = pose;
	this.colour = colour;
	this.scale = scale;
	this.speciesNames = speciesNames;
	this.listOfSpecies = listOfSpecies;
}
//Constructor without fields
public Species() {
	super();
	// TODO Auto-generated constructor stub
}

public String getSpeciesId() {
	return speciesId;
}

public void setSpeciesId(String speciesId) {
	this.speciesId = speciesId;
}
public String getSpeciesName() {
	return speciesName;
}
public void setSpeciesName(String speciesName) {
	this.speciesName = speciesName;
}
public String getSpeciesDescription() {
	return speciesDescription;
}
public void setSpeciesDescription(String speciesDescription) {
	this.speciesDescription = speciesDescription;
}
public String getSpeciesLooklike1() {
	return speciesLooklike1;
}
public void setSpeciesLooklike1(String speciesLooklike1) {
	this.speciesLooklike1 = speciesLooklike1;
}
public String getSpeciesLooklike2() {
	return speciesLooklike2;
}
public void setSpeciesLooklike2(String speciesLooklike2) {
	this.speciesLooklike2 = speciesLooklike2;
}
public String getSpeciesLooklike3() {
	return speciesLooklike3;
}
public void setSpeciesLooklike3(String speciesLooklike3) {
	this.speciesLooklike3 = speciesLooklike3;
}
public List<Species> getListOfSpecies() {
	return listOfSpecies;
}
public void setListOfSpecies(List<Species> listOfSpecies) {
	this.listOfSpecies = listOfSpecies;
}
}
