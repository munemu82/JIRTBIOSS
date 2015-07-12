package jirtbioss.core.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Users implements Serializable {
private String username;
private String password;
private int userId;
private String firstname;
private String status;
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
private String lastname;
private String email;
private String dateregister;
private List<Users> allAppUsers;
public List<Users> getAllAppUsers() {
	return allAppUsers;
}
public void setAllAppUsers(List<Users> allAppUsers) {
	this.allAppUsers = allAppUsers;
}
private int authLevel;

public int getAuthLevel() {
	return authLevel;
}
public void setAuthLevel(int authLevel) {
	this.authLevel = authLevel;
}
public String getDateregister() {
	return dateregister;
}
public void setDateregister(String dateregister) {
	this.dateregister = dateregister;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
private String session;
private Boolean loggedIn;
private String sessionId;
private ArrayList<String> usernames;
private List<Users> usersList;

public ArrayList<String> getUsernames() {
	return usernames;
}
public List<Users> getUsersList() {
	return usersList;
}
public void setUsersList(List<Users> usersList) {
	this.usersList = usersList;
}
public void setUsernames(ArrayList<String> usernames) {
	this.usernames = usernames;
}
public String getSessionId() {
	return sessionId;
}
public void setSessionId(String sessionId) {
	this.sessionId = sessionId;
}
public Boolean getLoggedIn() {
	return loggedIn;
}
public void setLoggedIn(Boolean loggedIn) {
	this.loggedIn = loggedIn;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getSession() {
	return session;
}
public void setSession(String session) {
	this.session = session;
}

}
