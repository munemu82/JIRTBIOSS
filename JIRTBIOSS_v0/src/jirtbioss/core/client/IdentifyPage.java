package jirtbioss.core.client;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jirtbioss.core.client.model.Imageidentity;
import jirtbioss.core.client.model.ImagesList;
import jirtbioss.core.client.model.Species;
import jirtbioss.core.client.model.SpeciesConfiguration;
import jirtbioss.core.client.model.Study;
import jirtbioss.core.client.service.SpeciesListServiceClientImpl;
import jirtbioss.core.client.utility.FilterButtonsHandler;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class IdentifyPage extends Composite {
	
	//super global variables
	  private HTML speciesDescription = new HTML();  //this is used by the inner class "Identification"
	//Main controls
		private HTML identifyPageTitle = new HTML("<h1 align='center'>Image Identications for Data collections</h1>");
		private VerticalPanel identifyPanel = new VerticalPanel();
		private HorizontalPanel imgPlusFilterPanel = new HorizontalPanel();
		private VerticalPanel imgIdentityPanel = new VerticalPanel();
		
		//setup identification form study configus
		private List<Study> colours;
		private List<Study> behaviors;
		private List<Study> poses;
		private List<Study> scales;
		private String activeStudyId;
		//Image controls
		private HorizontalPanel imgControlPanel = new HorizontalPanel();
		private HorizontalPanel imgFinishControl = new HorizontalPanel();
		private Button nextBtn = new Button("Next");
		private CheckBox noAnimalChkBx = new CheckBox("No Animal");
		private Button finishBtn = new Button("Finish");
		private Button  discussImg = new Button ("Disccuss");
		private Button facebookImg = new Button("Share");
		private Button tweetImg = new Button("Tweet");
		int index = 0;
		private Image baseImg;
		private List<String> imageURLs;
		
		//Identify Form Controls
		Label speciesLabel = new Label("");
		private HorizontalPanel formControlPanel1 = new HorizontalPanel();
		private HorizontalPanel formControlPanel2 = new HorizontalPanel();
		private ListBox looksLikeSpecies = new ListBox();
		private TextBox autoCompltSearch = new TextBox();
		private Button colorBtn = new Button("Color");
		private Button poseBtn  = new Button("Pose");
		private Button scaleBtn = new Button("Scale");
		private HorizontalPanel btnFilterPanel = new HorizontalPanel();
		private HorizontalPanel poseBtnFilterPanel = new HorizontalPanel();
		private HorizontalPanel scaleBtnFilterPanel = new HorizontalPanel();
		private Label debugLabel = new Label("This a Debug");
		private Button closePoseFilterBtn = new Button("Clear");
		private Button closeColorFilterBtn = new Button("Clear");
		private Button closeScaleFilterBtn = new Button("Clear");
		private ScrollPanel scrollPanel;
		private SpeciesListServiceClientImpl ServiceImpl;
		
		//Identification list grid
		VerticalPanel speciesNamesPanel = new VerticalPanel();
		VerticalPanel filteredPanel = new VerticalPanel();
		
		//Filter by pose buttons 
		private Button straighPoseBtn = new Button();
		private Button leftPoseBtn = new Button();
		private Button rightPoseBtn = new Button();
		private Button backTailPoseBtn = new Button();
		//filter by color buttons
		private Button redBtn = new Button();
		private Button brownBtn = new Button();
		private Button yellowBtn = new Button();
		private Button whiteBtn = new Button();
		private Button blackBtn = new Button();
		private Button grayBtn = new Button();
		//filter by scale buttons
		private Button tallBtn = new Button();
		private Button smallBtn = new Button();
		private Button lankyBtn = new Button();
		private Button stockyBtn = new Button();
		//filter flag to be send to RPC call for filtering the species list
		private String filterColorTxt ="";
		private String filterScaleTxt ="";
		private String filterPoseTxt ="";
		//Constructor
		public IdentifyPage(SpeciesListServiceClientImpl ServiceImpl){
			//Initializing the main content panel
			initWidget(this.identifyPanel);
			this.ServiceImpl = ServiceImpl;
			
			//INTIAL RPC callS and load the species when this page is opened
			ServiceImpl.getSpeciesList();			// list of species
			ServiceImpl.speciesLookslikeList();
			ServiceImpl.getActiveStudyColors();
			ServiceImpl.getActiveStudyBehaviors();
			ServiceImpl.getActiveStudyPoses();
			ServiceImpl.getActiveStudy();
			ServiceImpl.getActiveStudyScales();
			//initialize study config options
			colours = new ArrayList<Study>();
			behaviors= new ArrayList<Study>();
			poses = new ArrayList<Study>();
			scales = new ArrayList<Study>();
			activeStudyId = "NONE";
			
			//set css style of the main panel
			this.identifyPanel.setStyleName("identityPanel");
			this.identifyPanel.add(identifyPageTitle);				//add title to the main Panel
			this.imgIdentityPanel.setSpacing(8);
			this.noAnimalChkBx.setStyleName("customCheckBox");
			/*
			 * IMAGE CONTROLS SECTION
			 */
			//Define Buttons and checkboxes evnent Handlers
			this.noAnimalChkBx.addClickHandler(new noAnimalChkBxHandler()); //this.nextBtn.addClickHandler(new nextBtnClickHandler());
			this.finishBtn.addClickHandler(new FinishBtnClickHandler());
			this.colorBtn.addClickHandler(new ColorBtnClickHandler());
			this.poseBtn.addClickHandler(new PoseBtnClickHandler());
			this.scaleBtn.addClickHandler(new ScaleBtnClickHandler());
			// Next Button Click event Handler, when this clicked another image displayed
			this.nextBtn.addClickHandler(new nextBtnClickHandler());
			this.looksLikeSpecies.addChangeHandler(new looksLikesChangeHandler());
			
			//Add styles to buttons
			this.finishBtn.setStyleName("Disabledgwt-Button");
			this.facebookImg.setStyleName("Enabledgwt-Button");
			this.tweetImg.setStyleName("Enabledgwt-Button");
			this.discussImg.setStyleName("Enabledgwt-Button");
			this.nextBtn.setStyleName("Enabledgwt-Button");
			
			//add finish button and checkbox on the img panel
			this.imgFinishControl.add(finishBtn);
			this.imgFinishControl.add(noAnimalChkBx);
			//set the finish button to disabled 
			this.finishBtn.setEnabled(false);
			
			//add ImgFinishControl to the main panel (identifyPanel)
			this.identifyPanel.add(imgFinishControl);
			
			//Adding controls to the image control panel
			this.imgControlPanel.add(facebookImg);
			this.imgControlPanel.add(tweetImg);
			this.imgControlPanel.add(discussImg);
			this.imgControlPanel.add(nextBtn);					//add next button
						
			//Add the image control panel to the main panel
			this.identifyPanel.add(imgControlPanel);
			this.imgControlPanel.setVisible(false);
			//END OF IMAGE CONTROLS SECTION
				 
			/*IMAGE IDENTIFICATION CONTROLS SECTION */
		      // identify form controls
		      //-------------------------------------------------
		      //1) Drop down list of species looks like
		      looksLikeSpecies.addStyleName("DropDowninput");
		     looksLikeSpecies.addItem("Looks Like");		      
		      
		      //2) Search input box
		      autoCompltSearch.setStyleName("input");
		    		    
		     //Adding styles to Control Panels and buttons
		      this.formControlPanel1.setStyleName("paddedHorizontalPanel");
		      this.formControlPanel2.setStyleName("paddedHorizontalPanel");
		      this.imgIdentityPanel.setStyleName("toolbar");
		      this.colorBtn.setStyleName("custom-gwt-Button");
		      colorBtn.setSize("115px", "35px");
		      this.scaleBtn.setStyleName("custom-gwt-Button");
		      scaleBtn.setSize("115px", "35px");
		      this.poseBtn.setStyleName("custom-gwt-Button");
		      poseBtn.setSize("115px", "35px");
		    
		    //Adding form Controls to the first top Panel
		     this.formControlPanel1.add(looksLikeSpecies);		//add drop down select looks like to the identify panel
		    
		     //Setup the filters panels
		     this.btnFilterPanel.setVisible(false);             //this is set to invisible but shown when button is clicked
		     this.poseBtnFilterPanel.setVisible(false);
		     this.scaleBtnFilterPanel.setVisible(false);
			 this.imgIdentityPanel.add(btnFilterPanel);
			 this.imgIdentityPanel.add(scaleBtnFilterPanel);
			 this.imgIdentityPanel.add(poseBtnFilterPanel);
		     //Add form buttons controls to the second top Panel
		     this.formControlPanel2.add(colorBtn);
		     this.formControlPanel2.add(scaleBtn);
		     this.formControlPanel2.add(poseBtn);
		     
		     //Add the form control panels to the identify panel
		      this.imgIdentityPanel.add(formControlPanel1);
		      this.imgIdentityPanel.add(formControlPanel2);
		     
		      imgIdentityPanel.add(new HTML("<hr />"));
		       //Species list
			  this.scrollPanel = new ScrollPanel();
			   scrollPanel.setSize("400px", "350px");
			    /* END OF IMAGE IDENTIFICATION CONTROLS SECTION*/
			   
		     // baseImg = new Image(imageURLs.get(0));
			   baseImg = new Image("applicationImages/image0.JPG");	//setup initial image
			   // make panels to hold images
			VerticalPanel imgPanel = new VerticalPanel();
			
			//set size of the image
			baseImg.setPixelSize(512, 512);
			imgPanel.add(baseImg);
		
			// add image panel to the container
			this.imgPlusFilterPanel.add(imgPanel);
			this.imgPlusFilterPanel.add(imgIdentityPanel);
			//Add the container panel to the Main panel
			this.identifyPanel.add(imgPlusFilterPanel);	
			//scrollPanel.add(speciesNamesPanel);
			  // this.imgIdentityPanel.add(scrollPanel);
		}
		//RPC LIST OF LOOKS LIKE to populate the ListBox
		public void displayLookslikeList(SpeciesConfiguration listOfLooksLike){
			List<String> currentLooksLikeList = listOfLooksLike.getLooksLikeList();
			for(int i=0; i < currentLooksLikeList.size(); i++){
				 looksLikeSpecies.addItem(currentLooksLikeList.get(i));
			}
			 looksLikeSpecies.addItem("Clear");
		}
		
	/*
	 * CLICK HANDLERS WHEN BUTTONS CLICK EVENT OCCURS 	
	 */
		private class nextBtnClickHandler implements ClickHandler{
			@Override
			public void onClick(ClickEvent event) {
					noAnimalChkBx.setEnabled(true);                 //alwasys enable this when next image is loaded
					ServiceImpl.getImages();
					
					//Now set the next button disabled to complete identification
					finishBtn.setStyleName("Disabledgwt-Button");
					finishBtn.setEnabled(false);
					
					//hide the next image control panel
					imgControlPanel.setVisible(false);
					//Display the finish control panel
					imgFinishControl.setVisible(true);
					//Show the identification options
					
					imgIdentityPanel.setVisible(true);
					}
			}
		
		//implements CheckBox No Animal Present action events
		private class noAnimalChkBxHandler implements ClickHandler{
			public void onClick(ClickEvent event) {
				if(noAnimalChkBx.isChecked()==true){
					imgIdentityPanel.setVisible(false);
					finishBtn.setStyleName("Enabledgwt-Button");
					finishBtn.setEnabled(true);
				}else
				{
					imgIdentityPanel.setVisible(true);
					finishBtn.setStyleName("Disabledgwt-Button");
					finishBtn.setEnabled(false);
				}
			}
			
		}
		//END OF CONSTRUCTOR
		
		//Finish button Click Handler
		private class FinishBtnClickHandler implements ClickHandler{
			@Override
			public void onClick(ClickEvent event) {
				//first hide this panel
				imgFinishControl.setVisible(false);
				//Reset the checkbox / uncheck it
				noAnimalChkBx.setValue(false);
				//Clear identify image panel and hide it
				imgIdentityPanel.setVisible(false);
				//Display the next image control panel
				imgControlPanel.setVisible(true);
			}
		}
		
		//Color Button click event handler
		private class ColorBtnClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				//hide the Looks like drop down list and search field
				scaleBtnFilterPanel.setVisible(false);
				poseBtnFilterPanel.setVisible(false);
				formControlPanel1.setVisible(false);
				//show the filters
				btnFilterPanel.setVisible(true);
				displayFilters("Color");
				//perform an RPC call to filter the species list
				//test 1- filter by red
				
				if(!"Color".equals(colorBtn.getText())){
					if("Scale".equals(scaleBtn.getText()) && "Pose".equals(poseBtn.getText())){
					String filterSQL = "SELECT * FROM species where colour='"+colorBtn.getText()+"'";
					ServiceImpl.getSpeciesNamesByFilter(filterSQL);
					System.out.println(filterSQL);
					}else if(!"Scale".equals(scaleBtn.getText())){
						String filterSQL = "SELECT * FROM species where colour='"+colorBtn.getText()+"' AND scale='"+scaleBtn.getText()+"'";
						ServiceImpl.getSpeciesNamesByFilter(filterSQL);
						System.out.println(filterSQL);
						if(!"Pose".equals(poseBtn.getText())){
							 filterSQL = "SELECT * FROM species where colour='"+colorBtn.getText()+"' AND scale='"+scaleBtn.getText()+"' AND pose='"+poseBtn.getText()+"'";
							ServiceImpl.getSpeciesNamesByFilter(filterSQL);
						System.out.println(filterSQL);
						}
					}else if(!"Pose".equals(poseBtn.getText())){
						String filterSQL = "SELECT * FROM species where colour='"+colorBtn.getText()+"' AND pose='"+poseBtn.getText()+"'";
						//set checkpoint here
						ServiceImpl.getSpeciesNamesByFilter(filterSQL);
						System.out.println(filterSQL);
						if(!"Scale".equals(scaleBtn.getText())){
							 filterSQL = "SELECT * FROM species where colour='"+colorBtn.getText()+"' AND scale='"+scaleBtn.getText()+"' AND pose='"+poseBtn.getText()+"'";
								ServiceImpl.getSpeciesNamesByFilter(filterSQL);
							System.out.println(filterSQL);
						}
					}else {//if(!"Scale".equals(scaleBtn.getText()) && !"Pose".equals(poseBtn.getText())){
						String filterSQL = "SELECT * FROM species where colour='"+colorBtn.getText()+"' AND scale='"+scaleBtn.getText()+"' AND pose='"+poseBtn.getText()+"'";
						ServiceImpl.getSpeciesNamesByFilter(filterSQL);
						System.out.println(filterSQL);
					}
				}
				
			}
		}
		//Pose Button click event handler
		private class PoseBtnClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				//hide the Looks like drop down list and search field
				formControlPanel1.setVisible(false);
				btnFilterPanel.setVisible(false);
				scaleBtnFilterPanel.setVisible(false);
				poseBtnFilterPanel.setVisible(true);
				//show the filters
				displayFilters("Pose");
				if(!"Pose".equals(poseBtn.getText())){
					if("Scale".equals(scaleBtn.getText()) && "Color".equals(colorBtn.getText())){
					String filterSQL = "SELECT * FROM species where pose='"+poseBtn.getText()+"'";
					ServiceImpl.getSpeciesNamesByFilter(filterSQL);
					System.out.println(filterSQL);
					}else if(!"Scale".equals(scaleBtn.getText())){
						String filterSQL = "SELECT * FROM species where pose='"+poseBtn.getText()+"' AND scale='"+scaleBtn.getText()+"'";
						ServiceImpl.getSpeciesNamesByFilter(filterSQL);
						System.out.println(filterSQL);
						if(!"Color".equals(colorBtn.getText())){
							 filterSQL = "SELECT * FROM species where colour='"+colorBtn.getText()+"' AND scale='"+scaleBtn.getText()+"' AND pose='"+poseBtn.getText()+"'";
							ServiceImpl.getSpeciesNamesByFilter(filterSQL);
						System.out.println(filterSQL);
						}
					}else if(!"Color".equals(colorBtn.getText())){
						String filterSQL = "SELECT * FROM species where colour='"+colorBtn.getText()+"' AND pose='"+poseBtn.getText()+"'";
						//set checkpoint here
						ServiceImpl.getSpeciesNamesByFilter(filterSQL);
						System.out.println(filterSQL);
						if(!"Scale".equals(scaleBtn.getText())){
							 filterSQL = "SELECT * FROM species where colour='"+colorBtn.getText()+"' AND scale='"+scaleBtn.getText()+"' AND pose='"+poseBtn.getText()+"'";
								ServiceImpl.getSpeciesNamesByFilter(filterSQL);
							System.out.println(filterSQL);
						}
					}else {//if(!"Scale".equals(scaleBtn.getText()) && !"Pose".equals(poseBtn.getText())){
						String filterSQL = "SELECT * FROM species where colour='"+colorBtn.getText()+"' AND scale='"+scaleBtn.getText()+"' AND pose='"+poseBtn.getText()+"'";
						ServiceImpl.getSpeciesNamesByFilter(filterSQL);
						System.out.println(filterSQL);
					}
				}
				
			}
		}
		//Scale Button click event handler
		private class ScaleBtnClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				//hide the Looks like drop down list and search field
				formControlPanel1.setVisible(false);
				poseBtnFilterPanel.setVisible(false);
				btnFilterPanel.setVisible(false);
				scaleBtnFilterPanel.setVisible(true);
				//show the filters
				displayFilters("Scale");
				if(!"Scale".equals(scaleBtn.getText())){
					if("Color".equals(colorBtn.getText()) && "Pose".equals(poseBtn.getText())){
					String filterSQL = "SELECT * FROM species where scale='"+scaleBtn.getText()+"'";
					ServiceImpl.getSpeciesNamesByFilter(filterSQL);
					System.out.println(filterSQL);
					}else if(!"Color".equals(colorBtn.getText())){
						String filterSQL = "SELECT * FROM species where colour='"+colorBtn.getText()+"' AND scale='"+scaleBtn.getText()+"'";
						ServiceImpl.getSpeciesNamesByFilter(filterSQL);
						System.out.println(filterSQL);
						if(!"Pose".equals(poseBtn.getText())){
							 filterSQL = "SELECT * FROM species where colour='"+colorBtn.getText()+"' AND scale='"+scaleBtn.getText()+"' AND pose='"+poseBtn.getText()+"'";
							ServiceImpl.getSpeciesNamesByFilter(filterSQL);
						System.out.println(filterSQL);
						}
					}else if(!"Pose".equals(poseBtn.getText())){
						String filterSQL = "SELECT * FROM species where scale='"+scaleBtn.getText()+"' AND pose='"+poseBtn.getText()+"'";
						//set checkpoint here
						ServiceImpl.getSpeciesNamesByFilter(filterSQL);
						System.out.println(filterSQL);
						if(!"Color".equals(colorBtn.getText())){
							 filterSQL = "SELECT * FROM species where colour='"+colorBtn.getText()+"' AND scale='"+scaleBtn.getText()+"' AND pose='"+poseBtn.getText()+"'";
								ServiceImpl.getSpeciesNamesByFilter(filterSQL);
							System.out.println(filterSQL);
						}
					}else {//if(!"Scale".equals(scaleBtn.getText()) && !"Pose".equals(poseBtn.getText())){
						String filterSQL = "SELECT * FROM species where colour='"+colorBtn.getText()+"' AND scale='"+scaleBtn.getText()+"' AND pose='"+poseBtn.getText()+"'";
						ServiceImpl.getSpeciesNamesByFilter(filterSQL);
						System.out.println(filterSQL);
					}
				}
				
			}
		}
		//function to setup diplay of filters
		public void displayFilters(String filterName){  
			FilterButtonsHandler filterButtonsHandler = new FilterButtonsHandler();  	//straight pose
			//clear color filters handler
			this.closeColorFilterBtn.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					//Hide the filters 
					btnFilterPanel.setVisible(false);
					//show the panel
					formControlPanel1.setVisible(true);
					//Reset the pose button
					colorBtn.setStyleName("custom-gwt-Button");
					colorBtn.setText("Color");
					//this will clear everything/Call RPC to reset the list to original -  still need to find way only to clear color filters
					ServiceImpl.getSpeciesList();
					
					}
			});
			this.closeScaleFilterBtn.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					//Hide the filters 
					scaleBtnFilterPanel.setVisible(false);
					//show the panel
					formControlPanel1.setVisible(true);
					//Reset the pose button
					scaleBtn.setStyleName("custom-gwt-Button");
					scaleBtn.setText("Scale");
					//this will clear everything/Call RPC to reset the list to original -  still need to find way only to clear color filters
					ServiceImpl.getSpeciesList();
					}
			});
			//clear Scale filters handler
			//Clear pose filters handler
			this.closePoseFilterBtn.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					//Hide the filters 
					poseBtnFilterPanel.setVisible(false);
					//show the panel
					formControlPanel1.setVisible(true);
					//Reset the pose button
					poseBtn.setStyleName("custom-gwt-Button");
					poseBtn.setText("Pose");
					//this will clear everything/Call RPC to reset the list to original -  still need to find way only to clear color filters
					ServiceImpl.getSpeciesList();
					}
			});
			//add filters
			switch(filterName){
			case "Color":
				//btnFilterPanel.clear();
				this.btnFilterPanel.setStyleName("filterPanel");
				redBtn.setStyleName("filterIconsRed");
				redBtn.setTitle("Red");
				brownBtn.setStyleName("filterIconsBrown");
				brownBtn.setTitle("Brown");
				yellowBtn.setStyleName("filterIconsYellow");
				yellowBtn.setTitle("Yellow");
				whiteBtn.setStyleName("filterIconsWhite");
				whiteBtn.setTitle("White");
				blackBtn.setStyleName("filterIconsBlack");
				blackBtn.setTitle("Black");
				grayBtn.setStyleName("filterIconsGray");
				grayBtn.setTitle("Gray");
				
				//add filters 
				btnFilterPanel.add(redBtn);
				btnFilterPanel.add(yellowBtn);
				btnFilterPanel.add(whiteBtn);
				btnFilterPanel.add(grayBtn);
				btnFilterPanel.add(blackBtn);
				btnFilterPanel.add(brownBtn);
				this.closeColorFilterBtn.setStyleName("close");
				this.btnFilterPanel.add(closeColorFilterBtn);
				//Color Buttons filter event handler for different image colors
				filterButtonsHandler.setColorButton(this.redBtn,this.whiteBtn, this.yellowBtn, this.grayBtn,this.blackBtn, this.brownBtn, this.colorBtn);
				
				break;
			case "Scale":
				//btnFilterPanel.clear();
				scaleBtnFilterPanel.setStyleName("filterPanel");
				tallBtn.setStyleName("filterIconsTall");
				tallBtn.setTitle("Tall");
				smallBtn.setStyleName("filterIconsSmall");
				smallBtn.setTitle("Small");
				lankyBtn.setStyleName("filterIconsLanky");
				lankyBtn.setTitle("Lanky");
				stockyBtn.setStyleName("filterIconsStocky");
				stockyBtn.setTitle("Stocky");
				//Adding buttons to the filter
				scaleBtnFilterPanel.add(stockyBtn);
				scaleBtnFilterPanel.add(tallBtn);
				scaleBtnFilterPanel.add(lankyBtn);
				scaleBtnFilterPanel.add(smallBtn);
				this.closeScaleFilterBtn.setStyleName("close");
				this.scaleBtnFilterPanel.add(closeScaleFilterBtn);
				//Scale Buttons filter event handler for different image colors
				filterButtonsHandler.setScaleButton(this.tallBtn, this.smallBtn, this.lankyBtn, this.stockyBtn, this.scaleBtn);
				break;
			case "Pose":
				poseBtnFilterPanel.setStyleName("filterPanel");
				straighPoseBtn.setStyleName("filterIconsStraightPose");
				straighPoseBtn.setTitle("Straight");
				leftPoseBtn.setStyleName("filterIconsLeftPose");
				leftPoseBtn.setTitle("Left");
				rightPoseBtn.setStyleName("filterIconsRightPose");
				rightPoseBtn.setTitle("Right");
				backTailPoseBtn.setStyleName("filterIconsBackTailPose");
				backTailPoseBtn.setTitle("Back");
				
				//Adding buttons to the filter
				poseBtnFilterPanel.add(straighPoseBtn);
				poseBtnFilterPanel.add(leftPoseBtn);
				poseBtnFilterPanel.add(rightPoseBtn);
				poseBtnFilterPanel.add(backTailPoseBtn);
				this.closePoseFilterBtn.setStyleName("close");
				this.poseBtnFilterPanel.add(closePoseFilterBtn);
				//Pose buttons Filter event handlers for different image poses
				filterButtonsHandler.setPoseButton(this.straighPoseBtn, this.leftPoseBtn, this.rightPoseBtn, this.backTailPoseBtn, this.poseBtn);
				break;
			default :
					btnFilterPanel.clear();
					btnFilterPanel.add(debugLabel);
				break;
			}
			
		}
		            
		public void displaySpeciesListInfo(Species species){
			final List<Species> speciesList = species.getListOfSpecies();
			speciesNamesPanel.clear();
			 //create the suggestion data 	  
		      MultiWordSuggestOracle speciesSuggestion = new MultiWordSuggestOracle();  
		      
			 for(final Species aSpecies: speciesList){
				Label speciesLabel = new Label();
				speciesLabel.setText(aSpecies.getSpeciesName());
				speciesLabel.setStyleName("speciesNames");
				Label line = new Label("------------------------------------------------------");
				
				//Add species names into the data for suggestions
				speciesSuggestion.add(aSpecies.getSpeciesName());
				//add scrollpanel to the main panel
				speciesLabel.addClickHandler(new ClickHandler() {
			         @Override
			         public void onClick(ClickEvent event) {
			        	 imgIdentityPanel.setVisible(false);
			        	 noAnimalChkBx.setEnabled(false);
										        	
			        	 //Get the label text from actual selected event /get the source text
			        	 String txt = ((Label) event.getSource()).getText();
			        	 String description = aSpecies.getSpeciesDescription();
			        	 Identification mySpeciesId = new Identification(txt, description);
			        	 imgPlusFilterPanel.add(mySpeciesId);
			             }
			      });
				speciesNamesPanel.add(speciesLabel);
				
				speciesNamesPanel.add(line);
			}
			scrollPanel.add(speciesNamesPanel);						//CHECKPOINT ---------debug this ASK NEAL
			 //checkpoint
			  this.imgIdentityPanel.add(scrollPanel);
			   
			   //create the suggestion box and pass it the data created above
			      final SuggestBox suggestionBox = new SuggestBox(speciesSuggestion);
			      suggestionBox.setStyleName("input");
			      suggestionBox.addSelectionHandler(new SelectionHandler<Suggestion>(){
			    	  @Override
					public void onSelection(SelectionEvent<Suggestion> event) {
						//Get the label text from actual selected event /get the source text
			        	
						imgIdentityPanel.setVisible(false);
			        	 noAnimalChkBx.setEnabled(false);
			        	 String txt = suggestionBox.getText(); 
			        	 String Description = "";
			        	 for(final Species aSpecies: speciesList){
			        		 if(aSpecies.getSpeciesName().equals(txt)){
			        			 Description = aSpecies.getSpeciesDescription();
			        		 }
			        	 }
			        	 Identification mySpeciesId = new Identification(txt, Description);
			        	 imgPlusFilterPanel.add(mySpeciesId);
			        	 //clear suggestion box after using the select value
			        	 suggestionBox.setValue("");
			        }
			   });
			      //set width to 200px.
			      suggestionBox.setWidth("200");
			      formControlPanel1.add(suggestionBox);
			    
			  //check filters
			      
		}

