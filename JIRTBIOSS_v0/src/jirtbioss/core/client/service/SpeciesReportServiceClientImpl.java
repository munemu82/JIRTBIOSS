package jirtbioss.core.client.service;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import jirtbioss.core.client.SpeciesCellTable;
import jirtbioss.core.client.model.Imageidentity;
import jirtbioss.core.client.ui.AdvancedReport;

public class SpeciesReportServiceClientImpl implements SpeciesReportServiceInt, LoginServiceInt{

	//define global variables
	private SpeciesCellTable celltableUI;
	private SpeciesReportServiceAsync serviceimpl;
	private AdvancedReport advanceReportUI;
	//constructur of client implementation
	public SpeciesReportServiceClientImpl(String url){
		//just a debug
		System.out.println(url);
		//Create RPC Proxy
		this.serviceimpl = GWT.create(SpeciesReportService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.serviceimpl;
		endpoint.setServiceEntryPoint(url);
		
		//instantiate the celltable Ui
		this.celltableUI = new SpeciesCellTable(this);
		advanceReportUI = new AdvancedReport(this);
	}
	@Override
	public void getSpeciesObjects() {
		this.serviceimpl.getSpeciesObjects(new SpeciesReportCallback());
		
	}
	//this is for exporting table data to CSV file
	@Override
	public void exportToCsv(String filter, String filterType) {
		this.serviceimpl.exportToCsv(filter, filterType, new SpeciesReportCallback());
		
	}
	@Override
	public void getStudyIds() {
		this.serviceimpl.getStudyIds(new SpeciesReportCallback());
		
	}
	@Override
	public void getSpeciesByName(String speciesName) {
		this.serviceimpl.getSpeciesByName(speciesName, new SpeciesFilterdReportCallback());
		
	}
	@Override
	public void getSpeciesByAdvancedFilters(String studyId, String speciesName,
			String NumOfRecord) {
		this.serviceimpl.getSpeciesByAdvancedFilters(studyId, speciesName, NumOfRecord, new AdvancedSpeciesFilterReportCallback());
		
	}
	//Define how/where to display the result from the RPC callback from the server
	public SpeciesCellTable getSpeciesCellTableUI(){
		return this.celltableUI;
	}
	public AdvancedReport getSpeciesSearchUI(){
		return this.advanceReportUI;
	}
	@Override
	public void getSpeciesByStudyId(String studyId) {
		this.serviceimpl.getSpeciesByStudyId(studyId, new SpeciesFilterByStudyIdCallback());
		
	}
	@Override
	public void exportToCsvByStudyId(String studyId) {
		this.serviceimpl.exportToCsvByStudyId(studyId, new SpeciesReportByStudyIdCallback());
	}
	@Override
	public void exportToCsvBySpeciesName(String speciesName) {
		this.serviceimpl.exportToCsvBySpeciesName(speciesName, new SpeciesReportBySpeciesNameCallback());
	}
	//Define Async callback class and methods
	private class SpeciesReportCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			//throw an error message when something goes wrong in the server
			Window.alert("Error has occurred in the server processing, try again later..");
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Imageidentity){
				Imageidentity speciesList = (Imageidentity) result;
				celltableUI.displaySpeciesRecords(speciesList);
			}else if(result instanceof String){
				String exportResult = (String) result;
				celltableUI.csvExportSuccess(exportResult);
			}
			
		}
		
	}
	private class SpeciesFilterdReportCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			//throw an error message when something goes wrong in the server
			Window.alert("Error has occurred in the server processing, try again later..");
			
		}

		@Override
		public void onSuccess(Object result) {
			 if(result instanceof Imageidentity){
				Imageidentity speciesByNameList = (Imageidentity) result;
				celltableUI.displayReportBySpeciesName(speciesByNameList);
			}
			
		}
		
	}
	private class AdvancedSpeciesFilterReportCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			//throw an error message when something goes wrong in the server
			Window.alert("Error has occurred in the server processing advanced filters, try again later..");
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Imageidentity){
				Imageidentity speciesList = (Imageidentity) result;
				advanceReportUI.displayAdvancedReport(speciesList);
			}			
		}
		
	}
	private class SpeciesFilterByStudyIdCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			//throw an error message when something goes wrong in the server
			Window.alert("Error has occurred in the server processing advanced filters, try again later..");
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Imageidentity){
				Imageidentity speciesList = (Imageidentity) result;
				celltableUI.displaySpeciesByStudyId(speciesList);
			}			
		}
		
	}
	private class SpeciesReportByStudyIdCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			//throw an error message when something goes wrong in the server
			Window.alert("Error has occurred in the server processing advanced filters, try again later..");
			}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String){
				String exportResult = (String) result;
				celltableUI.csvExportByStudyId(exportResult);
			}
		}
		
	}
	private class SpeciesReportBySpeciesNameCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			//throw an error message when something goes wrong in the server
			Window.alert("Error has occurred in the server processing advanced filters, try again later..");
			}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String){
				String exportResult = (String) result;
				celltableUI.csvExportBySpeciesName(exportResult);
			}
		}
		
	}
	// Will be implemented only specific methods
	@Override
	public void login(String name, String password) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void checkLogin() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void changePassword(String name, String newPassword) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getUserName() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getSession() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getAllUsers() {
	
		
	}
	


}
