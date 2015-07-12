package jirtbioss.core.shared;

import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * A class for the user database table
 */
public class User implements IsSerializable {
    private String id = "";
    private String username = "";
    private String password = "";
    
    private User() {
        //just here because GWT wants it.
    }
    
    /**
     * A user of the system
     * @param id
     * @param username
     * @param password
     */
    public User(String id, String username, String password) {
        this.setId(id);
        this.setUserName(username);
        this.setPassword(password);
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param username the username to set
     */
    public void setUserName(String username) {
        this.username = username;
    }

    /**
     * @return the username
     */
    public String getUserName() {
        return username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }    
}