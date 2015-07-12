package jirtbioss.core.client;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ForumPage extends Composite {
	//define fields
	private Label forumPageTitle = new Label("This is a forum page");
	private Label test = new Label();
	private VerticalPanel forumPanel = new VerticalPanel();
	
	//Constructor
	public ForumPage(){
		initWidget(this.forumPanel);
	
			this.forumPanel.add(forumPageTitle);
		
		
			
	}
}