//display next Image
public void displayNextImage(ImagesList images){
	imageURLs = images.getSpeciesImages();
	if(imageURLs.size() > 0){
	int imageIndex = 1;
		// next image
		baseImg.setUrl( imageURLs.get(index) );
		index = (index+1<imageURLs.size()) ? index+1 : 0;
		Window.alert("The current image is " +baseImg);
	}else{
		baseImg.setUrl("applicationImages/NoImage.png");
	}
}
public void displaySpeciesListInfoLooksLike(String speciesLooksLike){
	Species species = new Species();
	List<Species> speciesList = species.getListOfSpecies();
	
	for(final Species aSpecies: speciesList){
		
		//if(aSpecies.getSpeciesName().equals(speciesLooksLike)){
		if(aSpecies.getSpeciesLooklike1().equals(speciesLooksLike) || aSpecies.getSpeciesLooklike2().equals(speciesLooksLike)
				|| aSpecies.getSpeciesLooklike3().equals(speciesLooksLike)){
			speciesNamesPanel.clear();
			speciesLabel = new Label(aSpecies.getSpeciesName());
			speciesLabel.setStyleName("speciesNames");
			Label line = new Label("------------------------------------------------------");
			//add scrollpanel to the main panel
			speciesLabel.addClickHandler(new ClickHandler() {
		         @Override
		         public void onClick(ClickEvent event) {
		        	 imgIdentityPanel.setVisible(false);
		        	 noAnimalChkBx.setEnabled(false);
		        	 String txt = ((Label) event.getSource()).getText();
		        	 String Description = aSpecies.getSpeciesDescription();
		        	 Identification mySpeciesId = new Identification(txt, Description);
		        	 imgPlusFilterPanel.add(mySpeciesId);
		             }
		      });
						
			speciesNamesPanel.add(this.speciesLabel);
			speciesNamesPanel.add(line);
			
		}
				
	}
	 scrollPanel.add(speciesNamesPanel);
	 this.imgIdentityPanel.add(scrollPanel);
	
}
//this method is to display filtered list of species by the pose, color and scale
public void displaySpeciesListByFilter(Species getSpecieList){
	speciesNamesPanel.clear();
	VerticalPanel speciesPanel = new VerticalPanel();
	//Species currentSpecies = new Species();
	List<Species> speciesList = getSpecieList.getFilterdSpeciesList();
	if(speciesList.size()==0){
		speciesLabel = new Label("No records for this filter..");
		speciesNamesPanel.add(this.speciesLabel);
	}
	for(final Species aSpecies: speciesList){
				speciesLabel = new Label(aSpecies.getSpeciesName());
				speciesLabel.setStyleName("speciesNames");
				Label line = new Label("------------------------------------------------------");
				//add scrollpanel to the main panel
				speciesLabel.addClickHandler(new ClickHandler() {
			         @Override
			         public void onClick(ClickEvent event) {
			        	 imgIdentityPanel.setVisible(false);
			        	 noAnimalChkBx.setEnabled(false);
			        	 String txt = ((Label) event.getSource()).getText();
							//ServiceImpl.getSpecies(speciesName);  //single species
			        	 String Description = aSpecies.getSpeciesDescription();
			        	 Identification mySpeciesId = new Identification(txt, Description);
			        	 imgPlusFilterPanel.add(mySpeciesId);
			             }
			      });
							
				speciesNamesPanel.add(this.speciesLabel);
				speciesNamesPanel.add(line);
		}
	
	 scrollPanel.add(speciesNamesPanel);
	 this.imgIdentityPanel.add(speciesNamesPanel);
	 
}

