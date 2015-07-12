package jirtbioss.core.client.authentication;

import java.sql.Date;

import jirtbioss.core.client.JIRTBIOSS_v0;
import jirtbioss.core.client.service.LoginServiceClientImpl;

import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


public class Logout extends Composite{
	private Button logoutBtn;
	private VerticalPanel mainpanel = new VerticalPanel();
	private Label userNameLabel;
	private HorizontalPanel infoPanel = new HorizontalPanel();
	//Authentication variables
	private LoginServiceClientImpl service;
	HTML homePage = new HTML("<a href='JIRTBIOSS_v0.html?gwt.codesvr=127.0.0.1:9997'>Go to home </a>");
	public Logout(LoginServiceClientImpl service){
		initWidget(this.mainpanel);
		this.service = service;
		this.logoutBtn = new Button("Logout");
		this.userNameLabel = new Label(Cookies.getCookie("username"));
		this.infoPanel.add(logoutBtn);
		this.infoPanel.add(userNameLabel);
		this.infoPanel.add(homePage);
		this.mainpanel.add(infoPanel);
		this.logoutBtn.addClickHandler(new LogoutbtnClickHandler());
		
	}
	
	public void logoutServer(String userID){
		//check user logged session
	
		//Cookies.removeCookie("username");
	}
	private class LogoutbtnClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			service.logout();
			mainpanel.clear();
			
			//Set cookies to current time and therefore user neeed to relogin to access the system
			Date expires = new Date (System.currentTimeMillis());
			Cookies.setCookie("username", Cookies.getCookie("username"), expires, null, "/", true);
		
		}
	}

}
