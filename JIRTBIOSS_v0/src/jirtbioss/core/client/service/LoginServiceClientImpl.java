package jirtbioss.core.client.service;

import jirtbioss.core.client.authentication.Login;
import jirtbioss.core.client.authentication.Logout;
import jirtbioss.core.client.model.Users;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;


public class LoginServiceClientImpl implements LoginServiceInt{
	//Authentication variables
	private LoginServiceAsync service;
	//GUI
	private Login loginGui;
	private Logout logoutGui;

	public LoginServiceClientImpl(String url){
		//just a debug
		System.out.println(url);
		this.service = GWT.create(LoginService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		//initialize client main GUI
		this.loginGui = new Login(this);	//this here means give me the copy of the MainGUI
		this.logoutGui = new Logout(this);
		
	}
	@Override
	public void login(String name, String password) {
		// TODO Auto-generated method stub
		this.service.login(name, password, new DefaultCallback());
	}
	@Override
	public void checkLogin() {
		this.service.checkLogin(new DefaultCallback());
	}
	@Override
	public void changePassword(String name, String newPassword) {
		this.service.changePassword(name, newPassword, new DefaultCallback());
	}
	@Override
	public void logout() {
		this.service.logout(new DefaultCallback());
	}
	@Override
	public void setUserName(String userName) {
		this.service.setUserName(userName, new DefaultCallback());
		
	}


	@Override
	public void getUserName() {
		this.service.getUserName(new DefaultCallback());
		
	}
	@Override
	public void getSession() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getAllUsers() {
		// TODO Auto-generated method stub
		this.service.getAllUsers(new DefaultCallback());
		
	}
	
//A Login GUI
	public Login getLoginGUI(){
		return this.loginGui;
	}
//A Logout GUI
	public Logout getLogoutGUI(){
		return this.logoutGui;
	}


private class DefaultCallback implements AsyncCallback{

		
		@Override
		public void onFailure(Throwable caught) {
		//this method is triggered when something goes wrong	
			Window.alert("Access Denied. Check your user-name and password.");
		}

		@Override
		public void onSuccess(Object result) {
		//service response without error
			 if(result instanceof String){
				 String info = (String) result;
				 loginGui.loginToserver(info);
				 
			}else if(result instanceof String){
				String isLogged = (String) result;
				loginGui.getLoggedIn(isLogged);
			}else if(result instanceof String){
				String logout = (String) result;
				logoutGui.logoutServer(logout);
			}else if(result instanceof Users){
				Users list = (Users) result;
				loginGui.setSetupSessions(list);
			}
			 
	}
}

}
