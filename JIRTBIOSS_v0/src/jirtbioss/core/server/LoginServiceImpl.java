package jirtbioss.core.server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 import jirtbioss.core.client.authentication.AESEncryption;
import jirtbioss.core.client.model.Users;
import jirtbioss.core.client.service.LoginService;
import jirtbioss.core.shared.DBUtility;
import jirtbioss.core.shared.FieldVerifier;
import jirtbioss.core.shared.JirtbiossLogger;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mysql.jdbc.PreparedStatement;
 
/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
	private ArrayList<String> currentLoggedUsers = new ArrayList<String>();
	private String loggedInUser;
	private Connection connection = DBUtility.getConnection();
	//Logging variables
	private String loggingLevelProperty = DBUtility.getLoggingLevel();
	private JirtbiossLogger jirbiossAuthLogging = new JirtbiossLogger("JIRTBIOSS_AUTH", loggingLevelProperty);

	 
 
	public String login(String name, String password) throws IllegalArgumentException {
		String result = "";

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		name = escapeHtml(name);
		password = escapeHtml(password);
		// Verify that the input is valid.
		if (!FieldVerifier.isValidName(name)) {
			result = "Name must be at least 4 characters long";
		/*}else if (!"amos".equals(name) || !"pass".equals(password)) {
			result = "username and password does not match our databae";*/
 		}else{
 			
 			//prepare query
 			try{
 				//encrypt password with AES Encryption
 				final String strToEncrypt = password;
 		          final String strPssword = "encryptor key";
 		          AESEncryption.setKey(strPssword);
 		          AESEncryption.encrypt(strToEncrypt.trim());
 				
 				Statement st = connection.createStatement();
 			      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * FROM members where status='A' AND uname=? AND pass=?");
 			      query.setString(1, name);
 			      query.setString(2, AESEncryption.getEncryptedString());
 			  // execute the query, and get a java resultset
 			      ResultSet rs = query.executeQuery();
 			     if(rs.last()==true){
 			    	// create session and store userid
  	 	 			setUserName(name);
  	 	 			result = getUserName();
  	 	 			//add user to the current logged in users
  	 	 			this.loggedInUser = name;
  	 	 			this.currentLoggedUsers.add(this.loggedInUser);
  	 	 		//BELOW is Logging the user who logged in the application successfully
  	 	 		/*File file = new File("./Logs/JirtBiossLogin.log");
  	 	 		String dirPath = file.getAbsoluteFile().getParentFile().getAbsolutePath();
  	 	 		//dirPath+"/JirtBiossLogin.log"
  	 	 		FileHandler logFile = new FileHandler("Logs/JirtBiossLogin.log", 1024 * 1024, 1, true);
  	 	 	    SimpleFormatter formatter = new SimpleFormatter();
  	 	 	    logFile.setFormatter(formatter);
  	 	 	    Logger LOGGER = Logger.getLogger(LoginServiceImpl.class.getName());
  	 	 	    LOGGER.addHandler(logFile);
  	 	 	    LOGGER.setLevel(Level.INFO);
  	 	 	    LOGGER.info(result + " logged in successfully");
  	 	 	    logFile.close();*/
  	 	 		this.jirbiossAuthLogging.performLoggig("LoginServiceImpl", "login", result + " logged in successfully");
  	 	 	    //END OF LOGGING
 			     }else{
 			    	result = "username and password does not match our databae";
 			    	//result ="";
 			     }
 			    st.close();
 			} catch (Exception e)
		    {
			      System.err.println("Got an exception! ");
			      System.err.println(e.getMessage());
			      this.jirbiossAuthLogging.performLoggig("LoginServiceImpl", "login", result + " authentication failed! "+e.getMessage());
		    }
 		}
		return result;
		}
 
	@Override
	public boolean checkLogin() {
 
		HttpServletRequest request = this.getThreadLocalRequest();
		// dont create a new one -> false
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userName") == null)
			return false;
		// session and userid is available, looks like user is logged in.
		return true;
	}
 
	@Override
	public String logout() {
 
		HttpServletRequest request = this.getThreadLocalRequest();
		// dont create a new one -> false
		HttpSession session = request.getSession(false);
		
		if (session == null) 
			return getUserName();
		// do some logout stuff ...
		//HttpSession httpSession = getThreadLocalRequest().getSession(false);
		//session.setMaxInactiveInterval(0);
		//session.setAttribute("userName", null);
		session.invalidate();
		//setUserName("");
		this.jirbiossAuthLogging.performLoggig("LoginServiceImpl", "logout", getUserName() + " Logout from server");
		return getUserName();
	}
 
 
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	@Override
	public boolean changePassword(String name, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setUserName(String userName) {
		HttpSession httpSession = getThreadLocalRequest().getSession(true);
		httpSession.setMaxInactiveInterval(1 * 60 *60);
	     httpSession.setAttribute("userName", userName);
	     
	}
	@Override
	public String getUserName() {
		   HttpSession session = getThreadLocalRequest().getSession(true);
		    if (session.getAttribute("userName") != null)
		    {
		    	 	return (String) session.getAttribute("userName");
		    }
		    else 
		    {
		        return "";
		    }
	}


	@Override
	public Users getAllUsers() {
		Users appUsers = new Users();
		//Setup array list of speciesnames
				List<Users> usersList = new ArrayList<Users>();
				 try
				    {
				    
				      String query = "SELECT * FROM members";
				 
				      // create the java statement
				      Statement st = connection.createStatement();
				       
				      // execute the query, and get a java resultset
				      ResultSet rs = st.executeQuery(query);
				       
				      // iterate through the java resultset
				      while (rs.next())
				      {
				    	  Users aUser = new Users();
				    	  aUser.setUsername(rs.getString("uname"));
				    	  aUser.setAuthLevel(rs.getInt("securityLevel"));
				    	  usersList.add(aUser);
				      }
				      st.close();
				    }
				    catch (Exception e)
				    {
				      System.err.println("Got an exception! ");
				      System.err.println(e.getMessage());
				    }

				 appUsers.setAllAppUsers(usersList);
		return appUsers;
	}
	
}