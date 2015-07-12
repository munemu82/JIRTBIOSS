package jirtbioss.core.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import jirtbioss.core.client.model.Users;

public interface LoginServiceAsync {
	 void login(String name, String password, AsyncCallback callback);
	 
	    void checkLogin(AsyncCallback callback);
	     
	    void changePassword(String name, String newPassword, AsyncCallback callback);
	 
	    void logout(AsyncCallback callback);
	    void setUserName(String userName, AsyncCallback<Void> callback);
	    void getUserName(AsyncCallback<String> callback);
	    void getAllUsers(AsyncCallback callback);
	}
