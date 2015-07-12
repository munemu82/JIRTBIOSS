package jirtbioss.core.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Documentation  extends Composite{

	//define fields
		private HTML pageTitle = new HTML("<h1> JIRTBIOSS DOCUMENTATION </h1> <hr />");
	//	private Label pageTitle = new Label("JIRTBIOSS DOCUMENTATION");
		private VerticalPanel mainPanel = new VerticalPanel();
		private VerticalPanel contentPanel = new VerticalPanel();
		private DockPanel layoutPanel = new DockPanel();	
		private HorizontalPanel topButtonsPanel = new HorizontalPanel();
		private Button quickStatBtn = new Button("Quick Start");
		private Button userGuideBtn = new Button("User Guide");
		private Button techGuideBtn = new Button("Technical Guide");
		private Button techReferenceBtn = new Button("Technical References");
		
		//Constructor
		public Documentation(){
			initWidget(this.mainPanel);
			
			//Stying and formating
			layoutPanel.setSize("100%", "");
			this.topButtonsPanel.setSpacing(20);
			
			//Add widgets to the panels
			this.topButtonsPanel.add(quickStatBtn);
			this.topButtonsPanel.add(userGuideBtn);
			this.topButtonsPanel.add(techGuideBtn);
			
			//Add the panels to the main panel
			this.layoutPanel.add(pageTitle, layoutPanel.NORTH);
			this.layoutPanel.add(topButtonsPanel, layoutPanel.WEST);
			this.layoutPanel.add(contentPanel, layoutPanel.CENTER);
			this.mainPanel.add(layoutPanel);
			
			//Add/Register click event handlers
			this.quickStatBtn.addClickHandler(new quickStartBtnClickHandler());
			
		}
		
		//BUTTONS EVENT HANDLER
		private class quickStartBtnClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				//first clear content Panel
				contentPanel.clear();
				 Frame frame = new Frame("documentation/QuickStart.pdf");
				 frame.setSize("900px", "500px");
				 contentPanel.add(frame);
			}
			
		}
}