//Change handler for the looks like dropdown list
private class looksLikesChangeHandler implements ChangeHandler{
	@Override
	public void onChange(ChangeEvent event) {
		speciesNamesPanel.clear();
		
		int lookIndex = looksLikeSpecies.getSelectedIndex();
		String looksLikeName = looksLikeSpecies.getItemText(lookIndex);
		if(!"Clear".equals(looksLikeName)){
			ServiceImpl.getSpeciesListLkLike(looksLikeName);
		}
		if("Clear".equals(looksLikeName) || "Looks Like".equals(looksLikeName))
		{
			//set selected item to the first item which is looks like
			looksLikeSpecies.setSelectedIndex(0);  
			//Load all species names
			ServiceImpl.getSpeciesList();
		}
				
		}
	}

public void insertIdentification(String test) {
	
	test = "test";
	System.out.println(test);
}
public void displaySpeciesDescription(String speciesDesc){
	speciesDescription.setText(speciesDesc);
 }
//populate the list of study config
public void setStudyColors(Study configs){
	//List<Study> currentStudyConfigs = configs.getListOfStudyConfig();
	colours = configs.getListOfStudyConfig();
}
public void setStudyBehaviors(Study configs){
	behaviors= configs.getListOfStudyConfig();
}
public void setStudyPoses(Study configs){
	poses = configs.getListOfStudyConfig();
}
public void setStudyScales(Study configs){
	scales = configs.getListOfStudyConfig();
}
public void setActiveStudyId(String currentActiveStudy){
	activeStudyId = currentActiveStudy;
}
//Inner private class for handling identification form
private class Identification extends Composite {
	
