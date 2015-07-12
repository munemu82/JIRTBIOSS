package jirtbioss.core.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Messages extends Composite {

	//the message
	private String message;
	//Popup panal for success messages
	final DecoratedPopupPanel successMessagePopup = new DecoratedPopupPanel();
	//Popup panal for success messages
	final DecoratedPopupPanel ErrorMessagePopup = new DecoratedPopupPanel();
	private VerticalPanel messagPanel = new VerticalPanel();
	
	//constructor
	public Messages(HTML themessage, int messageType){
		//messageType 0= error message, 1=success message
		if(messageType==0){
			initWidget(this.ErrorMessagePopup);
			themessage.setStyleName("error");			// set error style to the message
			messagPanel.add(themessage);               //add message to the panel
			ErrorMessagePopup.setWidget(messagPanel);
			ErrorMessagePopup.show();
		        // Now here i want to wait for like 5 secs and then
		        Timer timer = new Timer()
		        {
		            @Override
		            public void run()
		            {
		            	ErrorMessagePopup.hide();
		            }
		        };

		        timer.schedule(5000);
		}else{
			initWidget(this.successMessagePopup);
			themessage.setStyleName("success");			// set error style to the message
			messagPanel.add(themessage);               //add message to the panel
			successMessagePopup.setWidget(messagPanel);
			successMessagePopup.show();
		        // Now here i want to wait for like 5 secs and then
		        Timer timer = new Timer()
		        {
		            @Override
		            public void run()
		            {
		            	successMessagePopup.hide();
		            }
		        };

		        timer.schedule(5000);
		}
	
	}
}
