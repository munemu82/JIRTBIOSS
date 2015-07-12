package jirtbioss.core.client;

import java.sql.Date;


import jirtbioss.core.client.service.LoginServiceClientImpl;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class JIRTBIOSS_v0 implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * This is the entry point method.
	 */
	private Button logoutBtn;
	private VerticalPanel mainpanel = new VerticalPanel();
	private Label userNameLabel;
	private Label securityLabel;
	private HorizontalPanel infoPanel = new HorizontalPanel();
	LoginServiceClientImpl loginClientImpl = new LoginServiceClientImpl(GWT.getModuleBaseURL() + "LoginService");
	
	
	public void onModuleLoad() {
		//First setup log4/logging 

		
				//LOGIN form
				HTML line = new HTML("<hr />");
		//check user logged session
		String sessionID = Cookies.getCookie("username");
		if(sessionID !=null){
			RootPanel.get().clear();
			this.logoutBtn = new Button("Logout");
			this.logoutBtn.setStyleName("Enabledgwt-Button");
			this.infoPanel.setSpacing(5);
			this.userNameLabel = new Label(Cookies.getCookie("username"));
			this.infoPanel.add(logoutBtn);
			this.infoPanel.add(userNameLabel);
			this.mainpanel.add(infoPanel);
			this.logoutBtn.addClickHandler(new LogoutbtnClickHandler());
			RootPanel.get().add(mainpanel);
			
			//RootPanel.get().add(loginClientImpl.getLogoutGUI());
			
			MainView jirtbiossMain = new MainView();
			RootPanel.get().add(jirtbiossMain);
		}else{
			RootPanel.get().add(line);
			
			RootPanel.get().add(loginClientImpl.getLoginGUI());
			
		}
		
		
		//Creating the main view object
		//MainView jirtbiossMain = new MainView();
	
		//add the Main view object/instance jirtbiossMain to the application's root
		//RootPanel.get().add(jirtbiossMain);
		
		
	}
	private class LogoutbtnClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			loginClientImpl.logout();
			//mainpanel.clear();
			RootPanel.get().clear();
			
			//Set cookies to current time and therefore user neeed to relogin to access the system
			Date expires = new Date (System.currentTimeMillis());
			Cookies.setCookie("username", Cookies.getCookie("username"), expires, null, "/", true);
			Cookies.setCookie("authLevel", Cookies.getCookie("authLevel"), expires, null, "/", true);
			RootPanel.get().add(loginClientImpl.getLoginGUI());
		}
	}
}
