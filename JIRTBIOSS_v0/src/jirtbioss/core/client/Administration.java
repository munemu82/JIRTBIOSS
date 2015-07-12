package jirtbioss.core.client;

import java.util.ArrayList;
import java.util.List;

import jirtbioss.core.client.authentication.InputValidator;
import jirtbioss.core.client.model.ImagesList;
import jirtbioss.core.client.model.Species;
import jirtbioss.core.client.model.SpeciesConfiguration;
import jirtbioss.core.client.model.Study;
import jirtbioss.core.client.model.Users;
import jirtbioss.core.client.service.AdminServiceClientImpl;
import jirtbioss.core.client.ui.AddLooksLike;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

public class Administration extends Composite{
	 private DockPanel dockPanel = new DockPanel();								//for layout panel
	 private VerticalPanel mainPanel = new VerticalPanel();						//main administration buttons
	 private HorizontalPanel adminButtonsPanel = new HorizontalPanel();				//this is the panel that holds admin buttons
	 private VerticalPanel selectedButtonsPanel = new VerticalPanel();
	 private Label saveResultLbl = new Label();
	 private ScrollPanel listScrollPanel = new ScrollPanel();
	 //--------------------------------USERS----------------------------------------------
	 private Button listUsersBtn = new Button("Users");
	 private Button addUserBtn = new Button("Add User");
	 private Button editUserBtn = new Button("Save");
	 private Label activateUserBtn = new Label("Activate User");
	 private Label userAcessLvlLbl= new Label("User Access Level:");
		 private TextBox userID = new TextBox(); 
		private TextBox username = new TextBox();
		private PasswordTextBox password = new PasswordTextBox(); 
		private TextBox firstname = new TextBox(); 
		private TextBox lastname = new TextBox(); 
		private TextBox email = new TextBox(); 
		private ListBox userAccessLevel = new ListBox();
	private ScrollPanel centerPanelScroll = new ScrollPanel();
	 //-----------------------------Studies--------------------------------------------------
	 private Button listStudiesBtn = new Button("Studies");
	 private Button addStudyBtn = new Button("Add Study");
	 private Button editStudyBtn= new Button("Save Study");
	 private Button deleteStudyBtn = new Button("Delete Study");
	 private HorizontalPanel studyButtons = new HorizontalPanel();
	 private TextBox studyIdTxtBx = new TextBox(); 
	 private TextBox studyTitleTxtBx = new TextBox(); 
	 private TextArea studyDescTxtBx = new TextArea(); 
	 private DateBox studyStartDateTxtBx = new DateBox(); 
	 private DateBox studyEndDateTxtBx = new DateBox(); 
	 private TextBox studySpeciesTxtBx = new TextBox(); 
	 private Button studyConfigListBtn = new Button("Display Configs");
	 //-------------------------------Configuration -------------------------------------------
	 private Button listConfigBtn = new Button("Configurations");
	 private Button addSpecies = new Button("Add species");
	 private Button addImagePathsBtn = new Button("Load Images");
	 private Button EditSpecies = new Button("Edit Species");
	 private Button addLooksLikeBtn = new Button("Add lookslike");
	 private Button addSpeciesFilters = new Button ("Add Species Filter");
	 private Button listSpeciesBtn = new Button("Display Species List");
	 private HorizontalPanel configButtons = new HorizontalPanel();
	 private AdminServiceClientImpl adminServiceImpl;
	 private Button saveSpeciesChangesBtn = new Button("Save Changes");
	 private Button cancelBtn = new Button("Cancel");
	 private TextBox speciesIdTxtBx = new TextBox(); 
	 private TextBox speciesNameTxtBx = new TextBox(); 
	 private TextArea speciesDescTxtBx = new TextArea(); 
		private TextBox speciesSimilar1TxtBx = new TextBox(); 
		 private TextBox speciesSimilar2TxtBx = new TextBox();
		 private TextBox speciesSimilar3TxtBx = new TextBox();
	 
	 //Dynamic values and commponents
	 VerticalPanel userPanel = new VerticalPanel();
	 VerticalPanel studyPanel = new VerticalPanel();
	 VerticalPanel configPanel = new VerticalPanel();
	 VerticalPanel speciesPanel = new VerticalPanel();
	 HorizontalPanel buttonsPanel = new HorizontalPanel();
	 Label WestPanelTitile = new Label();
	 
