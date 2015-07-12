package jirtbioss.core.client;

import jirtbioss.core.client.service.SpeciesReportServiceClientImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;



public class Reports extends Composite {
	//Fields
	private Label profileTitle = new Label();
	private VerticalPanel ProfileContent = new VerticalPanel();
	
	public Reports(){
		//initializing the widgets
		initWidget(this.ProfileContent);
		
		String authLevel = Cookies.getCookie("authLevel");
		System.out.println(authLevel);
		if("0".equals(authLevel))
		{
			profileTitle.setStyleName("error");
			profileTitle.setText("You are not authorized to view this content");
			this.ProfileContent.add(profileTitle);
		}else{ 
			this.ProfileContent.add(profileTitle);
			
			SpeciesReportServiceClientImpl table = new SpeciesReportServiceClientImpl(GWT.getModuleBaseURL() + "speciesreportservice");
			
			this.ProfileContent.add(table.getSpeciesCellTableUI());
		}
		
	
}
}