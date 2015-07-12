package jirtbioss.core.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SpeciesReportServiceAsync {
	 void getSpeciesObjects(AsyncCallback callback);
	 void exportToCsv(String filter, String filterType, AsyncCallback callback);
	 void exportToCsvByStudyId(String studyId, AsyncCallback callback);
	 void exportToCsvBySpeciesName(String speciesName, AsyncCallback callback);
	 void getStudyIds(AsyncCallback callback);
	 void getSpeciesByName(String speciesName, AsyncCallback callback);
	 void getSpeciesByAdvancedFilters(String studyId, String speciesName, String NumOfRecord, AsyncCallback callback);
	 void getSpeciesByStudyId(String studyId, AsyncCallback callback);
}