	//Define panels to hold controls
	private VerticalPanel mainIdentificationPanel = new VerticalPanel();
	 private VerticalPanel identificationFormPanel = new VerticalPanel();			//Main panel of the form
	 private HorizontalPanel howManySpeciesPanel = new HorizontalPanel(); 			 //for how many radio buttons
	 private HorizontalPanel behaviorsPanel = new HorizontalPanel(); 	
	 private HorizontalPanel coloursPanel = new HorizontalPanel(); 			 		//for how colours radio buttons
	 private HorizontalPanel posesPanel = new HorizontalPanel(); 
	 private HorizontalPanel scalesPanel = new HorizontalPanel(); 	
	 private HorizontalPanel  identifyButtonsPanel = new HorizontalPanel();			//for actions buttons
	 private HorizontalPanel  youngPresentPanel = new HorizontalPanel();				//for Behavior checkboxes
	 private ScrollPanel mainScroll = new ScrollPanel();
	 //Define buttons for handling form
	 private Button cancelBtn;								//cancel button to cancel the form
	  private Button identifyBtn;							//identify button to process the form
 	  //species description
	 // private HTML speciesDescription = new HTML();
	  private TextArea speciesUserDescription;
	  private Label speciesDescLabel;
	  private Label description;
	  
	// Create some radio buttons, all in one group 'radioGroup'.
      final RadioButton radioButton1 = new RadioButton("howManyRadio", "1");
      final RadioButton radioButton2 = new RadioButton("howManyRadio", "2");
      final RadioButton radioButton3 = new RadioButton("howManyRadio", "3");
      final RadioButton radioButton4 = new RadioButton("howManyRadio", "4");
      final RadioButton radioButton5 = new RadioButton("howManyRadio", "5");
      final RadioButton radioButton6_10 = new RadioButton("howManyRadio", "6-10");
      final RadioButton radioButton11_50 = new RadioButton("howManyRadio", "11-50");
      final RadioButton radioButton51Plus = new RadioButton("howManyRadio", "51+");
    //Young Present checkbox
	  private CheckBox youngPresent = new CheckBox("Young animal(s) present");
	  private Label youngPresentLbl = new Label("Optional: ");
	
