package jirtbioss.core.client.ui;

import java.util.List;

import jirtbioss.core.client.model.Imageidentity;
import jirtbioss.core.client.service.SpeciesReportServiceClientImpl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AdvancedReport extends Composite {
//Panels
private VerticalPanel mainReportPanel = new VerticalPanel();
private HorizontalPanel formPanel = new HorizontalPanel();
private VerticalPanel labelsPanel = new VerticalPanel();
private VerticalPanel textBoxesPanel = new VerticalPanel();
private HorizontalPanel buttonsPanel = new HorizontalPanel();

//Labels;
private HTML formTitle = new HTML("<p><h2>Advanced Report Filter: </h2><hr /></p>");
private Label noOfRecordsLbl = new Label("Number of Records: ");
private Label filterStudyIdLbl = new Label("Study ID: ");
private Label speciesNameLbl = new Label("SpeciesName: ");
private Label resultLbl = new Label();

//TextBoxes
private TextBox noOfRecordsTxtBx = new TextBox();
private TextBox filterStudyIdTxtBx = new TextBox();
private TextBox speciesNameTxtBx = new TextBox();

//Buttons
private Button searchBtn = new Button("Get Report");
private Button cancelBtn = new Button("Cancel");
private Button csvExportBtn = new Button("CSV Export");

//RPC variables
private SpeciesReportServiceClientImpl AdvancedRportServiceImpl;
//Constructor
public AdvancedReport(SpeciesReportServiceClientImpl speciesReportServiceImpl){
	//initalizing the main variables and widgets
	initWidget(this.mainReportPanel);
	this.AdvancedRportServiceImpl = speciesReportServiceImpl;
	this.mainReportPanel.add(formTitle);
	this.mainReportPanel.add(resultLbl);
	
	//Styling and formating necessary componets
	this.mainReportPanel.setStyleName("ScrollBox");
	textBoxesPanel.setStyleName("formTextBoxesPanel");//textBoxesPanel.setSpacing(10);
	labelsPanel.setSpacing(6);buttonsPanel.setSpacing(10);
	noOfRecordsTxtBx.setStyleName("input"); filterStudyIdTxtBx.setStyleName("input");speciesNameTxtBx.setStyleName("input");
	searchBtn.setStyleName("reportButton");cancelBtn.setStyleName("close");

	//Add to panels
	labelsPanel.add(filterStudyIdLbl);textBoxesPanel.add(filterStudyIdTxtBx);
	labelsPanel.add(speciesNameLbl);textBoxesPanel.add(speciesNameTxtBx);
	labelsPanel.add(noOfRecordsLbl);textBoxesPanel.add(noOfRecordsTxtBx);
	buttonsPanel.add(searchBtn);buttonsPanel.add(cancelBtn);
	textBoxesPanel.add(buttonsPanel);
	formPanel.add(labelsPanel);formPanel.add(textBoxesPanel);
	mainReportPanel.add(formPanel); //mainReportPanel.add(buttonsPanel);
	
	this.cancelBtn.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
		 mainReportPanel.clear();
			
		}
		
	});
	this.searchBtn.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
	
			if(filterStudyIdTxtBx.getText().equals("") && speciesNameTxtBx.getText().equals("") && noOfRecordsTxtBx.getText().equals("")){
				resultLbl.setStyleName("error");
				resultLbl.setText("At least one of the field must be filled..try again");
			}else  if (!noOfRecordsTxtBx.getText().matches("[0-9]*")) {
				resultLbl.setStyleName("error");
				resultLbl.setText("Number of records field must be a numeric value....");
	        }else{
	        	 mainReportPanel.clear();
	        	AdvancedRportServiceImpl.getSpeciesByAdvancedFilters(filterStudyIdTxtBx.getText(), speciesNameTxtBx.getText(), noOfRecordsTxtBx.getText());
	        }
		}
		
	});
}
	public void displayAdvancedReport(Imageidentity filteredSpeciesList){
		 final List<Imageidentity> listOfFilteredSpecies = filteredSpeciesList.getSpeciesRecordsAdvancedFilters();
		 mainReportPanel.clear();
		 this.mainReportPanel.add(csvExportBtn);  									//add csv button
		  // Create a CellTable.
				 SpeciesReportTable speciesTableByName = new SpeciesReportTable(listOfFilteredSpecies);
				 mainReportPanel.add(speciesTableByName);
			//  this.mainPanel.add(vp);
				//add clickHandler to the csv button to export species report to csv fil
		  this.csvExportBtn.addClickHandler(new ClickHandler(){
			 @Override
			public void onClick(ClickEvent event) {
				//call the RPC call to export CSV file
				 String filterType = "";
				 if(!"".equals(filterStudyIdTxtBx.getText()) && !"".equals(speciesNameTxtBx.getText()) && !"".equals(noOfRecordsTxtBx.getText())){
						String filterString ="SELECT * from imageidentity where species='"+speciesNameTxtBx.getText()+"' AND studyId='"+filterStudyIdTxtBx.getText()
								+"' LIMIT "+noOfRecordsTxtBx.getText();
						filterType="SpeciesName_StudyId";
						AdvancedRportServiceImpl.exportToCsv(filterString, filterType);
				 }else if(filterStudyIdTxtBx.getText().equals("") && !"".equals(speciesNameTxtBx.getText()) && !"".equals(noOfRecordsTxtBx.getText())){
					 String filterString ="SELECT * from imageidentity where species='"+speciesNameTxtBx.getText()+"' LIMIT "+noOfRecordsTxtBx.getText();
					 filterType="SpeciesName_Limit";	
					 AdvancedRportServiceImpl.exportToCsv(filterString, filterType);
				 }else if(speciesNameTxtBx.getText().equals("") && !"".equals(filterStudyIdTxtBx.getText()) && !"".equals(noOfRecordsTxtBx.getText())){
					 String filterString ="SELECT * from imageidentity where studyId='"+filterStudyIdTxtBx.getText()+"' LIMIT "+noOfRecordsTxtBx.getText();
					 filterType="StudyId_Limit";	
					 AdvancedRportServiceImpl.exportToCsv(filterString, filterType);
				 }else if(!"".equals(filterStudyIdTxtBx.getText()) && speciesNameTxtBx.getText().equals("") && "".equals(noOfRecordsTxtBx.getText())){
					 String filterString ="SELECT * from imageidentity where studyId='"+filterStudyIdTxtBx.getText()+"'";
					 filterType="StudyId_Advanced";	
					 AdvancedRportServiceImpl.exportToCsvByStudyId(filterStudyIdTxtBx.getText());
				 }else if(!"".equals(speciesNameTxtBx.getText()) && filterStudyIdTxtBx.getText().equals("") && "".equals(noOfRecordsTxtBx.getText())){
					  AdvancedRportServiceImpl.exportToCsvBySpeciesName(speciesNameTxtBx.getText());
				 }else if(!"".equals(speciesNameTxtBx.getText()) && !"".equals(filterStudyIdTxtBx.getText()) && "".equals(noOfRecordsTxtBx.getText())){
					 String filterString ="SELECT * from imageidentity where species='"+speciesNameTxtBx.getText()+"' AND studyId='"+filterStudyIdTxtBx.getText()+"'";
					 filterType="SpeciesName_StudyId";	
					 AdvancedRportServiceImpl.exportToCsv(filterString, filterType);
				 }
				 else{String filterString ="SELECT * from imageidentity LIMIT "+noOfRecordsTxtBx.getText();
				 filterType="All_Limit";	
				 AdvancedRportServiceImpl.exportToCsv(filterString,filterType);
					 
				 }
				 
			}
			  
		  });
		
	}
}
