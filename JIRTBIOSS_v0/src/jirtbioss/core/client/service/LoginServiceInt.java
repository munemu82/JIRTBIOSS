package jirtbioss.core.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceInt {
	void login(String name, String password);
	 
    void checkLogin();
     
    void changePassword(String name, String newPassword);
 
    void logout();
    void setUserName(String userName);
    void getUserName();
    void getSession();
    void getAllUsers();
    
}
