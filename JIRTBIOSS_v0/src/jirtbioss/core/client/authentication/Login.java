package jirtbioss.core.client.authentication;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jirtbioss.core.client.model.Users;
import jirtbioss.core.client.service.LoginServiceClientImpl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Login extends Composite{
	private VerticalPanel LoginFormMainPanel = new VerticalPanel();
	private TextBox userNameTxtBox;
	private Label userNameLabel;
	private PasswordTextBox userPasswordTxtBox;
	private Label userPasswordLabel;
	private Button loginBtn;
	private Label resultLabel;
	private HorizontalPanel loginFormPanel1 = new HorizontalPanel();
	private HorizontalPanel loginFormPanel2 = new HorizontalPanel();
	private HorizontalPanel loginFormPanel3 = new HorizontalPanel();
	
	//Authentication variables
	private LoginServiceClientImpl service;
	
	public Login(LoginServiceClientImpl service){
		//initializing the widgets
		initWidget(this.LoginFormMainPanel);
		this.service = service;
		
		this.LoginFormMainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		
		//Set styles and spacing for panels
		this.loginFormPanel1.setSpacing(15);
		this.loginFormPanel2.setSpacing(15);
		this.loginFormPanel3.setStyleName("loginPanel");
		
		this.LoginFormMainPanel.setSize("1000px", "180px");
	
		//Username
		this.userNameTxtBox = new TextBox();
		this.userNameTxtBox.setStyleName("input");
		this.userNameLabel = new Label("Username: ");
		
		//Password
		this.userPasswordTxtBox = new PasswordTextBox();
		this.userPasswordTxtBox.setStyleName("input");
		this.userPasswordLabel = new Label("Password: ");
		
		//Login button
		this.loginBtn = new Button("Login");
		this.loginBtn.setStyleName("Enabledgwt-Button");
		this.loginBtn.addClickHandler(new LoginBtnClickHandler());
		
		
		//Add username label and textbox to the login panel
		this.loginFormPanel1.add(userNameLabel);
		this.loginFormPanel1.add(userNameTxtBox);
		
		//Add password label and textbox to the login panel
		this.loginFormPanel2.add(userPasswordLabel);
		this.loginFormPanel2.add(userPasswordTxtBox);
	
		//add login button to  the panel
		this.loginFormPanel3.add(loginBtn);
		
		//add all the form panels to the main panel
		this.LoginFormMainPanel.add(loginFormPanel1);
		this.LoginFormMainPanel.add(loginFormPanel2);
		this.LoginFormMainPanel.add(loginFormPanel3);
		
		//initialize result label
		this.resultLabel = new Label();
		this.resultLabel.setStyleName("error_validation");
		this.LoginFormMainPanel.add(resultLabel);
		
	}
	//Event handler for loginBtn 
	private class LoginBtnClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			//first perform client validation
			if("".equals(userNameTxtBox.getText()) || "".equals(userPasswordTxtBox.getText())){
				//Window.alert("Username and/or password cannot be empty");
				resultLabel.setText("Username and/or password cannot be empty");
			}
			else{
				//if all validation pass successfully then perform RPC call
				resultLabel.setText("");  //clear result label
				service.login(userNameTxtBox.getText(), userPasswordTxtBox.getText());
				//resultLabel.setText("Logged In!");
			}
		
	}
	}
	//get login
	
	//What to do when validation success
	public void loginToserver(String userID){
		System.out.println(userNameTxtBox.getText());
		
		
		resultLabel.setText(userID);
		System.out.println(resultLabel.getText());
	     if("".equals(resultLabel.getText())){
			//resultLabel.setText("username and password does not match our databae");
	    	 Window.alert("You are logging out......");
		}/*else if("Name must be at least 4 characters long".equals(resultLabel.getText())){
			resultLabel.setText("Name must be at least 4 characters long");*/
	     else if(userNameTxtBox.getText().equals(resultLabel.getText())){
			String sessionID = resultLabel.getText();
			//String securityID = "1";
			final long DURATION = 1000* 60 * 60;
			Date expires = new Date (System.currentTimeMillis() + DURATION);
			Cookies.setCookie("username", sessionID, expires, null, "/", false);
			LoginFormMainPanel.clear();
			HTML homePage = new HTML("<a href='JIRTBIOSS_v0.html?gwt.codesvr=127.0.0.1:9997'>Go to home </a>");
			LoginFormMainPanel.add(homePage);
			//get list of all users
			service.getAllUsers();
		}
	     //System.out.println(resultLabel.getText());
	}
	public String currentUserCheck(Users theUser){
		ArrayList<String> users = theUser.getUsernames();
		if(users.contains(resultLabel.getText())){
			return resultLabel.getText();
		}
		return null;
		
	}

	public boolean getLoggedIn(String currentUser){
		if(currentUser == this.resultLabel.getText())
			 return true;
		else 
			return false;
	}
	public void setSetupSessions(Users listOfusers){
		final List<Users> listOfUsers = listOfusers.getAllAppUsers();
		final long DURATION = 1000* 60 * 60;
		Date expires = new Date (System.currentTimeMillis() + DURATION);
		for(Users aUser: listOfUsers){
			String sessionID = Cookies.getCookie("username");
			if(aUser.getUsername().equals(sessionID))
			{
				if(aUser.getAuthLevel()==2){
					String auth ="2";
					Cookies.setCookie("authLevel",auth, expires, null, "/", false);
				}else if((aUser.getAuthLevel() ==1)){
					String auth ="1";
					Cookies.setCookie("authLevel",auth, expires, null, "/", false);
				}else{
					String auth ="0";
					Cookies.setCookie("authLevel",auth, expires, null, "/", false);
				}
			}
	}
	}
	
}
