package jirtbioss.core.client.service;

import jirtbioss.core.client.model.Users;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("LoginService")
public interface LoginService extends RemoteService
{
 
   String login(String name, String password);
 
    boolean checkLogin();
     
    boolean changePassword(String name, String newPassword);
 
    public String logout();
    public void setUserName(String userName);
    public String getUserName();
    public Users getAllUsers();
    
}