package jirtbioss.core.client.model;

import java.io.Serializable;

public class SpeciesInfo implements Serializable{
	
	//fields 
	private long recordId;
	private String behavior;
	private int number;
	private String pose;
	private char children;
	private String scale;
	private String species;
	private String color;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	private String[] speciesList;
	
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
	public char getChildren() {
		return children;
	}
	public void setChildren(char children) {
		this.children = children;
	}
		

}
