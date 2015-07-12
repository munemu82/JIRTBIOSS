package jirtbioss.core.client.ui;

import java.util.ArrayList;
import java.util.List;

import jirtbioss.core.client.model.ImagesList;
import jirtbioss.core.client.model.Species;
import jirtbioss.core.client.model.Users;
import jirtbioss.core.client.service.AdminServiceClientImpl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SpeciesForms extends Composite{
	//main panel
			private VerticalPanel mainSpeciesPanel = new VerticalPanel();
			private HorizontalPanel formWidgetPanel = new HorizontalPanel();
			 private ScrollPanel listScrollPanel = new ScrollPanel();
			//user details components
			private Label saveResultLbl = new Label();
			private VerticalPanel saveCsvResultPanel = new VerticalPanel();
			private Label speciesIdLbl = new Label("Species ID: ");
			private Label speciesNameLbl = new Label("Species Name: ");
			private Label speciesDescLbl = new Label("Species Details: ");
			private Label speciesSimilarLbll = new Label("Similar Species 1: ");
			private Label speciesSimilarLbl2 = new Label("Similar Species 2: ");
			private Label speciesSimilarLbl3 = new Label("Similar Species 3: ");
			//Text boxes
			private TextBox speciesIdTxtBx = new TextBox(); 
			 private TextBox speciesNameTxtBx = new TextBox(); 
			 private TextArea speciesDescTxtBx = new TextArea(); 
			 private TextBox speciesSimilar1TxtBx = new TextBox(); 
			 private TextBox speciesSimilar2TxtBx = new TextBox();
			 private TextBox speciesSimilar3TxtBx = new TextBox();
			//buttons
			Button saveBtn = new Button("Save");
			Button editBtn = new Button("Edit");
			Button cancelEditBtn = new Button("Save");
			Button cancelBtn = new Button("Cancel");
			Button ImportCsvBtn = new Button();
			//Panels
			HorizontalPanel speciesButtonsPanel = new HorizontalPanel();
			VerticalPanel labelsPanel = new VerticalPanel();
			VerticalPanel textBoxesPanel = new VerticalPanel();
			HorizontalPanel formPanel = new HorizontalPanel(); 
		//constructor
			AdminServiceClientImpl speciesServiceImpl;
			public SpeciesForms(AdminServiceClientImpl theSpeciesServiceImpl){
				initWidget(this.mainSpeciesPanel);
				this.speciesServiceImpl =  theSpeciesServiceImpl;
			
				textBoxesPanel.setStyleName("formTextBoxesPanel");//textBoxesPanel.setSpacing(10);
				labelsPanel.setSpacing(6);speciesButtonsPanel.setSpacing(10);
				speciesNameTxtBx.setStyleName("input");speciesSimilar1TxtBx.setStyleName("input");speciesDescTxtBx.setStyleName("textAreaInput");speciesSimilar2TxtBx.setStyleName("input");
				speciesSimilar3TxtBx.setStyleName("input");speciesIdTxtBx.setStyleName("input");
				cancelBtn.setStyleName("close"); saveBtn.setStyleName("success");
				ImportCsvBtn.setStyleName("importCsv");
				
				//Add widgets to the panels
				mainSpeciesPanel.add(ImportCsvBtn);
				mainSpeciesPanel.add(saveResultLbl);
				labelsPanel.add(speciesIdLbl);
				textBoxesPanel.add(speciesIdTxtBx);
				labelsPanel.add(speciesNameLbl);
				textBoxesPanel.add(speciesNameTxtBx);
				labelsPanel.add(speciesSimilarLbll);
				textBoxesPanel.add(speciesSimilar1TxtBx);
				labelsPanel.add(speciesSimilarLbl2);
				textBoxesPanel.add(speciesSimilar2TxtBx);
				labelsPanel.add(speciesSimilarLbl3);
				textBoxesPanel.add(speciesSimilar3TxtBx);
				labelsPanel.add(speciesDescLbl);
				textBoxesPanel.add(speciesDescTxtBx);
				//add form wdiget to the formPanel
				formPanel.add(labelsPanel); formPanel.add(textBoxesPanel);
				//buttons for this user
				speciesButtonsPanel.add(saveBtn);
				speciesButtonsPanel.add(cancelBtn);
				
				//Click Handlers
				ImportCsvBtn.addClickHandler(new ImportCsvBtnClickHandler());
				saveBtn.addClickHandler(new SaveBtnClickHandler());
				cancelBtn.addClickHandler(new ClickHandler(){
					@Override
					public void onClick(ClickEvent event) {
						mainSpeciesPanel.clear();				
					}
					
				});
				//add widgets to the main panel
				mainSpeciesPanel.add(formPanel);
				mainSpeciesPanel.add(speciesButtonsPanel);
			}
			
			//RPC displays
			public void displayCsvImport(Species listOfSpecies){
				List<Species> currentListOfSpecies = listOfSpecies.getListOfSpecies();
				listScrollPanel.setStyleName("textStyle");
				for(Species aSpecies: currentListOfSpecies){
					//VerticalPanel speciesListPanel = new VerticalPanel();
					Label speciesNameLbl = new Label();
					speciesNameLbl.setText(aSpecies.getSpeciesName());
					saveCsvResultPanel.add(speciesNameLbl);
				}
				listScrollPanel.add(saveCsvResultPanel);
			}
			private class ImportCsvBtnClickHandler implements ClickHandler{

				@Override
				public void onClick(ClickEvent event) {
					//first clear the main panel
					mainSpeciesPanel.clear();
					saveResultLbl.setText("The below species were imported Successfully");
					Label line = new Label("-------------------------------------------------");
					//make RPC call
					String fileName ="";    //filename is empty for now
					speciesServiceImpl.importCsv(fileName);	
					mainSpeciesPanel.add(saveResultLbl);
					mainSpeciesPanel.add(line);
					mainSpeciesPanel.add(listScrollPanel);
				}
				
			}
			public void displaySaveSpeciesResult(String result){
				saveResultLbl.setStyleName("success");
				saveResultLbl.setText(result);
				
			}
			private class SaveBtnClickHandler implements ClickHandler{

				@Override
				public void onClick(ClickEvent event) {
					//mainSpeciesPanel.clear();
					if(speciesIdTxtBx.getText().equals("") || speciesNameTxtBx.getText().equals("")){
						saveResultLbl.setStyleName("error");
						saveResultLbl.setText("Species ID and Species Name fields must be populated....");
					}else{
					speciesServiceImpl.saveSpecies(speciesIdTxtBx.getText(), speciesNameTxtBx.getText(), speciesDescTxtBx.getText(), speciesSimilar1TxtBx.getText(), speciesSimilar2TxtBx.getText(), speciesSimilar3TxtBx.getText());
					//clear the fields
					speciesIdTxtBx.setText("");speciesNameTxtBx.setText("");speciesDescTxtBx.setText("");speciesSimilar1TxtBx.setText("");speciesSimilar2TxtBx.setText("");speciesSimilar3TxtBx.setText("");
					saveResultLbl.setStyleName("success");
				}
					//mainSpeciesPanel.add(saveResultLbl);
				}
				
			}
			//Methods to display edit form for configured species
			public void displayEditSpecies(List<Species> currentSpecies, String selectedSpeciesId){
				for(Species theSpecies: currentSpecies){
					//check if the user click is the correct
					if(theSpecies.getSpeciesId().equals(selectedSpeciesId)){
						HorizontalPanel userButtonsPane = new HorizontalPanel();
						VerticalPanel labelsPanel = new VerticalPanel();
						VerticalPanel textBoxesPanel = new VerticalPanel();
						HorizontalPanel formPanel = new HorizontalPanel(); 
						//styling
						textBoxesPanel.setStyleName("formTextBoxesPanel");labelsPanel.setSpacing(6);userButtonsPane.setSpacing(10);
						//populate the textBox
						speciesIdTxtBx.setText(theSpecies.getSpeciesId());
						speciesNameTxtBx.setText(theSpecies.getSpeciesName());
						speciesDescTxtBx.setText(theSpecies.getSpeciesDescription());
						speciesSimilar1TxtBx.setText(theSpecies.getSpeciesLooklike1());
						speciesSimilar2TxtBx.setText(theSpecies.getSpeciesLooklike2());
						speciesSimilar3TxtBx.setText(theSpecies.getSpeciesLooklike3());
						//Styling textBoxes
						speciesIdTxtBx.setStyleName("input");speciesNameTxtBx.setStyleName("input");speciesDescTxtBx.setStyleName("input");speciesSimilar1TxtBx.setStyleName("input");speciesSimilar2TxtBx.setStyleName("input");
						speciesSimilar3TxtBx.setStyleName("input");
						Label speciesIdLbl = new Label("Species ID: ");
						Label speciesNameLbl= new Label("Species Name ");
						Label speciesDescLbl = new Label("Species Description: ");
						Label speciesLookslike1Lbl = new Label("Similar species 1: ");
						Label speciesLookslike2Lbl = new Label("Similar species 2: ");
						Label speciesLookslike3Lbl = new Label("Similar species 3: ");
						
						labelsPanel.add(speciesIdLbl); textBoxesPanel.add(speciesIdTxtBx);
						labelsPanel.add(speciesNameLbl);textBoxesPanel.add(speciesNameTxtBx);
						labelsPanel.add(speciesLookslike1Lbl);textBoxesPanel.add(speciesSimilar1TxtBx);
						labelsPanel.add(speciesLookslike2Lbl);textBoxesPanel.add(speciesSimilar2TxtBx);
						labelsPanel.add(speciesLookslike3Lbl);textBoxesPanel.add(speciesSimilar3TxtBx);
						labelsPanel.add(speciesDescLbl);textBoxesPanel.add(speciesDescTxtBx);
						formPanel.add(labelsPanel);formPanel.add(textBoxesPanel);
						//buttons for this user
						
						mainSpeciesPanel.add(saveResultLbl);
						formPanel.add(saveBtn);
						formPanel.add(cancelBtn);
						mainSpeciesPanel.add(formPanel);
						}
				
			}
			}
			
}