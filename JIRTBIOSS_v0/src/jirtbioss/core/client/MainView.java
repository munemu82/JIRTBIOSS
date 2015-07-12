/*
 * MainView.java 
 * A class that control all the contents in the application
 */
package jirtbioss.core.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainView extends Composite {

	//Creating main placeholder, the main frame of the application
	private VerticalPanel mainViewPanel = new VerticalPanel();
	private VerticalPanel contentPanel;
	
	//Constructor for the MainView class
	public MainView(){
		//initializing the main application frame 
		initWidget(this.mainViewPanel);
				
		//setting up border and width of the mainview
		this.mainViewPanel.setBorderWidth(1);
		this.mainViewPanel.setWidth("100%");
		//this.mainViewPanel.setHeight("800px");
		
		//Creating and adding MenuView to the MainView Panel
		MenuView menuPanel = new MenuView();
		this.mainViewPanel.add(menuPanel);
		
				
	}

	
}
