package jirtbioss.core.client.model;

import java.io.Serializable;
import java.util.List;

public class SpeciesConfiguration implements Serializable{
//configuration fields
	private int configurationId;
	private String configurationName;
	private String configurationType;
	private List<String> looksLikeList;
	public List<String> getLooksLikeList() {
		return looksLikeList;
	}
	public void setLooksLikeList(List<String> looksLikeList) {
		this.looksLikeList = looksLikeList;
	}
	private String configurationStatus;
	private List<SpeciesConfiguration> allConfigList;
	private List<String> speciesConfigList;
	private List<String> speciesFilterConfigList;
	public int getConfigurationId() {
		return configurationId;
	}
	public void setConfigurationId(int configurationId) {
		this.configurationId = configurationId;
	}
	public String getConfigurationName() {
		return configurationName;
	}
	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}
	public String getConfigurationType() {
		return configurationType;
	}
	public void setConfigurationType(String configurationType) {
		this.configurationType = configurationType;
	}
	public String getConfigurationStatus() {
		return configurationStatus;
	}
	public void setConfigurationStatus(String configurationStatus) {
		this.configurationStatus = configurationStatus;
	}
	public List<SpeciesConfiguration> getAllConfigList() {
		return allConfigList;
	}
	public void setAllConfigList(List<SpeciesConfiguration> allConfigList) {
		this.allConfigList = allConfigList;
	}
	public List<String> getSpeciesConfigList() {
		return speciesConfigList;
	}
	public void setSpeciesConfigList(List<String> speciesConfigList) {
		this.speciesConfigList = speciesConfigList;
	}
	public List<String> getSpeciesFilterConfigList() {
		return speciesFilterConfigList;
	}
	public void setSpeciesFilterConfigList(List<String> speciesFilterConfigList) {
		this.speciesFilterConfigList = speciesFilterConfigList;
	}
}
