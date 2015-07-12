package jirtbioss.core.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SpeciesReportServiceInt {
	void getSpeciesObjects();
	void exportToCsv(String filter, String filterType);
	void exportToCsvByStudyId(String studyId);
	void exportToCsvBySpeciesName(String speciesName);
	void getStudyIds();
	void getSpeciesByName(String speciesName);
	void getSpeciesByAdvancedFilters(String studyId, String speciesName, String NumOfRecord);
	void getSpeciesByStudyId(String studyId);
}
