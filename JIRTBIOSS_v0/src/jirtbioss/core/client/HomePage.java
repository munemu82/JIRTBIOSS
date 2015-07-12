package jirtbioss.core.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HomePage extends Composite {
	//define fields
	//private Label homePageTitle = new Label("This is a home page");
	private VerticalPanel homePanel = new VerticalPanel();
	
	//Constructor
	public HomePage(){
		initWidget(this.homePanel);
		//Define HTML content
		HTML homePage = new HTML("<h1>Welcome to JIRTBIOSS</h1>"+
		"<table><tr><td><img src='applicationImages/species.jpg'/></td><td><font size='4em'>Like Snapshot Serengeti we need a very easy to use interface for users to interact with camera trap photos and to rapidly browse provide identification information for the hundreds of thousands images that a typical deployment collects."+
				"<p>This software will play a critical role in the biosecurity surveillance program, allowing a database of identifications to be collected to train machine learning algorithms in automated identification, to manage identifications of species from images across a team of observers and also to manage records of who has viewed and downloaded which images. </p>"
		+ "</td></tr></table>");
		
		this.homePanel.add(homePage);
	}
	
}
