package jirtbioss.core.client.service;

import java.util.List;

import jirtbioss.core.client.model.Imageidentity;
import jirtbioss.core.client.model.Study;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("speciesreportservice")
public interface SpeciesReportService extends RemoteService{

	Imageidentity getSpeciesObjects();
	String exportToCsv(String filter, String filterType);
	String exportToCsvByStudyId(String studyId);
	String exportToCsvBySpeciesName(String speciesName);
	Study getStudyIds();
	Imageidentity getSpeciesByName(String speciesName);
	Imageidentity getSpeciesByAdvancedFilters(String studyId, String speciesName, String NumOfRecord);
	Imageidentity getSpeciesByStudyId(String studyId);
}
