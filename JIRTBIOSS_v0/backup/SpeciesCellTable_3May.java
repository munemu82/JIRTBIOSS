package jirtbioss.core.client;

import java.util.ArrayList;
import java.util.List;

import jirtbioss.core.client.model.Imageidentity;
import jirtbioss.core.client.model.Study;
import jirtbioss.core.client.service.SpeciesReportServiceClientImpl;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public class SpeciesCellTable extends Composite{
  private VerticalPanel mainPanel = new VerticalPanel();
 private  VerticalPanel vp = new VerticalPanel();
 private HorizontalPanel filterAndButtonsPanel = new HorizontalPanel();
 private Button showSpeciesBtn;					//button to show all species
 private Button clearTable;						//button to clear the table
 private ListBox filterBySpecies;				//Filter listbox to filter report by species
 private Label filterBySpeciesLbel;				//Label for filter by species
  private MultiWordSuggestOracle suggestedStudyId = new MultiWordSuggestOracle();  //create the suggestion data 
 private Label filterByStudyID;					//Label for filter by study
 private Button filterByStudyIdBtn;
  private HTML hLineForFilterAndButtons;
  private SpeciesReportServiceClientImpl ServiceImpl;				//for the RPC
  
//Reports buttons - these are shown when a specific report filter is displayed
 private Button csvExportBtn;
  public SpeciesCellTable(SpeciesReportServiceClientImpl speciesReportServiceImpl){
	  //Set main composite/gui panel 
	  initWidget(this. mainPanel);
	//instantiate and initialize variables and widgets
	  this.ServiceImpl = speciesReportServiceImpl;
	  this.showSpeciesBtn = new Button("Show all Species");			
	  this.showSpeciesBtn.setStyleName("Enabledgwt-Button");
	  this.showSpeciesBtn.setSize("180px", "35px");
	  this.showSpeciesBtn.addClickHandler(new ShowAllSpeciesBtnHandler());
	  this.clearTable = new Button("Clear table");
	  this.clearTable.setStyleName("Enabledgwt-Button");
	  this.clearTable.addClickHandler(new ClearTableBtnHandler());
	  this.csvExportBtn = new Button("CSV Export");						//Button to export CSV report
	  this.csvExportBtn.setStyleName("speciesReportBtn");				//Set style
	  this.filterBySpecies = new ListBox();
	  this.filterBySpeciesLbel = new Label("Filter By Species");
	  this.hLineForFilterAndButtons = new HTML("<hr />"); //this is just a simple horizontal line for styling
	  this.filterByStudyID = new Label("Filter by StudyID");
	  final SuggestBox studyIdSuggestionBox = new SuggestBox(suggestedStudyId);
	  studyIdSuggestionBox.setStyleName("input"); 
	  this.filterByStudyIdBtn = new Button("Go");
	  this.filterByStudyIdBtn.setStyleName("roundButton");
	  //populate filterBySpecies listBox
	  this.filterBySpecies.addStyleName("DropDowninput");
	  this.filterBySpecies.addItem("Kangaroo");
	  this.filterBySpecies.addItem("Koala");
	  this.filterBySpecies.addItem("Wallaby");
	  this.filterBySpecies.addItem("Possum");
	  this.filterBySpecies.addItem("Mouse");
	  this.filterBySpecies.addItem("Sheep");
	  this.filterBySpecies.addItem("Goat");
	  
	  //Add widgets to the panel ( to filterAndButtonsPanel)
	  this.filterAndButtonsPanel.setSpacing(15);
	  this.filterAndButtonsPanel.add(showSpeciesBtn);
	  this.filterAndButtonsPanel.add(clearTable);
	  this.filterAndButtonsPanel.add(filterBySpeciesLbel);
	  this.filterAndButtonsPanel.add(filterBySpecies);
	  this.filterAndButtonsPanel.add(filterByStudyID);
	  this.filterAndButtonsPanel.add(studyIdSuggestionBox);
	  this.filterAndButtonsPanel.add(filterByStudyIdBtn);
	  
	  //add the widgets panel to the main panel (Report Filters and buttons)
	  this.mainPanel.add(filterAndButtonsPanel);
	  this.mainPanel.add(hLineForFilterAndButtons);
	 }
 
  //Method for RPC calls
  public void displaySpeciesRecords(Imageidentity specieslist){
	// create ArrayList of species records from the database (rows returned from RPC call)
	  final List<Imageidentity> SPECIES = specieslist.getSpeciesRecords();
	// Create a CellTable.
	    final CellTable<Imageidentity> table = new CellTable<Imageidentity>();
	    //set size of the table
	    table.setSize("1200", "400px");
	    // Display 10 rows in one page
	    table.setPageSize(10);
	 
	    Column<Imageidentity, Number> idColumn = new Column<Imageidentity, Number>(new NumberCell()) {
	        @Override
	        public Number getValue(Imageidentity object) {
	            return (Number) object.getRecordId();
	        }
	    };
	    table.addColumn(idColumn, "Record ID");

	    // Add a text column to show the Image ID.
	    TextColumn<Imageidentity> imageIdColumn = new TextColumn<Imageidentity>() {
	        @Override
	        public String getValue(Imageidentity object) {
	          return object.getImageid();
	        }
	      };
	      table.addColumn(imageIdColumn, "Image ID");

	    // Add a text column to show the behavior.
	    TextColumn<Imageidentity> behaviorColumn = new TextColumn<Imageidentity>() {
	      @Override
	      public String getValue(Imageidentity object) {
	        return object.getBehavior();
	      }
	    };
	    table.addColumn(behaviorColumn, "Behavior");
	    
	    // Add a text column to show the number of species in the species.
	    Column<Imageidentity, Number> numberColumn = new Column<Imageidentity, Number>(new NumberCell()) {
	        @Override
	        public Number getValue(Imageidentity object) {
	            return (Number) object.getNumber();
	        }
	    };
	    table.addColumn(numberColumn, "No. of Species");
	    
	    // Add a text column to show the pose.
	    TextColumn<Imageidentity> poseColumn = new TextColumn<Imageidentity>() {
	      @Override
	      public String getValue(Imageidentity object) {
	        return object.getPose();
	      }
	    };
	    table.addColumn(poseColumn, "Pose");
	    
	    // Add a text column to show children present.
	    TextColumn<Imageidentity> childrenColumn = new TextColumn<Imageidentity>() {
	      @Override
	      public String getValue(Imageidentity object) {
	        return object.getChildren();
	      }
	    };
	    table.addColumn(childrenColumn, "Children Present(y/n)");
	    
	 // Add a text column to show Scale.
	    TextColumn<Imageidentity> scaleColumn = new TextColumn<Imageidentity>() {
	      @Override
	      public String getValue(Imageidentity object) {
	        return object.getScale();
	      }
	    };
	    table.addColumn(scaleColumn, "Scale");
	 // Add a text column to show Species Name.
	    TextColumn<Imageidentity> speciesColumn = new TextColumn<Imageidentity>() {
	      @Override
	      public String getValue(Imageidentity object) {
	        return object.getSpecies();
	      }
	    };
	    table.addColumn(speciesColumn, "Species Name");
	    // Associate an async data pr
	    AsyncDataProvider<Imageidentity> provider = new AsyncDataProvider<Imageidentity>() {
	      @Override
	      protected void onRangeChanged(HasData<Imageidentity> display) {
	        int start = display.getVisibleRange().getStart();
	        int end = start + display.getVisibleRange().getLength();
	        end = end >= SPECIES.size() ? SPECIES.size() : end;
	        List<Imageidentity> sub = SPECIES.subList(start, end);
	        updateRowData(start, sub);
	      }
	    };
	    provider.addDataDisplay(table);
	    provider.updateRowCount(SPECIES.size(), true);

	    SimplePager pager = new SimplePager();
	    pager.setDisplay(table);
	    
	    //add buttons to the report
		  this.vp.add(csvExportBtn);  									//add csv button
		  	//add clickHandler to the csv button to export species report to csv file
		  this.csvExportBtn.addClickHandler(new ClickHandler(){
			 @Override
			public void onClick(ClickEvent event) {
				//call the RPC call to export CSV file
				ServiceImpl.exportToCsv();
			}
			  
		  });
	    vp.add(table);
	    vp.add(pager);
	    mainPanel.add(vp);
	  }
  	public void csvExportSuccess(String flagExport){
  		Window.alert(flagExport);
  	}
  	//EVENT HANDLERS
  	//Show all species button event handler
  	private class ShowAllSpeciesBtnHandler implements ClickHandler{
  		@Override
		public void onClick(ClickEvent event) {
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
			//clear the table vp panel
			vp.clear();
			//Enable the show species button
			showSpeciesBtn.setEnabled(true);
			
		}
  		
  	}
  	public void displaySuggestion(Study listOfStudyIds){
  		ArrayList<String> studyIds = listOfStudyIds.getStudyIds();
  		 for(int i=0; i < studyIds.size(); i++){
  			 suggestedStudyId.add(studyIds.get(i));
  		 }
  	}
  	//report by studyID filter
  	//1) RPC call to list all studyIDs
  	//2) RPC call to filter species records by selected studyID
  	
  		
}