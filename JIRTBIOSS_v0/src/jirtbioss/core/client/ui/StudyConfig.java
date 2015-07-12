package jirtbioss.core.client.ui;

import java.util.List;

import jirtbioss.core.client.model.Species;
import jirtbioss.core.client.model.Study;
import jirtbioss.core.client.service.AdminServiceClientImpl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StudyConfig extends Composite {
	private HTML behaviorslbl = new HTML("<p class='subtitles'>Behaviors </p> <hr />");
	private HTML poseLbl = new HTML("<p class='subtitles'> Poses </p> <hr />");
	private HTML colourLbl = new HTML("<p class='subtitles'>Colours </p> <hr />");
	private HTML scaleLbl = new HTML("<p class='subtitles'>Scales </p> <hr />");
	private VerticalPanel mainStudyPanel = new VerticalPanel();
	private HorizontalPanel mainForm = new HorizontalPanel();
	private VerticalPanel behaviorPanel = new VerticalPanel();
	private VerticalPanel behaviorLblPanel = new VerticalPanel();
	private VerticalPanel posePanel = new VerticalPanel();
	private HorizontalPanel behaviorFormPanel = new HorizontalPanel();
	private VerticalPanel poseLblPanel = new VerticalPanel();
	private HorizontalPanel poseFormPanel = new HorizontalPanel();
	private VerticalPanel colourPanel = new VerticalPanel();
	private VerticalPanel colourLblPanel = new VerticalPanel();
	private HorizontalPanel colourFormPanel = new HorizontalPanel();
	private VerticalPanel scalePanel = new VerticalPanel();
	private VerticalPanel scaleLblPanel = new VerticalPanel();
	private HorizontalPanel scaleFormPanel = new HorizontalPanel();
	private ScrollPanel srollPanel = new ScrollPanel();
	//private TextBox configStudyIdTxtBx = new TextBox();
	private Label configStudyIdLbl = new Label("Study ID: ");
	private Label configStudyIdLblResult = new Label();
	private Button displayConfigsBtn = new Button("Get configurations");
	private Button behaviorConfigBtn = new Button("Save changes");
	private Button poseConfigBtn = new Button("Save changes");
	private Button colourConfigBtn = new Button("Save changes");
	private Button scaleConfigBtn = new Button("Save changes");
	private Button addStudyConfigBtn = new Button("Add new Config");
	private Label behaviorConfigLabel;
	private VerticalPanel behaviorConfigPanel = new VerticalPanel();
	private VerticalPanel poseConfigPanel = new VerticalPanel();
	private VerticalPanel colourConfigPanel = new VerticalPanel();
	private VerticalPanel scaleConfigPanel = new VerticalPanel();
	private Label colourConfigLabel;
	private Label poseConfigLabel;
	private Label scaleConfigLabel;
	private Label behaviorChbxLbl;
	private Label poseChbxLbl;
	private Label colourChbxLbl;
	private Label scaleChbxLbl;
	MultiWordSuggestOracle speciesSuggestion = new MultiWordSuggestOracle(); 
	private SuggestBox configStudyIdTxtBx = new SuggestBox(speciesSuggestion);
	private VerticalPanel titlePanel = new VerticalPanel();  
	//New Study Config form widgets
	private HorizontalPanel formPanel = new HorizontalPanel();
	private VerticalPanel labelPanel = new VerticalPanel();
	private VerticalPanel widgetPanel = new VerticalPanel();
	private Label resultStatus = new Label();
	private Label configNameLbl = new Label("Configuration Name:");
	private Label configTypeLbl = new Label("Configuration Type:");
	private ListBox configTypeLstBx = new ListBox();
	private TextBox configNameTxtBx = new TextBox();
	private Button saveConfigBtn = new Button("Save Config");
	private Anchor redisplayConfigBtn = new Anchor("Back to Config");
	
	 private AdminServiceClientImpl adminServiceImpl;
	
	 public StudyConfig(AdminServiceClientImpl theAdminImpl){
		 initWidget(this.mainStudyPanel);
		 this.adminServiceImpl = theAdminImpl;
		 this.mainForm.add(configStudyIdLbl);
		 this.mainForm.add(configStudyIdTxtBx);
		 this.mainForm.add(displayConfigsBtn);
		 this.mainStudyPanel.add(mainForm);
		 this.adminServiceImpl.getListOfstudies();
		 //format and styling panels and widgets
		 behaviorPanel.setSize("250px", "");
		 behaviorFormPanel.setSize("300px", "");
		 labelPanel.setSpacing(5); 
		 
		 //Add event handlers
		this.displayConfigsBtn.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				//check if configStudyIdTxtBx to ensure text box is not empty
				if(configStudyIdTxtBx.getText().equals("")){
					configStudyIdLblResult.setStyleName("error");
					configStudyIdLblResult.setText("Study Id must be specified to get config for this study");
					mainStudyPanel.add(configStudyIdLblResult);
				}else{
					mainStudyPanel.clear();
					final HTML currentStudyId = new HTML("<div class='titles'> Study configurations for "+configStudyIdTxtBx.getText()+"</div><hr />");
					titlePanel.add(currentStudyId);
					mainStudyPanel.add(titlePanel);
					mainStudyPanel.add(addStudyConfigBtn);
				//make RPC call get all configurations
				adminServiceImpl.getStudyBehavConfigs(configStudyIdTxtBx.getText());
				adminServiceImpl.getStudyPoseConfigs(configStudyIdTxtBx.getText());
				adminServiceImpl.getStudyColourConfigs(configStudyIdTxtBx.getText());
				adminServiceImpl.getStudyScaleConfigs(configStudyIdTxtBx.getText());
				
				addStudyConfigBtn.addClickHandler(new ClickHandler(){

					@Override
					public void onClick(ClickEvent event) {
						titlePanel.clear();
						mainStudyPanel.clear();
						
						//redisplayConfigBtn.setVisible(true);
						HTML addStudyConfigTitle = new HTML("<div class='titles'> Adding Config for Study ID:  "+configStudyIdTxtBx.getText()+"</div><hr />");
						titlePanel.add(addStudyConfigTitle);
						mainStudyPanel.add(titlePanel);
						//Setup list of configuration types
						configTypeLstBx.addItem("Behavior"); configTypeLstBx.addItem("Colour"); configTypeLstBx.addItem("Poses"); configTypeLstBx.addItem("Scale");
						//adding components to the form
						mainStudyPanel.add(resultStatus);
						labelPanel.add(configNameLbl); 
						widgetPanel.add(configNameTxtBx);
						labelPanel.add(configTypeLbl);
						widgetPanel.add(configTypeLstBx);
						formPanel.add(labelPanel);
						formPanel.add(widgetPanel);
						mainStudyPanel.add(formPanel);
						labelPanel.add(saveConfigBtn);
						//mainStudyPanel.add(redisplayConfigBtn);
						/*redisplayConfigBtn.addClickHandler(new ClickHandler(){
							@Override
							public void onClick(ClickEvent event) {
								//make RPC call to redisplay the configurations
								formPanel.clear();
								titlePanel.clear();
								mainStudyPanel.add(addStudyConfigBtn);
								titlePanel.add(currentStudyId);
								redisplayConfigBtn.setVisible(false);
								adminServiceImpl.getStudyBehavConfigs(configStudyIdTxtBx.getText());
								adminServiceImpl.getStudyPoseConfigs(configStudyIdTxtBx.getText());
								adminServiceImpl.getStudyColourConfigs(configStudyIdTxtBx.getText());
								adminServiceImpl.getStudyScaleConfigs(configStudyIdTxtBx.getText());
							}
							});*/
						//Add save configuration event handler
						saveConfigBtn.addClickHandler(new ClickHandler(){

							@Override
							public void onClick(ClickEvent event) {
								//Validate fields
								if(configNameTxtBx.getText().equals("")){
									resultStatus.setStyleName("error");
									resultStatus.setText("Configuration name field must be populated...!");
								}else{
									adminServiceImpl.saveStudyConfig(configTypeLstBx.getSelectedItemText(), configStudyIdTxtBx.getText(), configNameTxtBx.getText());
									
								}
							}
							
						});
					}
					
				});
				}
			}
			
		}) ;		 
	 }
	 
	 public void displayBehavior(Study listOfStudyBehavior){
		 final List<Study> listOfStudyConfigs = listOfStudyBehavior.getListOfStudyConfig();
		 //Behavior label
		 mainStudyPanel.add(behaviorslbl);
		
		 for(final Study studyBehavior: listOfStudyConfigs){
			 final CheckBox behaviorChkbox = new CheckBox();
		 behaviorChkbox.setStyleName("configCheckBox");
			 if(studyBehavior.getBehaviorStatus().equals("A")){
				 behaviorChkbox.setValue(true);
			 }
			 behaviorChbxLbl = new Label(studyBehavior.getBehavior());
			 // lblPanel.add(behaviorChbxLbl);
			 behaviorLblPanel.add(behaviorChbxLbl);
			  behaviorPanel.add(behaviorChkbox);
			  behaviorFormPanel.add(behaviorLblPanel);
			  behaviorFormPanel.add(behaviorPanel);
			  behaviorConfigBtn.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					//Boolean checkedBehavior = ((CheckBox) event.getSource()).getValue();
					if(behaviorChkbox.getValue()==true){
						adminServiceImpl.saveStudyBehavior(studyBehavior.getBehaviorId(), "A");
					}else{
						adminServiceImpl.saveStudyBehavior(studyBehavior.getBehaviorId(), "N");
					}
				}
				  
			  });
		 	}
		 
		 mainStudyPanel.add(behaviorFormPanel);
		 mainStudyPanel.add(behaviorConfigBtn);
		
	 }
	 public void displayPoses(Study listOfStudyPoses){
		 final List<Study> listOfStudyConfigs = listOfStudyPoses.getListOfStudyConfig();
		 //Behavior label
		 mainStudyPanel.add(poseLbl);
		 for(final Study studyPose: listOfStudyConfigs){
			 final CheckBox poseChckBox = new CheckBox();
			 poseChckBox.setStyleName("configCheckBox");
			 if(studyPose.getPoseStatus().equals("A")){
				 poseChckBox.setValue(true);
			 }
			 behaviorChbxLbl = new Label(studyPose.getPose());
			 // lblPanel.add(behaviorChbxLbl);
			 poseLblPanel.add(behaviorChbxLbl);
			 posePanel.add(poseChckBox);
			 poseFormPanel.add(poseLblPanel);
			 poseFormPanel.add(posePanel);
			 poseConfigBtn.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					//RPC cal
					if(poseChckBox.getValue()==true){
						adminServiceImpl.saveStudyPose(studyPose.getPoseId(), "A");
					}else{
						adminServiceImpl.saveStudyPose(studyPose.getPoseId(), "N");
					}
				}
				 
			 });
	 }
		 mainStudyPanel.add(poseFormPanel); 
		 mainStudyPanel.add(poseConfigBtn);
	 }
	 public void displayColour(Study listOfStudyColours){
		 final List<Study> listOfStudyConfigs = listOfStudyColours.getListOfStudyConfig();
		 //Behavior label
		 mainStudyPanel.add(colourLbl);
		 for(final Study studyColour: listOfStudyConfigs){
			 final CheckBox colourChkbox = new CheckBox();
			 colourChkbox.setStyleName("configCheckBox");
			 if(studyColour.getColourStatus().equals("A")){
				 colourChkbox.setValue(true);
			 }
			 colourChbxLbl = new Label(studyColour.getColour());
			 // lblPanel.add(behaviorChbxLbl);
			 colourLblPanel.add(colourChbxLbl);
			  colourPanel.add(colourChkbox);
			  colourFormPanel.add(colourLblPanel);
			  colourFormPanel.add(colourPanel);
			  colourConfigBtn.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					//Boolean checkedBehavior = ((CheckBox) event.getSource()).getValue();
					if(colourChkbox.getValue()==true){
						adminServiceImpl.saveStudyColour(studyColour.getColourId(), "A");
					}else{
						adminServiceImpl.saveStudyColour(studyColour.getColourId(), "N");
					}
				}
				  
			  });
		 }
		 mainStudyPanel.add(colourFormPanel);
		 mainStudyPanel.add(colourConfigBtn);
		
	 }
	 public void displayScales(Study listOfStudyColours){
		 final List<Study> listOfStudyConfigs = listOfStudyColours.getListOfStudyConfig();
		 //Behavior label
		 mainStudyPanel.add(scaleLbl);
		 for(final Study studyScale: listOfStudyConfigs){
			 final CheckBox scaleChkbox = new CheckBox();
			 scaleChkbox.setStyleName("configCheckBox");
			 if(studyScale.getScaleStatus().equals("A")){
				 scaleChkbox.setValue(true);
			 }
			 scaleChbxLbl = new Label(studyScale.getScale());
			 // lblPanel.add(behaviorChbxLbl);
			 scaleLblPanel.add(scaleChbxLbl);
			  scalePanel.add(scaleChkbox);
			  scaleFormPanel.add(scaleLblPanel);
			  scaleFormPanel.add(scalePanel);
			  scaleConfigBtn.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					//Boolean checkedBehavior = ((CheckBox) event.getSource()).getValue();
					if(scaleChkbox.getValue()==true){
						adminServiceImpl.saveStudyScale(studyScale.getScaleId(), "A");
					}else{
						adminServiceImpl.saveStudyScale(studyScale.getScaleId(), "N");
					}
				}
				  
			  });
		 }
		 mainStudyPanel.add(scaleFormPanel);
		 mainStudyPanel.add(scaleConfigBtn);
		
	 }
	 //RPC calls results
	 public void displayStudySuggestion(Study listOfStudies){
		 List<Study> currentStudies = listOfStudies.getListOfStudies();
		 for(Study aStudy: currentStudies){
			 speciesSuggestion.add(aStudy.getStudyId());
		 }
	 }
	 public void displaySaveBehaviorResult(String result){
		 Label resultLabel = new Label(result);
	 }
	 public void displaySavePoseResult(String result){
		 Label resultLabel = new Label(result);
	 }
	 public void displaySaveColourResult(String result){
		 Label resultLabel = new Label(result);
	 }
	 public void displaySaveScaleResult(String result){
		 Label resultLabel = new Label(result);
	 }
	 public void displaySaveConfigStatus(String saveConfigStatus){
		 if(saveConfigStatus.equals("Failed")){
			 resultStatus.setStyleName("error");
			 resultStatus.setText("System failed saving configuration due to an error..!");
		 }else{
			 resultStatus.setStyleName("success");
			 resultStatus.setText(saveConfigStatus+" Configuration saved successfully!");
			 formPanel.clear();
		 }
	 }
}
