/*
 * MenuView.java
 * This class is for defining the top navigation bar that contains navigations
 */
package jirtbioss.core.client;

import jirtbioss.core.client.service.AdminServiceClientImpl;
import jirtbioss.core.client.service.SpeciesListServiceClientImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TabPanel;


public class MenuView extends Composite {
	
	//Fields for the main menu
	private HorizontalPanel topMenuPanel = new  HorizontalPanel();
	private MainView main;
	  //Create an empty tab panel 
    TabPanel tabPanel = new TabPanel();
	//set the width of the top menu panel
	
	
	//Constructor MenuView
	public MenuView(){
		initWidget(this.tabPanel);
		
		//Pages to go in the tabs 
		HomePage homePage = new HomePage();							//for the home tab
		//IdentifyPage identifyPage = new IdentifyPage(); 			//for the Identify Page
		//Calling the client implementation of RPC instead of the GUI, as we know the GUI was called in the client implementation	
		SpeciesListServiceClientImpl clientImpl = new SpeciesListServiceClientImpl(GWT.getModuleBaseURL() + "specieslistservice");
			
		ForumPage forumPage = new ForumPage();						 //for the forum Page
		Documentation userGuidePage = new Documentation();			//for the User Guide Page
		AboutProjectPage aboutProjectPage = new AboutProjectPage(); //for the About project Page
		Reports reportsPage = new Reports();						//For profile page
		//Main page for administration
		AdminServiceClientImpl  administratorPage = new AdminServiceClientImpl(GWT.getModuleBaseURL() + "adminservice");
	     
	      //create titles for tabs
	      String homeTab = "Home";
	      String identifyTab = "Identify";
	      String forumTab = "Forum";
	      String userGuideTab = "Documentations";
	      String aboutProjectTab = "About Project";
	      String reportsTab = "Reports";
	      String administratorTab = "Administration";

	      //create tabs 
	      tabPanel.add(homePage, homeTab);
	      tabPanel.add(clientImpl.getIdentifyUI(), identifyTab);
	      tabPanel.add(forumPage, forumTab);
	      tabPanel.add(reportsPage, reportsTab);
	      tabPanel.add(administratorPage.getAdminGui(), administratorTab);
	      tabPanel.add(userGuidePage, userGuideTab);
	      tabPanel.add(aboutProjectPage, aboutProjectTab);
	     

	      //select first tab
	      tabPanel.selectTab(0);

	      //set width if tabpanel
	      tabPanel.setWidth("100%");
	     

	      // Add the widgets to the root panel.
	    
					
				}
		
}