	  //Seletected values
	  String selectedColor ="N/A";
	  String selectedPose = "N/A";
	  String selectedScale = "N/A";
	
	  ArrayList<String> selectedBehaviors = new ArrayList<String>();
	 String species;
	 //Constructing the Identification class
	  public Identification(String ls, String Thedescription){
		  //Labels
		  Label coloursLbl = new Label("Colours: ");
		  Label behaviorLbl = new Label("Behaviors: ");	
		  Label howManyLbl = new Label("How many: ");
		  Label posesLbl = new Label("Poses: ");
		  Label scalesLbl = new Label("Scales: ");
		  
		//Define list of colours and display them to the identification form
		  coloursPanel.add(coloursLbl);
		  HTML noColorSet = new HTML("&nbsp;&nbsp;<font color='blue'>Not Required!</font>");
		  coloursPanel.add(noColorSet);
			for(Study aStudy: colours){
				if(aStudy.getStudyId().equals(activeStudyId)){ 					//Checking for Current Active study to get proper configs
					noColorSet.setText("");
				RadioButton theColor = new RadioButton("colour", aStudy.getColour());
				coloursPanel.add(theColor);
				theColor.addValueChangeHandler(new ValueChangeHandler<Boolean>(){

					@Override
					public void onValueChange(ValueChangeEvent<Boolean> event) {
						if(event.getValue()==true){
							selectedColor = ((RadioButton) event.getSource()).getText();
						}
					}
					
				});
				}
			}
			//Define list of behaviors and display them to the identification form
			behaviorsPanel.add(behaviorLbl);
			HTML noBehaviorSet = new HTML("&nbsp;&nbsp;<font color='blue'>Not Required!</font>");
			behaviorsPanel.add(noBehaviorSet);
			for(Study aStudy: behaviors){
				
				if(aStudy.getStudyId().equals(activeStudyId)){				//Checking for Current Active study to get proper configs
					noBehaviorSet.setText("");
				CheckBox theBehavior = new CheckBox(aStudy.getBehavior());
				behaviorsPanel.add(theBehavior);
				theBehavior.addClickHandler(new ClickHandler(){
					int i = 1;
					@Override
					public void onClick(ClickEvent event) {
						boolean checked =((CheckBox) event.getSource()).isChecked();
						if(checked==true){
						selectedBehaviors.add(((CheckBox) event.getSource()).getText());
						System.out.println(selectedBehaviors.get(i));
						 i++;
						}else{
							selectedBehaviors.remove(i);
						}
					}
				});
			}
		}
		  //Define list of poses and display them to the identification form
			posesPanel.add(posesLbl);
			 HTML noPoseSet = new HTML("&nbsp;&nbsp;<font color='blue'>Not Required!</font>");
			 posesPanel.add(noPoseSet);
			for(Study aStudy: poses){
				if(aStudy.getStudyId().equals(activeStudyId)){						//Checking for Current Active study to get proper configs
					noPoseSet.setText("");
				RadioButton thePose = new RadioButton("pose", aStudy.getPose());
				posesPanel.add(thePose);
				thePose.addValueChangeHandler(new ValueChangeHandler<Boolean>(){

					@Override
					public void onValueChange(ValueChangeEvent<Boolean> event) {
						if(event.getValue()==true){
							selectedPose = ((RadioButton) event.getSource()).getText();
						}
					}
					
				});
				}
			}
			 //Define list of poses and display them to the identification form
			scalesPanel.add(scalesLbl);
			 HTML noScaleSet = new HTML("&nbsp;&nbsp;<font color='blue'>Not Required!</font>");
			 scalesPanel.add(noScaleSet);
			for(Study aStudy: scales){
				if(aStudy.getStudyId().equals(activeStudyId)){						//Checking for Current Active study to get proper configs
					noScaleSet.setText("");
				RadioButton theScale = new RadioButton("scale", aStudy.getScale());
				scalesPanel.add(theScale);
				theScale.addValueChangeHandler(new ValueChangeHandler<Boolean>(){
					@Override
					public void onValueChange(ValueChangeEvent<Boolean> event) {
						if(event.getValue()==true){
							selectedScale = ((RadioButton) event.getSource()).getText();
						}
					}
					
				});
				}
			}

		  //set widget styling
    	  initWidget(this.mainIdentificationPanel);
    	  identificationFormPanel.setStyleName("toolbar");
    	  identificationFormPanel.setSpacing(10);
    	  mainScroll.setStyleName("identificationFormScroll");
    	  identifyButtonsPanel.setSpacing(10);
    	  
    	  //identification image
    	  Image speciesImg = new Image("applicationImages/image0.JPG");
    	  speciesImg.setSize("400px", "200px");
    	  
    	  //Identification form Controls
    	  this.cancelBtn = new Button("Cancel");
    	  this.cancelBtn.setStyleName("Enabledgwt-Button");
    	  this.identifyBtn = new Button("Identify");
    	  this.identifyBtn.setStyleName("Enabledgwt-Button");
    	     	 
    
    	//Add the form controls to the identification panel
    	  this.identificationFormPanel.add(speciesImg);             //Adding species image
    	
    	//ADD species label as per selection from the species list (Main identification page)
    	  final Label newLbl = new Label(ls);
    	  newLbl.setStyleName("TitleLabelStyle");
    	  
    	  identificationFormPanel.add(newLbl);
 
          this.identificationFormPanel.add(speciesDescription);
        //get selected species description using RPC
        //add Description
     	 this.description = new Label(Thedescription);
     	  ScrollPanel holdDscPanel = new ScrollPanel();holdDscPanel.setStyleName("speciesDecription");
     	  holdDscPanel.add(description);identificationFormPanel.add(holdDscPanel);
     	  
          //Add species description / comments field for user 
          this.speciesUserDescription = new TextArea();
          this.speciesUserDescription.setSize("400px", "50px");
          this.speciesDescLabel = new Label("Add Comments/Notes");
          this.speciesDescLabel.setStyleName("TitleLabelStyle");
          this.identificationFormPanel.add(speciesDescLabel);
          this.identificationFormPanel.add(speciesUserDescription);
       
          //set the first radio button as selected by default
          radioButton1.setValue(true);
           //Add radio buttons label to the radio panel 
    	  howManySpeciesPanel.add(howManyLbl); howManySpeciesPanel.add(radioButton1); howManySpeciesPanel.add(radioButton2);
    	  howManySpeciesPanel.add(radioButton3);howManySpeciesPanel.add(radioButton4); howManySpeciesPanel.add(radioButton5);
    	  howManySpeciesPanel.add(radioButton6_10); howManySpeciesPanel.add(radioButton11_50);howManySpeciesPanel.add(radioButton51Plus);
    	  
    	  //Add young present checkbox to the form panel
    	  this.youngPresentPanel.add(youngPresentLbl);
    	  this.youngPresentPanel.add(youngPresent);
    	 
    	   //Add action buttons to the buttons panel
    	  this.identifyButtonsPanel.add(cancelBtn);				//Adding Cancel Button
    	  this.identifyButtonsPanel.add(identifyBtn);				//Adding Cancel Button
   	   	 
          //Add the how many panel, behaviorPanel and action buttons panel to the form panel
           this.identificationFormPanel.add(howManySpeciesPanel);
           this.identificationFormPanel.add(coloursPanel);
           this.identificationFormPanel.add(behaviorsPanel);
           this.identificationFormPanel.add(posesPanel);
           this.identificationFormPanel.add(scalesPanel);
           this.identificationFormPanel.add(youngPresentPanel);
           this.identificationFormPanel.add(identifyButtonsPanel);
           this.mainScroll.add(identificationFormPanel);
           this.mainIdentificationPanel.add(mainScroll);
            //Event for cancel button 
           this.cancelBtn.addClickHandler(new ClickHandler(){
    		 
			@Override
			public void onClick(ClickEvent event) {
				 noAnimalChkBx.setEnabled(true);
				 mainIdentificationPanel.setVisible(false);
				imgIdentityPanel.setVisible(true);
			}
    		  
    	  });
           
          //Event for Identify button
           this.identifyBtn.addClickHandler(new ClickHandler(){
        	@Override
			public void onClick(ClickEvent event) {
				//perform a validation check
				if("".equals(speciesUserDescription.getText())){
					Window.alert("Please enter a short description of the image");
									
				}else{ 
					//if all validation and no issues then perform the below
						//Define record fields
						
						String howMany ="";
						if(radioButton1.getValue()==true) howMany = radioButton1.getText();
						if(radioButton2.getValue()==true) howMany = radioButton2.getText();
						if(radioButton3.getValue()==true) howMany = radioButton3.getText();
						if(radioButton4.getValue()==true) howMany = radioButton4.getText();
						if(radioButton5.getValue()==true) howMany = radioButton5.getText();
						if(radioButton6_10.getValue()==true) howMany = radioButton6_10.getText();
						if(radioButton11_50.getValue()==true) howMany = radioButton11_50.getText();
						if(radioButton51Plus.getValue()==true) howMany = radioButton51Plus.getText();
						
						String youngPrent ="";
						if(youngPresent.getValue()==true){
							 youngPrent = "y";
						}
						else{
							youngPrent = "n";
						}
					
						String userCommment = speciesUserDescription.getText();
						
						//extracting image ID
						String imageId =" ";
						if(index >0){
						String currentImageID = imageURLs.get(index);
					 	currentImageID = currentImageID.substring(currentImageID.indexOf("/") + 1);
					 	//currentImageID = currentImageID.substring(0, currentImageID.indexOf("."));
					 	 imageId = currentImageID;
						}else{
							imageId = "image0";
						}
					 	species =  newLbl.getText();         //get the species selected/identified
					 	//Get current username
						 String username = Cookies.getCookie("username");
						 
						 //behaviors - remove duplicates from the list
						 Set<String> hs = new HashSet<>();
						 hs.addAll(selectedBehaviors);
						 selectedBehaviors.clear();
						 selectedBehaviors.addAll(hs);
						 String behaviorSelection = "";
						 for(int i=0; i<selectedBehaviors.size(); i++){
							 behaviorSelection = selectedBehaviors.get(i) +"|"+behaviorSelection;
						
							}
						 
						//Make an RPC Call
					 	IdentifyPage.this.ServiceImpl.InsertIdentification(username, behaviorSelection, howMany, youngPrent, userCommment, imageId, species, selectedColor, selectedPose, selectedScale);
					 	
					 	//re-set and organize the identification panel
					 	mainIdentificationPanel.setVisible(false);  // Hide the identification form panel
						noAnimalChkBx.setEnabled(false);			//Disable No Animal Present checkbox
						finishBtn.setEnabled(true);                 //Enable the Finish Button to allow user to go to next image
						finishBtn.setStyleName("Enabledgwt-Button");
						imgIdentityPanel.setVisible(true);			// show the main species list panel
						ServiceImpl.getSpeciesList();
					 Window.alert(imageId +": identified as "+species + " recorded successfully");
				}
			}
           	});
           
           }
	 
	}

}
