package jirtbioss.core.client;

import java.util.ArrayList;
import java.util.List;

import jirtbioss.core.client.model.Imageidentity;
import jirtbioss.core.client.service.SpeciesReportServiceClientImpl;
import jirtbioss.core.client.ui.SpeciesReportTable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SpeciesCellTable extends Composite{
  private VerticalPanel mainPanel = new VerticalPanel();
 private  VerticalPanel vp = new VerticalPanel();
 private HorizontalPanel filterAndButtonsPanel = new HorizontalPanel();
 private VerticalPanel studyIDFieldPanel = new VerticalPanel();
 private Button showSpeciesBtn;					//button to show all species
 private Button clearTable;						//button to clear the table
 private ListBox filterBySpecies;				//Filter listbox to filter report by species
 private Label filterBySpeciesLbel;				//Label for filter by species
   private Label filterByStudyID;					//Label for filter by study
 private Button filterByStudyIdBtn;
 private Button advanceReportBtn;
  private HTML hLineForFilterAndButtons;
  private HTML reportTitle = new HTML("<h2 align='center'>All Species Report</h2> <hr />");
  private SpeciesReportServiceClientImpl ServiceImpl;				//for the RPC
  private List<Imageidentity> allIdentityRecords;
  MultiWordSuggestOracle studyIdSuggestions = new MultiWordSuggestOracle();  
  SuggestBox studyIdSuggestionBox;
  
//Reports buttons - these are shown when a specific report filter is displayed
 private Button csvExportBtn;
  public SpeciesCellTable(SpeciesReportServiceClientImpl speciesReportServiceImpl){
	  //Set main composite/gui panel 
	  initWidget(this. mainPanel);
	  
	//instantiate and initialize variables and widgets
	  this.vp.setStyleName("ScrollBox");
	  this.ServiceImpl = speciesReportServiceImpl;
	  this.allIdentityRecords = new ArrayList<Imageidentity>();
	//the first report - that is show all species report
	  ServiceImpl.getSpeciesObjects();
	  
	  this.showSpeciesBtn = new Button("Show all Species");	
	  this.showSpeciesBtn.setEnabled(false); 	//disable this when load page
	  this.advanceReportBtn = new Button("Advanced");
	  this.advanceReportBtn.addClickHandler(new AdvanceReportBtnClickHandler());
	  this.showSpeciesBtn.setStyleName("Enabledgwt-Button");
	  this.showSpeciesBtn.setSize("180px", "35px");
	  this.showSpeciesBtn.addClickHandler(new ShowAllSpeciesBtnHandler());	//Line 118
	  this.clearTable = new Button("Clear table");
	  this.clearTable.setStyleName("Enabledgwt-Button");
	  this.advanceReportBtn.setStyleName("Enabledgwt-Button");
	  this.clearTable.addClickHandler(new ClearTableBtnHandler());		//Line 129
	  this.csvExportBtn = new Button("CSV Export");						//Button to export CSV report
	  this.csvExportBtn.setStyleName("speciesReportBtn");				//Set style
	  this.filterBySpecies = new ListBox();
	  this.filterBySpeciesLbel = new Label("Filter By Species");
	  this.hLineForFilterAndButtons = new HTML("<hr />"); //this is just a simple horizontal line for styling
	  this.filterByStudyID = new Label("Filter by StudyID");
	  
	  this.filterByStudyIdBtn = new Button("Go");
	  this.filterByStudyIdBtn.setStyleName("roundButton");
	  //populate filterBySpecies listBox
	  this.filterBySpecies.addStyleName("DropDowninput");
	  this.filterBySpecies.addItem("");
	  this.filterBySpecies.addItem("Kangaroo");
	  this.filterBySpecies.addItem("Koala");
	  this.filterBySpecies.addItem("Wallaby");
	  this.filterBySpecies.addItem("Possum");
	  this.filterBySpecies.addItem("Mouse");
	  this.filterBySpecies.addItem("Sheep");
	  this.filterBySpecies.addItem("Wild Mouse");
	  this.filterBySpecies.addItem("Goat");
	  this.filterBySpecies.addChangeHandler(new FilterBySpeciesChangeHandler()); //Line 167
	  
	  //Add widgets to the panel ( to filterAndButtonsPanel)
	  this.filterAndButtonsPanel.setSpacing(15);
	  this.filterAndButtonsPanel.add(showSpeciesBtn);
	  this.filterAndButtonsPanel.add(clearTable);
	  this.filterAndButtonsPanel.add(filterBySpeciesLbel);
	  this.filterAndButtonsPanel.add(filterBySpecies);
	  this.filterAndButtonsPanel.add(filterByStudyID);
	  this.filterAndButtonsPanel.add(studyIDFieldPanel);
	  this.filterAndButtonsPanel.add(advanceReportBtn);
	  
	  //add the widgets panel to the main panel (Report Filters and buttons)
	  this.mainPanel.add(filterAndButtonsPanel);
	  this.mainPanel.add(hLineForFilterAndButtons);
	  //Get species IDs suggestion
	  studyIdSuggestionBox = new SuggestBox(studyIdSuggestions);
	  this.filterByStudyIdBtn.addClickHandler(new filterByStudyIdBtnClickHandler());
	  }
 
  //Method for RPC calls
  public void displaySpeciesRecords(Imageidentity specieslist){
	  final String filterReport =""; 
	// create ArrayList of species records from the database (rows returned from RPC call)
	  final List<Imageidentity> SPECIES = specieslist.getSpeciesRecords();
	  allIdentityRecords = specieslist.getSpeciesRecords();
	  for(Imageidentity aIdentity: allIdentityRecords){
		  studyIdSuggestions.add(aIdentity.getStudyId());
	  }
	   studyIdSuggestionBox.setStyleName("input"); 
	   this.studyIDFieldPanel.add(studyIdSuggestionBox);
	   this.studyIDFieldPanel.add(filterByStudyIdBtn);
	   
	  this.vp.add(reportTitle);	
       this.vp.add(csvExportBtn); 
	    //creating table widget and populate with list of species from RPC result
		  SpeciesReportTable allSpeciesTable = new SpeciesReportTable(SPECIES); 	// Create a CellTable.
		  this.vp.add(allSpeciesTable);
	    this.mainPanel.add(vp);
	
	    this.csvExportBtn.addClickHandler(new ClickHandler(){
			 @Override
			public void onClick(ClickEvent event) {
				//call the RPC call to export CSV file
					ServiceImpl.exportToCsv(filterReport, "All");
				}
			
			  
		  });
			

	
	  }
public void displaySpeciesByStudyId(Imageidentity species){
	final List<Imageidentity> speciesByStudyIds = species.getSpeciesRecordsByStudyId();
	  //add buttons to the report
	
	//create a table for species by study ID
			 SpeciesReportTable speciesByStudyIdTable = new SpeciesReportTable(speciesByStudyIds); 	// Create a CellTable.
			 HTML title = new HTML("<h2 align='center'> Species Report By Study ID - " + studyIdSuggestionBox.getValue() +"</h2> <hr />");
				vp.add(title);
			 vp.add(csvExportBtn);  
			 vp.add(speciesByStudyIdTable);
			// showSpeciesBtn.setEnabled(true);
			  this.mainPanel.add(vp);
		  //add csv button
		  	//add clickHandler to the csv button to export species report to csv file
		  this.csvExportBtn.addClickHandler(new ClickHandler(){
			 @Override
			public void onClick(ClickEvent event) {
				//call the RPC call to export CSV file
				if(!"".equals(studyIdSuggestionBox.getValue())){
					//String filterString = "SELECT * FROM imageidentity where studyId="+"'"+studyIdSuggestionBox.getValue()+"'";
					ServiceImpl.exportToCsvByStudyId(studyIdSuggestionBox.getValue());
				}
			}
			  
		  });
		 
}
  	public void csvExportSuccess(String flagExport){
  		Window.alert(flagExport);
  	}
 	public void csvExportByStudyId(String flagExport){
  		Window.alert(flagExport);
  	}
 	public void csvExportBySpeciesName(String flagExport){
  		Window.alert(flagExport);
  	}
  	private class filterByStudyIdBtnClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			vp.clear();
			filterBySpecies.setSelectedIndex(0);
			//RCP call
			ServiceImpl.getSpeciesByStudyId(studyIdSuggestionBox.getValue());
		}
  		
  	}
  	
  	//EVENT HANDLERS
  	//Show all species button event handler
  	private class ShowAllSpeciesBtnHandler implements ClickHandler{
  		@Override
		public void onClick(ClickEvent event) {
  			//clear and reset other widgets
  			vp.clear();
  			studyIdSuggestionBox.setText("");
  			filterBySpecies.setSelectedIndex(0);
			//Make RPC call and load the species and populate the celltable
			ServiceImpl.getSpeciesObjects();
			//disable the show species button
			csvExportBtn.setVisible(true);
			showSpeciesBtn.setEnabled(false);
			}
  	}
  	//clear the species report panel
  	private class ClearTableBtnHandler implements ClickHandler{
  		@Override
		public void onClick(ClickEvent event) {
			//clear the table vp panel and reset other widgets
			vp.clear();
			studyIdSuggestionBox.setText("");
			filterBySpecies.setSelectedIndex(0);
			//csvExportBtn.setEnabled(false);
			//Enable the show species button
			showSpeciesBtn.setEnabled(true);
			}
  	}
  
  	public void displayReportBySpeciesName(Imageidentity speciesByNameList){
  		 final List<Imageidentity> SPECIESBYNAME = speciesByNameList.getSpeciesRecordsFilteredName();
  		
  		//add csv button
		  this.vp.add(csvExportBtn); 
 	// Create a CellTable.
 		 SpeciesReportTable speciesTableByName = new SpeciesReportTable(SPECIESBYNAME);
		  vp.add(speciesTableByName);
		  this.mainPanel.add(vp);							
		  	//add clickHandler to the csv button to export species report to csv file
		  this.csvExportBtn.addClickHandler(new ClickHandler(){
			 @Override
			public void onClick(ClickEvent event) {
				 int speciesIndex = filterBySpecies.getSelectedIndex();
					String selectedSpecies = filterBySpecies.getItemText(speciesIndex);
					System.out.println("Selected Species:"+selectedSpecies);
				ServiceImpl.exportToCsvBySpeciesName(selectedSpecies);
				
			}
			  
		  });
		
  	}
  	//report by studyID filter
  	//1) RPC call to list all studyIDs
  	//2) RPC call to filter species records by selected studyID
  	private class FilterBySpeciesChangeHandler implements ChangeHandler{
  		@Override
  		public void onChange(ChangeEvent event) {
			//first thing first, clear the panel and enable buttons
  			vp.clear();
  			studyIdSuggestionBox.setText("");
  			showSpeciesBtn.setEnabled(true);
			int speciesIndex = filterBySpecies.getSelectedIndex();
			String selectedSpecies = filterBySpecies.getItemText(speciesIndex);
			HTML title = new HTML("<h2 align='center'> Species Report - " + selectedSpecies +"</h2> <hr />");
			vp.add(title);
			//make RPC call
			if(!"".equals(selectedSpecies)){
			ServiceImpl.getSpeciesByName(selectedSpecies);
			}
		}
  	}
  	private class AdvanceReportBtnClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
		//clear the panel first and reset other widgets
			vp.clear();
			studyIdSuggestionBox.setText("");
			filterBySpecies.setSelectedIndex(0);
		//create advance filter form
			SpeciesReportServiceClientImpl advancedRortFilters = new SpeciesReportServiceClientImpl(GWT.getModuleBaseURL() + "speciesreportservice");
			vp.add(advancedRortFilters.getSpeciesSearchUI());
			mainPanel.add(vp);
		}
  		
  	}
  	
}  