	public Administration(AdminServiceClientImpl theAdminImpl){
		//initializing the widgets
		initWidget(this.mainPanel);
		this.mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		//Here need to perform security check to check whether user is authorized admin user based on their session
		
		//initialize the RPC client implementation
		this.adminServiceImpl = theAdminImpl;
		//Commponents and Buttons styles
		dockPanel.setStyleName("dockpanel");
	     dockPanel.setSpacing(5);
	     listUsersBtn.setStyleName("UsersIcon-Button");
	     listStudiesBtn.setStyleName("StudiesIcon-Button"); 
	     listConfigBtn.setStyleName("ConfigurationIcon-Button");
	     WestPanelTitile.setStyleName("titles");
	     userPanel.setStyleName("userlist");
	     listScrollPanel.setStyleName("userlistScroll");
	     centerPanelScroll.setStyleName("userlistScroll");
	     selectedButtonsPanel.setStyleName("toolbar");
	     cancelBtn.setStyleName("close");
	     saveSpeciesChangesBtn.setStyleName("success");
	     editStudyBtn.setStyleName("success");
	     addUserBtn.setStyleName("add");addStudyBtn.setStyleName("add");addSpecies.setStyleName("add");addLooksLikeBtn.setStyleName("add");
	     listSpeciesBtn.setStyleName("display");studyConfigListBtn.setStyleName("display");
	     addImagePathsBtn.setStyleName("upload");
	     
	     // This is the first North component
	     dockPanel.add(new HTML("<h1> JIRTBIOSS ADMINISTRATION </h1> <hr />"), 
	     DockPanel.NORTH);
	     //This is the second north component
	     this.adminButtonsPanel.add(listUsersBtn);
	     this.adminButtonsPanel.add(listStudiesBtn);
	     this.adminButtonsPanel.add(listConfigBtn);
	     HTML linespace = new HTML("<hr />");
	     VerticalPanel topPanel = new VerticalPanel();
	     topPanel.setSize("800px", "");
	     topPanel.add(adminButtonsPanel);
	     topPanel.add(linespace);
	     dockPanel.add(topPanel, DockPanel.NORTH);
	     //Adding East components
	  
	     dockPanel.add(WestPanelTitile, DockPanel.WEST);
	     //add the scroll list of content to the left
	     dockPanel.add(listScrollPanel, DockPanel.WEST);
	  // Add scrollable text in the center
	     centerPanelScroll.add(selectedButtonsPanel);
	     dockPanel.add(centerPanelScroll, DockPanel.CENTER);
	    
	      mainPanel.add(dockPanel);
	     
	    //populate user access level listbox
			userAccessLevel.addItem("0");
			userAccessLevel.addItem("1");
			userAccessLevel.addItem("2");
			
	      this.adminServiceImpl.getListOfusers();     //this is loaded a list when this page opens
					
	      //Add click handelers to Buttons
	      this.listUsersBtn.addClickHandler(new ListUsersBtnClickHandler());
	      this.addUserBtn.addClickHandler(new AddUserBtnClickHandler());
	      this.editUserBtn.addClickHandler(new EditUserBtnClickHandler());
	      this.listStudiesBtn.addClickHandler(new ListStudiesBtnClickHandler());
	      this.addStudyBtn.addClickHandler(new AddStudyBtnClickHandler());
	      this.editStudyBtn.addClickHandler(new EditStudyBtnClickHandler());
	      this.listConfigBtn.addClickHandler(new ListConfigBtnClickhandler());
	      this.addSpecies.addClickHandler(new AddSpeciesBtnClickHandler());
	      this.listSpeciesBtn.addClickHandler(new ListSpeciesBtnClickHandler());
	      this.cancelBtn.addClickHandler(new CancelBtnClickHandler());
	      this.saveSpeciesChangesBtn.addClickHandler(new SaveSpesChangesBtnClickHandler());
	      this.addLooksLikeBtn.addClickHandler(new AddLooksLikeBtnClickHandler());
	      this.studyConfigListBtn.addClickHandler(new StudyConfigListBtnClickHandler());
	      this.addImagePathsBtn.addClickHandler(new AddImagePathsBtnClickHndler());
	      
	}
	//Method called when listUsersBtn Button is clicked
	public void displayUsers(Users usersList){
		WestPanelTitile.setText("Manage Users");
		userPanel.add(WestPanelTitile);
		userPanel.add(addUserBtn);
		HTML listOfActiveUsers = new HTML("<div class='subtitles'>List of active users </div> <hr />");
		final List<Users> listOfUsers = usersList.getUsersList();
		 MultiWordSuggestOracle userSuggestion = new MultiWordSuggestOracle(); 
		  HorizontalPanel userPanel2 = new HorizontalPanel();userPanel2.setSpacing(10);
		  activateUserBtn.setStyleName("Enabledgwt-Button-smallActive");
		 //populated user suggestions
		 for(final Users suggestedUser: listOfUsers){
			 //check if user is diactivated
			 if(suggestedUser.getStatus().equals("N")){
			 userSuggestion.add(suggestedUser.getUsername());
			 }
		 }
		 final SuggestBox suggestionBox = new SuggestBox(userSuggestion);
		//Click event handler for activate user button
		 activateUserBtn.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				if(suggestionBox.getText().equals("")){
					Window.alert("Field cannot be blank.....!");
				}else{
				adminServiceImpl.activateUser(suggestionBox.getText());
				suggestionBox.setText("");
				}
			}
			 
		 });
		 suggestionBox.setStyleName("input");
		 //Add user activation component
		 userPanel2.add(suggestionBox);
		 userPanel2.add(activateUserBtn);
		 userPanel.add(userPanel2);
		 userPanel.add(listOfActiveUsers);
		//display result
		for(final Users aUser: listOfUsers){
			HorizontalPanel userDetailPane = new HorizontalPanel();
			String userId = Integer.toString(aUser.getUserId());
			Label userIdLabel = new Label(userId);
			Label usernameLabel = new Label(aUser.getUsername());
			HTML separator = new HTML("&nbsp - &nbsp");
			Button deactivateUserBtn = new Button(); deactivateUserBtn.setStyleName("deactivateUser");
			String sessionID = Cookies.getCookie("username");
			if(aUser.getUsername().equals(sessionID))
			{
				if(aUser.getAuthLevel() < 2){
					dockPanel.clear();
					Label authResultLbl = new Label("You are not authorized to view.....");
					authResultLbl.setStyleName("error");
					 VerticalPanel authPanel = new VerticalPanel();
					 authPanel.add(authResultLbl);
				     dockPanel.add(authPanel, DockPanel.NORTH);
				}
			}
			if(aUser.getStatus().equals("A")){
			 deactivateUserBtn.addClickHandler(new ClickHandler(){

					@Override
					public void onClick(ClickEvent event) {
						boolean checkOk = Window.confirm("Are you sure you want to deactivate this user...?");
						if(checkOk==true){
						adminServiceImpl.deactivateUser(aUser.getUserId());
						}
					}
					
				});
			 //add a title
			
			userDetailPane.add(userIdLabel);
			userDetailPane.add(separator);
			userDetailPane.add(usernameLabel);
			userDetailPane.add(deactivateUserBtn);
			HTML lineseparator = new HTML("<hr />");
			userPanel.add(userDetailPane);
			userPanel.add(lineseparator);
			
			//Add click handler to each Username label so that we can clikc and get user complete details
			usernameLabel.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					//first of all click the update result label
					String userid = ((Label) event.getSource()).getText();
					for(Users thisUser: listOfUsers){
						//check if the user click is the correct
						if(thisUser.getUsername().equals(userid)){
							 HTML currentUserId = new HTML("<div class='titles'>"+thisUser.getUsername()+" User Update</div><hr />");
							
							
							HorizontalPanel userButtonsPane = new HorizontalPanel();
							VerticalPanel labelsPanel = new VerticalPanel();
							VerticalPanel textBoxesPanel = new VerticalPanel();
							HorizontalPanel formPanel = new HorizontalPanel(); 
							//styling
							textBoxesPanel.setStyleName("formTextBoxesPanel");labelsPanel.setSpacing(6);userButtonsPane.setSpacing(10);
							editUserBtn.setStyleName("success");
							//populate the textBox
							userID.setText(Integer.toString(thisUser.getUserId()));
							username.setText(thisUser.getUsername());
							password.setText(thisUser.getPassword());
							firstname.setText(thisUser.getFirstname());
							lastname.setText(thisUser.getLastname());
							email.setText(thisUser.getEmail());
							userAccessLevel.setSelectedIndex(thisUser.getAuthLevel());
				
							userID.setText(Integer.toString(thisUser.getUserId()));
							//Styling textBoxes
							userID.setStyleName("input");username.setStyleName("input");firstname.setStyleName("input");lastname.setStyleName("input");email.setStyleName("input");
							password.setStyleName("input");userAccessLevel.setStyleName("input");
							Label userIdLbl = new Label("UserID: ");
							Label usernamelbl= new Label("Username ");
							Label passwordLbl = new Label("Password: ");
							Label firstnameLbl = new Label("First Name: ");
							Label lastnameLabl = new Label("Last Name: ");
							Label emailLbl = new Label("Email Address: ");
							labelsPanel.add(userIdLbl); textBoxesPanel.add(userID);
							labelsPanel.add(usernamelbl);textBoxesPanel.add(username);
							labelsPanel.add(passwordLbl);textBoxesPanel.add(password);
							labelsPanel.add(firstnameLbl);textBoxesPanel.add(firstname);
							labelsPanel.add(lastnameLabl);textBoxesPanel.add(lastname);
							labelsPanel.add(emailLbl);textBoxesPanel.add(email);
							labelsPanel.add(userAcessLvlLbl);textBoxesPanel.add(userAccessLevel);
							formPanel.add(labelsPanel);formPanel.add(textBoxesPanel);
							//buttons for this user
							userButtonsPane.add(editUserBtn);
							selectedButtonsPanel.clear();
							selectedButtonsPanel.add(currentUserId);
							selectedButtonsPanel.add(saveResultLbl);
							selectedButtonsPanel.add(formPanel);
							selectedButtonsPanel.add(userButtonsPane);
							
							}
					}
					
				}
				
			});
		}
		}
		//first of all clear the content of the scroll before adding 	
		listScrollPanel.clear();
		//add user list to the scroll panel
		listScrollPanel.add(userPanel);
	}
	public void displayUpdateUserResult(String resultStatus){
		saveResultLbl.setStyleName("success");
		saveResultLbl.setText(resultStatus);
	}//END OF USERS DISPLAY
	
	//START OF STUDY DISPLAY
	public void displayStudyDetails(Study studyList){
		WestPanelTitile.setText("Manage Studies");
		//add the title and the button to add a study
		studyPanel.add(WestPanelTitile);
		studyButtons.add(addStudyBtn);
		studyButtons.add(studyConfigListBtn);
		studyPanel.add(studyButtons);
		//get a list of all studies
		HTML listOfStudies = new HTML("<div class='subtitles'>List of Studies </div> <hr />");
		final List<Study> currentStudiesList = studyList.getListOfStudies();
		//Display studies
		studyPanel.add(listOfStudies);
		for(final Study theStudy: currentStudiesList){
			if(!"Completed".equals(theStudy.getStatus())){							//We only list studies that are not yet completed
			HorizontalPanel studyDetailsPanel = new HorizontalPanel();
			studyDetailsPanel.setSpacing(5);
			final Label studyIdLbl = new Label(theStudy.getStudyId());
			Label studyTitleLbl = new Label(theStudy.getStudyTitle());
			HTML separator = new HTML("&nbsp - &nbsp");
			HTML lineseparator = new HTML("<hr />");
			Button activateStudyBtn = new Button("Activate"); activateStudyBtn.setStyleName("Enabledgwt-Button-smallActive");
			Button deActivateStudyBtn = new Button("Deactivate"); deActivateStudyBtn.setStyleName("Enabledgwt-Button-smallDeactive");
			//add the study details to the panel
			studyDetailsPanel.add(studyIdLbl);studyDetailsPanel.add(separator); studyDetailsPanel.add(studyTitleLbl);
					
			//check if status
			if(theStudy.getStatus().equals("Active")){
				studyDetailsPanel.add(deActivateStudyBtn);
			}else{
				studyDetailsPanel.add(activateStudyBtn);
			}
			studyPanel.add(studyDetailsPanel); 
			studyPanel.add(lineseparator);
			//Event handler for the activate and deactivate buttons
			activateStudyBtn.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					adminServiceImpl.activateStudy(theStudy.getStudyId());
				}
				
			});
			deActivateStudyBtn.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					adminServiceImpl.deActivateStudy(theStudy.getStudyId());
				}
			});
			
			//Event handler for a selected studyID
			studyIdLbl.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					for(Study thisStudy: currentStudiesList){
						if(thisStudy.getStudyId().equals(studyIdLbl.getText())){
							 HTML currentStudyId = new HTML("<div class='titles'>"+thisStudy.getStudyId()+" Study Update</div><hr />");
							HorizontalPanel studyButtonsPane = new HorizontalPanel();
							VerticalPanel labelsPanel = new VerticalPanel();
							VerticalPanel textBoxesPanel = new VerticalPanel();
							HorizontalPanel formPanel = new HorizontalPanel(); 
							//Styling textBoxes and initializing panels
							textBoxesPanel.setStyleName("formTextBoxesPanel");labelsPanel.setSpacing(6);studyButtonsPane.setSpacing(10);
							studyIdTxtBx.setStyleName("input");studyTitleTxtBx.setStyleName("input");studyDescTxtBx.setStyleName("textAreaInput");studyStartDateTxtBx.setStyleName("input");studyEndDateTxtBx.setStyleName("input");
							studySpeciesTxtBx.setStyleName("input");
							//populate the textBox
							studyIdTxtBx.setText(thisStudy.getStudyId());
							studyTitleTxtBx.setText(thisStudy.getStudyTitle());
							studyDescTxtBx.setText(thisStudy.getStudyDetails());
							studyStartDateTxtBx.setValue(thisStudy.getStudyDateStart());
							studyEndDateTxtBx.setValue(thisStudy.getStudyDateFinish());
							studySpeciesTxtBx.setText(thisStudy.getSpeciesName());
							//Labels
							Label studyIdLbl = new Label("Study ID: ");
							Label studyTitleLbl= new Label("Study Title ");
							Label studyDescriptionLbl = new Label("Study Details: ");
							Label studyStartDateLbl = new Label("Start Date: ");
							Label studyEndDateLbl = new Label("End Date: ");
							Label studySpeciesLbl = new Label("Study Species: ");
							//Add to panels
							labelsPanel.add(studyIdLbl); textBoxesPanel.add(studyIdTxtBx);
							labelsPanel.add(studyTitleLbl);textBoxesPanel.add(studyTitleTxtBx);
							labelsPanel.add(studyStartDateLbl);textBoxesPanel.add(studyStartDateTxtBx);
							labelsPanel.add(studyEndDateLbl);textBoxesPanel.add(studyEndDateTxtBx);
							labelsPanel.add(studySpeciesLbl);textBoxesPanel.add(studySpeciesTxtBx);
							labelsPanel.add(studyDescriptionLbl);textBoxesPanel.add(studyDescTxtBx);
							formPanel.add(labelsPanel);formPanel.add(textBoxesPanel);
							studyButtonsPane.add(editStudyBtn);
							selectedButtonsPanel.clear();
							selectedButtonsPanel.add(currentStudyId);
							selectedButtonsPanel.add(saveResultLbl);
							selectedButtonsPanel.add(formPanel);
							selectedButtonsPanel.add(studyButtonsPane);
						}
				}}
				
			});
			
		}
		}
		//first of all clear the content of the scroll before adding 
		listScrollPanel.clear();
		listScrollPanel.add(studyPanel);
		
	}
	//START OF EVENT HANDLERS
	//click event handler for the add user button
	private class AddUserBtnClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			selectedButtonsPanel.clear();
			//UserDetails userForm = new UserDetails();
			AdminServiceClientImpl userFormImpl = new AdminServiceClientImpl(GWT.getModuleBaseURL() + "adminservice");
			
			selectedButtonsPanel.add(userFormImpl.getUserFormGui());
		}
		
	}
	//click event handler for the edit user button
	private class EditUserBtnClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			  InputValidator emailValid = new InputValidator();
			  saveResultLbl.setStyleName("error");
				if(emailValid.isEmpty(username.getText())==true || emailValid.isEmpty(password.getText())==true|| emailValid.isEmpty(firstname.getText())==true || emailValid.isEmpty(lastname.getText())==true || emailValid.isEmpty(email.getText())==true)
					{
						saveResultLbl.setText("All fields must be populated !!!");  //need to globalize these components.
					
					}
				else if(!emailValid.isValidEmailAddress(email.getText())){
					saveResultLbl.setText("Email Address is invalid!!");
				}else{
					int userId = Integer.parseInt(userID.getText());
					adminServiceImpl.saveUser(username.getText(), password.getText(), firstname.getText(), lastname.getText(), email.getText(),userId, userAccessLevel.getSelectedValue(), "Update");
					//update the result lable
					saveResultLbl.setStyleName("success");
					saveResultLbl.setText("Update completed Successfully");
				}
			
		}
		
	}//END OF DISPLAY OF STUDY
	//START OF DISPLAY OF CONFIGURATIONS
	public void displayAllConfigurations(SpeciesConfiguration rpcListOfConfigs){
		final List<SpeciesConfiguration> currentConfigList = rpcListOfConfigs.getAllConfigList();
		HTML lookslikeSpeciesTitle = new HTML("<div class='subtitles'>List of configured species lookslike </div> <hr />");
		configPanel.add(lookslikeSpeciesTitle);
		for(final SpeciesConfiguration theConfig: currentConfigList){
			HorizontalPanel listOfConfigPanel = new HorizontalPanel();
			final Label configIdLbl = new Label(Integer.toString(theConfig.getConfigurationId()));
			Label configNameLbl = new Label(theConfig.getConfigurationName());
			HTML separator = new HTML("&nbsp - &nbsp");
			Button deleteBtn = new Button(); deleteBtn.setStyleName("delete");
			HTML lineseparator = new HTML("<hr />");
			//add the study details to the panel
			listOfConfigPanel.add(configIdLbl);listOfConfigPanel.add(separator); listOfConfigPanel.add(configNameLbl);listOfConfigPanel.add(deleteBtn);
			configPanel.add(listOfConfigPanel); configPanel.add(lineseparator);
			//this is on click delete button to delete the looks like config
			deleteBtn.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					boolean checkOk = Window.confirm("Are you sure you want to delete this configuration..?");
					if(checkOk==true){
					adminServiceImpl.deleteLookslikeConfig(theConfig.getConfigurationId());
					}
				}
				
			});
		}
		listScrollPanel.clear();
		listScrollPanel.add(configPanel);
	}
	public void displaySpecies(final Species configListOfSpecies){
		final List<Species> listOfspecies = configListOfSpecies.getListOfSpecies();
		HTML listOfSpeciesTitle = new HTML("<div class='subtitles'>List of Configured Species </div> <hr />");
		configPanel.add(listOfSpeciesTitle);
		for(Species theSpecies: listOfspecies){
			HorizontalPanel listSpeciesPanel = new HorizontalPanel();
			final Label speciesIdLbl = new Label(theSpecies.getSpeciesId());
			Label speciesNameLbl = new Label(theSpecies.getSpeciesName());
			HTML separator = new HTML("&nbsp - &nbsp");
			HTML lineseparator = new HTML("<hr />");
			//add the study details to the panel
			listSpeciesPanel.add(speciesIdLbl);listSpeciesPanel.add(separator); listSpeciesPanel.add(speciesNameLbl);
			configPanel.add(listSpeciesPanel); configPanel.add(lineseparator);
			speciesIdLbl.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					saveResultLbl.setText("");saveResultLbl.setStyleName("");
					selectedButtonsPanel.clear();
					for(Species theSpecies: listOfspecies){
						//check if the user click is the correct
						if(theSpecies.getSpeciesId().equals(speciesIdLbl.getText())){
							 HTML selectedSpecies = new HTML("<div class='titles'>"+theSpecies.getSpeciesId()+" Species Update</div><hr />");
								
							HorizontalPanel userButtonsPane = new HorizontalPanel();
							VerticalPanel labelsPanel = new VerticalPanel();
							VerticalPanel textBoxesPanel = new VerticalPanel();
							HorizontalPanel formPanel = new HorizontalPanel(); 
							//styling
							textBoxesPanel.setStyleName("formTextBoxesPanel");labelsPanel.setSpacing(6);userButtonsPane.setSpacing(10);
							//populate the textBox
							speciesIdTxtBx.setText(theSpecies.getSpeciesId()); speciesIdTxtBx.setEnabled(false);
							speciesNameTxtBx.setText(theSpecies.getSpeciesName());
							speciesDescTxtBx.setText(theSpecies.getSpeciesDescription());
							speciesSimilar1TxtBx.setText(theSpecies.getSpeciesLooklike1());
							speciesSimilar2TxtBx.setText(theSpecies.getSpeciesLooklike2());
							speciesSimilar3TxtBx.setText(theSpecies.getSpeciesLooklike3());
							//Styling textBoxes
							speciesIdTxtBx.setStyleName("input");speciesNameTxtBx.setStyleName("input");speciesDescTxtBx.setStyleName("textAreaInput");speciesSimilar1TxtBx.setStyleName("input");speciesSimilar2TxtBx.setStyleName("input");
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
						
							selectedButtonsPanel.add(selectedSpecies);
							selectedButtonsPanel.add(saveResultLbl);
							
							selectedButtonsPanel.add(formPanel);
							buttonsPanel.add(saveSpeciesChangesBtn);
							buttonsPanel.add(cancelBtn);
							selectedButtonsPanel.add(buttonsPanel);
							}
					
				}
					//selectedButtonsPanel.add(editForm);
				}
				
			});
			
		}
		
		listScrollPanel.clear();
		listScrollPanel.add(configPanel);
	}
	public void displayChangeSaveResult(String changesStatus){
		if(changesStatus.equals("Failed")){
			saveResultLbl.setStyleName("error");
			saveResultLbl.setText(changesStatus);
		}else{
			saveResultLbl.setStyleName("success");
			saveResultLbl.setText(changesStatus);
		}
			
	}
	//click event handler for the editStudyBtn button
	private class EditStudyBtnClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			//setup email validation object
			InputValidator textValidator = new InputValidator();
			if(textValidator.isEmpty(studyIdTxtBx.getText()) || textValidator.isEmpty(studyTitleTxtBx.getText()) || textValidator.isEmpty(studySpeciesTxtBx.getText()))
			{	
				//saveResultLbl.setText("Study ID, Title and Species must be populated !!!");
				Window.alert("Study ID, Title and Species must be populated !!!");
			
			}//All good then invoke RPC call
		else{
			DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd");
			String datestart = ""; String dateEnd = ""; 
			if(studyStartDateTxtBx.getValue()==null) datestart = "9999-00-00"; else datestart = fmt.format(studyStartDateTxtBx.getValue());//formatter.format(studyStartDateTxtBx.getValue());
			if(studyEndDateTxtBx.getValue()==null)  dateEnd = "9999-00-00";  else dateEnd = fmt.format(studyEndDateTxtBx.getValue());//formatter.format(studyEndDateTxtBx.getValue());
			if(textValidator.isEmpty(studyDescTxtBx.getText())==true) studyDescTxtBx.setText("TBS");
				  
			if(textValidator.isEmpty(studyDescTxtBx.getText())) studyDescTxtBx.setText("TBS");
				//make an RPC call to save the study
			  adminServiceImpl.saveStudy(studyIdTxtBx.getText(), studyTitleTxtBx.getText(), studyDescTxtBx.getText(), datestart, dateEnd, studySpeciesTxtBx.getText(), "Edit");
			}
		}
			
			
	}
	//click event handler for the list users button
		private class ListUsersBtnClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				//clear stuff currently on the screen
				userPanel.clear(); selectedButtonsPanel.clear();
				
				//invoke RPC call to retrieve list of all users in the jirtbios database
				adminServiceImpl.getListOfusers();
				
			}
			
		}
	
	//click event handler for the list study button
		private class ListStudiesBtnClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			//clear the current stuff on the stufy panel first and set title 
			studyPanel.clear(); selectedButtonsPanel.clear();
			
			//invoke RPC call to retrieve list of all studies in the jirtbios database
			adminServiceImpl.getListOfstudies();
		}
			
		}
		//click event handler for the add study button
		private class AddStudyBtnClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				selectedButtonsPanel.clear();
				//UserDetails userForm = new UserDetails();
				AdminServiceClientImpl studyForm = new AdminServiceClientImpl(GWT.getModuleBaseURL() + "adminservice");
				
				selectedButtonsPanel.add(studyForm.getStudyFormGui());
				
			}
			
		}
		private class ListSpeciesBtnClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				studyPanel.clear(); selectedButtonsPanel.clear();userPanel.clear();configPanel.clear();configButtons.clear();
				WestPanelTitile.setText("Manage species");
				configPanel.add(WestPanelTitile);
				configButtons.add(addSpecies);
				configButtons.add(addImagePathsBtn);
				
				//add the title and the button to add a study
				configPanel.add(configButtons);
				//RPC CALL
				adminServiceImpl.getAllSpecies();
			}
			
		}
		//Click event handler for the list configuration button
		private class ListConfigBtnClickhandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			//clear the current stuff on the panel first and set title 
			studyPanel.clear(); selectedButtonsPanel.clear();userPanel.clear();configPanel.clear();configButtons.clear();
			WestPanelTitile.setText("Manage species");
			configPanel.add(WestPanelTitile);
			configButtons.add(addLooksLikeBtn);
			configButtons.add(listSpeciesBtn);
			//add the title and the button to add a study
			configPanel.add(configButtons);
			
			//Make RPC call to retrieve list of looks like records from the database
			adminServiceImpl.getAllConfigurations();
		}
		}	
		private class AddSpeciesBtnClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				selectedButtonsPanel.clear();
				//Form UI
				AdminServiceClientImpl speciesForm = new AdminServiceClientImpl(GWT.getModuleBaseURL() + "adminservice");
				selectedButtonsPanel.add(speciesForm.getSpeciesFormGui());
			}
			
		}
		private class CancelBtnClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				selectedButtonsPanel.clear();
			}
			
		}
		private class SaveSpesChangesBtnClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				//setup email validation object
				InputValidator textValidator = new InputValidator();
				if(textValidator.isEmpty(speciesNameTxtBx.getText()))
				{
					saveResultLbl.setStyleName("error");
					saveResultLbl.setText("Species Name field must be filled...");
				
				}//All good then invoke RPC call
			else{
				adminServiceImpl.saveEditedSpecies(speciesIdTxtBx.getText(), speciesNameTxtBx.getText(), speciesDescTxtBx.getText(), speciesSimilar1TxtBx.getText(), speciesSimilar2TxtBx.getText(), speciesSimilar3TxtBx.getText());
				saveResultLbl.setText("");
				}
				
			}
			
		}
		//Add looks like form
		private class AddLooksLikeBtnClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				//clear current stuff on the panel
				selectedButtonsPanel.clear();
				//AddLooksLike addLooklikeFomr = new AddLooksLike
				AdminServiceClientImpl speciesLooksLikeForm = new AdminServiceClientImpl(GWT.getModuleBaseURL() + "adminservice");
				selectedButtonsPanel.add(speciesLooksLikeForm.getSpeciesLookslikeFormGui());
			}
			
		}
		private class StudyConfigListBtnClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				selectedButtonsPanel.clear();
				AdminServiceClientImpl studyConfigGui = new AdminServiceClientImpl(GWT.getModuleBaseURL() + "adminservice");
				selectedButtonsPanel.add(studyConfigGui.getStudyConfigGui());
			}
			
		}
		public void displayDeleteConfigConfirm(String deleteConf){
			if(deleteConf.equals("Failed")){
				Window.alert(deleteConf + " delete failed !!");
			}else{
				Window.alert(deleteConf + " deleted successfully!!");
			}
		}
		public void displayStudyActive(String activation){
			if(activation.equals("Failed")){
				Window.alert(activation + " failed to activate due to other study being active and/or other reasons");
			}else{
				Window.alert(activation + " study is set to active successfully!!");
			}
		}
		public void displayStudyDeActive(String activation){
			if(activation.equals("Failed")){
				Window.alert(activation + " failed to deactivate due to system error, try again later...");
			}else{
				Window.alert(activation + " study status is set to Inactive successfully!!");
			}
		}
		public void displayUserDeActive(String activation){
			if(activation.equals("Failed")){
				Window.alert(activation + " failed to deactivate due to system error, try again later...");
			}else{
				Window.alert(activation + " is deactivated successfully!!");
			}
		}
		public void displayUserActive(String activation){
			if(activation.equals("Failed")){
				Window.alert(activation + " failed to activate due to system error, try again later...");
			}else{
				Window.alert(activation + " is activated successfully!!");
			}
		}
		private class AddImagePathsBtnClickHndler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				selectedButtonsPanel.clear();
				adminServiceImpl.getImageNames();
			}
			
		}
		public void displayLoadedImages(ImagesList theList){
			ArrayList<String> currentImageNames = theList.getImageNames();
			for(int i=0; i < currentImageNames.size(); i++){
				Label imageNameLbl = new Label(currentImageNames.get(i));
				selectedButtonsPanel.add(imageNameLbl);
			}
			
		}
}