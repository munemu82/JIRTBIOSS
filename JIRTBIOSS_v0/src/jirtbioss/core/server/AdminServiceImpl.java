package jirtbioss.core.server;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jirtbioss.core.client.authentication.AESEncryption;
import jirtbioss.core.client.model.ImagesList;
import jirtbioss.core.client.model.Species;
import jirtbioss.core.client.model.SpeciesConfiguration;
import jirtbioss.core.client.model.SpeciesCsvReader;
import jirtbioss.core.client.model.Study;
import jirtbioss.core.client.model.Users;
import jirtbioss.core.client.service.AdminService;
import jirtbioss.core.shared.FilesListComparator;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mysql.jdbc.PreparedStatement;

@SuppressWarnings("serial")
public class AdminServiceImpl extends RemoteServiceServlet implements AdminService{
	//establish connection
	private Connection connection = DBUtility.getConnection();
	//Global variables
	private java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	private String thedatetime = date.toString();
	private String properDate = thedatetime.substring(0, thedatetime.indexOf("."));
	private java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());
	private String theImagesFolderPath = DBUtility.getImageFolderPath();
	//private String liveImagesFolderPath = "/home/amos/eclipse-workspace/JIRTBIOSS_v0/war/imagecaptures/";
	private String liveImagesFolderPath = DBUtility.getLiveImagesFolderPath();
	
	@Override
	public Users getListOfusers() {
		Users userslist = new Users();
		//Setup arraylist to hold users
		List<Users> listOfUsers = new ArrayList<Users>();
		try
	    {
			// create the java statement
	      Statement st = connection.createStatement();
	      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * FROM members");
	     // execute the query, and get a java resultset
	      ResultSet rs = query.executeQuery();
	       
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  Users aUser = new Users();
	    	  aUser.setUserId(rs.getInt("id"));
	    	  aUser.setFirstname(rs.getString("first_name"));
	    	  aUser.setLastname(rs.getString("last_name"));
	    	  aUser.setEmail(rs.getString("email"));
	    	  aUser.setUsername(rs.getString("uname"));
	    	  aUser.setPassword(rs.getString("pass"));
	    	  aUser.setStatus(rs.getString("status"));
	    	  aUser.setAuthLevel(rs.getInt("securityLevel"));
	    	  aUser.setDateregister(rs.getString("regdate"));
	    	  
	    	  listOfUsers.add(aUser);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	 
	
	
		userslist.setUsersList(listOfUsers);
	return userslist;
	}

	@Override
	public String saveUser(String username, String password, String firstname,
			String lastname, String email, int userId, String userAccessLevel, String typeOfSave) {
		String insertSuccess = "Failed !!";
		 // First query to insert the new identification into the database QUERY 1
	    try{
	    	Statement st = connection.createStatement();
	    	 
	    	if(typeOfSave.equals("Insert"))
	    	{
	    		PreparedStatement query = (PreparedStatement) connection.prepareStatement("INSERT INTO members(uname, pass, first_name, last_name, email, securityLevel)VALUES(?,?,?,?,?,?)");
	    //encrypt the password 
	    
	    	
			final String strToEncrypt = password;
          final String strPssword = "encryptor key";
          AESEncryption.setKey(strPssword);
          AESEncryption.encrypt(strToEncrypt.trim());
          
	      query.setString(1, username);
	      query.setString(2, AESEncryption.getEncryptedString());
	      query.setString(3, firstname);
	      query.setString(4, lastname);
	       query.setString(5, email);
	       query.setInt(6, Integer.parseInt(userAccessLevel));
	      // execute the query, and insert the record to the database
	        query.executeUpdate();
	       //success flag 
	        insertSuccess = "User saved successfully";
	    	}
	    	else{
	    		PreparedStatement query = (PreparedStatement) connection.prepareStatement("update members set uname=?, pass=?, first_name=?, last_name=?, email=?, securityLevel=? where id=?");
	    	    //encrypt the password 
	    	    
	    	    	
	    			final String strToEncrypt = password;
	              final String strPssword = "encryptor key";
	              AESEncryption.setKey(strPssword);
	              AESEncryption.encrypt(strToEncrypt.trim());
	              
	    	      query.setString(1, username);
	    	      query.setString(2, AESEncryption.getEncryptedString());
	    	      query.setString(3, firstname);
	    	      query.setString(4, lastname);
	    	      query.setString(5, email);
	    	      query.setInt(6, Integer.parseInt(userAccessLevel));
	    	      query.setInt(7, userId);
	    	      // execute the query, and insert the record to the database
	    	        query.executeUpdate();
	    	        insertSuccess = "User updated successfully";
	    	}
	       st.close();
	     
	    }

	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      //debug 
	    }
	    return insertSuccess;
	}
	//get all records of studies
	@Override
	public Study getListOfstudies() {
		Study studiesList = new Study();
		//Setup arraylist to hold users
		List<Study> listOfStudies = new ArrayList<Study>();
		try
	    {
			// create the java statement
	      Statement st = connection.createStatement();
	      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * FROM study");
	     // execute the query, and get a java resultset
	      ResultSet rs = query.executeQuery();
	       
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  Study aStudy = new Study();
	    	  aStudy.setStudyId(rs.getString("studyId"));
	    	  aStudy.setStudyTitle(rs.getString("studyTitle"));
	    	  aStudy.setStudyDetails(rs.getString("studyDescription"));
	    	  aStudy.setStudyDateStart(rs.getDate("startDate"));
	    	  aStudy.setStudyDateFinish(rs.getDate("finishDate"));
	    	  aStudy.setStatus(rs.getString("status"));
	    	  aStudy.setSpeciesName(rs.getString("species"));
	    	 // aStudy.setBehavior(rs.getString("behavior"));
	    	  listOfStudies.add(aStudy);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		studiesList.setListOfStudies(listOfStudies);
		
		return studiesList;
	}

	@Override
	public String saveStudy(String studyId, String studyTitle, String studyDesc,
			String studyStartDate, String studyEndDate, String studySpecies, String typeOfSave) {
		String insertSuccess = "Failed !!";
		 // First query to insert the new identification into the database QUERY 1
	    try{
	    	Statement st = connection.createStatement();
	    	 
	    	if(typeOfSave.equals("Insert"))
	    	{
	    		PreparedStatement query = (PreparedStatement) connection.prepareStatement("INSERT INTO study(studyId, studyTitle, studyDescription, startDate, finishDate, species)VALUES(?,?,?,?,?,?)");
	     query.setString(1, studyId);
	      query.setString(2, studyTitle);
	      query.setString(3, studyDesc);
	      query.setString(4, studyStartDate);
	      query.setString(5, studyEndDate);
	      query.setString(6, studySpecies);
	      // execute the query, and insert the record to the database
	        query.executeUpdate();
	       //success flag 
	        insertSuccess = "Study saved successfully";
	    	}
	    	else{
	    		PreparedStatement query = (PreparedStatement) connection.prepareStatement("update study set studyId=?, studyTitle=?, studyDescription=?, startDate=?, finishDate=?, species=? where studyId=?");
	    	    //encrypt the password 
	    	  query.setString(1, studyId);
	    	  query.setString(2, studyTitle);
	  	      query.setString(3, studyDesc);
	  	      query.setString(4, studyStartDate);
	  	      query.setString(5, studyEndDate);
	  	      query.setString(6, studySpecies);
	  	    query.setString(7, studyId);
	    	      // execute the query, and insert the record to the database
	    	        query.executeUpdate();
	    	        insertSuccess = "Study updated successfully";
	    	}
	       st.close();
	     
	    }

	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      //debug 
	    }
	    return insertSuccess;
	}

	@Override
	public SpeciesConfiguration getAllConfigurations() {
		SpeciesConfiguration listofAlConfigs = new SpeciesConfiguration();
		//Setup arraylist to hold users
		List<SpeciesConfiguration> currentConfigs = new ArrayList<SpeciesConfiguration>();
		try
	    {
			// create the java statement
	      Statement st = connection.createStatement();
	      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * FROM lookslike");
	     // execute the query, and get a java resultset
	      ResultSet rs = query.executeQuery();
	       
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  SpeciesConfiguration theConfig = new SpeciesConfiguration();
	    	 theConfig.setConfigurationId(rs.getInt("lookslikeId"));
	    	 theConfig.setConfigurationName(rs.getString("speciesName"));
	    	 theConfig.setConfigurationStatus("Active");
	    	 theConfig.setConfigurationType("lookslike");
	    	 currentConfigs.add(theConfig);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		listofAlConfigs.setAllConfigList(currentConfigs);
		
		return listofAlConfigs;
	}

	@Override
	public Species importCsv(String fileName) {
		Species speciesList = new Species();
		//filename -- test current file
		fileName = "TBS";
		//Setup arraylist to hold users
		List<Species> currentSpecieList = new ArrayList<Species>();
		//List<Species> returnedSpeciesList = new ArrayList<Species>();
		//populate the array list with CSV contents
		String fileNameTest = System.getProperty("user.home")+"/species2.csv";
		SpeciesCsvReader myCsv = new SpeciesCsvReader();
		currentSpecieList = SpeciesCsvReader.readCsvFile(fileNameTest);
		
			
				 try
				    {
						// create the java statement
				      Statement st = connection.createStatement();
				       // execute the query, and get a java resultset
				      for(Species currentSpecis: currentSpecieList){
				    	  PreparedStatement query = (PreparedStatement) connection.prepareStatement("INSERT INTO species(speciesId, speciesName, speciesDescription, pose, colour, scale, lookslike1, lookslike2, lookslike3) VALUES(?,?,?,?,?,?,?,?,?)");
						   
				    	 query.setString(1, currentSpecis.getSpeciesId());
				    	  query.setString(2, currentSpecis.getSpeciesName());
				    	  query.setString(3, currentSpecis.getSpeciesDescription());
				    	  query.setString(4, currentSpecis.getPose());
				    	  query.setString(5, currentSpecis.getColour());
				    	  query.setString(6, currentSpecis.getScale());
				    	  query.setString(7, currentSpecis.getSpeciesLooklike1());
				    	  query.setString(8, currentSpecis.getSpeciesLooklike2());
				    	  query.setString(9, currentSpecis.getSpeciesLooklike3());
				    	  query.executeUpdate();
				    	//the below is for error handling purpose so that the list only populated insert to DB is successfully
				    	  speciesList.setListOfSpecies(currentSpecieList);
				      }
				       st.close();
				    }
				    catch (Exception e)
				    {
				      System.err.println("Got an exception! ");
				      System.err.println(e.getMessage());
				    }
			 
		return speciesList;
	}

	@Override
	public String saveSpecies(String speciesID, String speciesName,
			String speciesDesc, String speciesLookslike1,
			String speciesLookslike2, String speciesLookslike3) {
			String returnMessage ="";
		  try{
			  Statement st = connection.createStatement();
		    	  PreparedStatement query = (PreparedStatement) connection.prepareStatement("INSERT INTO species(speciesId, speciesName, speciesDescription, lookslike1, lookslike2, lookslike3) VALUES(?,?,?,?,?,?)");
				   
			    	 query.setString(1, speciesID);
			    	  query.setString(2, speciesName);
			    	  query.setString(3, speciesDesc);
			    	  query.setString(4, speciesLookslike1);
			    	  query.setString(5, speciesLookslike2);
			    	  query.setString(6, speciesLookslike3);
			    	  query.executeUpdate();
		  
	       st.close();
	       returnMessage ="Species Recorded Successfully";
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      returnMessage ="System failed to record species!!";
	    } 
		  
		return returnMessage;
	}

	@Override
	public Species getAllSpecies() {
		Species speciesList = new Species();
		//Setup arraylist to hold users
		List<Species> listofSpecies = new ArrayList<Species>();
		try
	    {
			// create the java statement
	      Statement st = connection.createStatement();
	      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * FROM species");
	     // execute the query, and get a java resultset
	      ResultSet rs = query.executeQuery();
	       
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  Species aSpecies = new Species();
	    	  aSpecies.setSpeciesId(rs.getString("speciesId"));
	    	  aSpecies.setSpeciesName(rs.getString("speciesName"));
	    	  aSpecies.setSpeciesDescription(rs.getString("speciesDescription"));
	    	  aSpecies.setColour(rs.getString("colour"));
	    	  aSpecies.setScale(rs.getString("scale"));
	    	  aSpecies.setPose(rs.getString("pose"));
	    	  aSpecies.setSpeciesLooklike1(rs.getString("lookslike1"));
	    	  aSpecies.setSpeciesLooklike2(rs.getString("lookslike2"));
	    	  aSpecies.setSpeciesLooklike3(rs.getString("lookslike3"));
	    	  listofSpecies.add(aSpecies);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		 speciesList.setListOfSpecies(listofSpecies);
		return speciesList;

	}

	@Override
	public String saveEditedSpecies(String speciesID, String speciesName,
			String speciesDesc, String speciesLookslike1,
			String speciesLookslike2, String speciesLookslike3) {
			String returnMessage ="";
		 try{
			  Statement st = connection.createStatement();
		    	  PreparedStatement query = (PreparedStatement) connection.prepareStatement("UPDATE species SET speciesName=?, speciesDescription=?, lookslike1=?, lookslike2=?, lookslike3=? where speciesId=?");
				   
			    	query.setString(1, speciesName);
			    	  query.setString(2, speciesDesc);
			    	  query.setString(3, speciesLookslike1);
			    	  query.setString(4, speciesLookslike2);
			    	  query.setString(5, speciesLookslike3);
			    	  query.setString(6, speciesID);
			    	  query.executeUpdate();
		  
	       st.close();
	       returnMessage ="Species Recorded Successfully";
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      returnMessage ="Failed";
	    } 
		return returnMessage;
	}

	@Override
	public String saveLooksLike(String looksLikeValue) {
		String status ="Failed";
		 try
		    {
				// create the java statement
		      Statement st = connection.createStatement();
		       // execute the query, and get a java resultset
		      
		    	  PreparedStatement query = (PreparedStatement) connection.prepareStatement("INSERT INTO lookslike(speciesName) VALUES(?)");
				   
		    	 query.setString(1, looksLikeValue);
		    	  
		    	  query.executeUpdate();
		    	   status = looksLikeValue + " recorded successfully";
		      
		       st.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
	 return status;
	}

	@Override
	public Study getStudyBehavConfigs(String studyId) {
		Study studyConfig = new Study();
		//Setup arraylist to hold users
		
		List<Study> listOfStudyConfig = new ArrayList<Study>();
		try
	    {
			// create the java statement
	      Statement st = connection.createStatement();
	      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * from behavior where studyId=?");
	      query.setString(1, studyId);
	     // execute the query, and get a java resultset
	      ResultSet rs = query.executeQuery();
	      
	         
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  Study aStudyBehavior = new Study();
	    	  aStudyBehavior.setBehaviorId(rs.getInt("behaviorId"));
	    	  aStudyBehavior.setStudyId(rs.getString("studyId"));
	    	  aStudyBehavior.setBehavior(rs.getString("behavior"));
	    	  aStudyBehavior.setBehaviorStatus(rs.getString("status"));
	    	  listOfStudyConfig.add(aStudyBehavior);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
		studyConfig.setListOfStudyConfig(listOfStudyConfig);
		
		return studyConfig;
	}

	@Override
	public Study getStudyPoseConfigs(String studyId) {
		Study studyConfig = new Study();
		//Setup arraylist to hold users
		
		List<Study> listOfStudyConfig = new ArrayList<Study>();
		try
	    {
			// create the java statement
	      Statement st = connection.createStatement();
	      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * from poses where studyId=?");
	      query.setString(1, studyId);
	     // execute the query, and get a java resultset
	      ResultSet rs = query.executeQuery();
	      
	         
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  Study aStudyPose = new Study();
	    	  aStudyPose.setPoseId(rs.getInt("poseId"));
	    	  aStudyPose.setStudyId(rs.getString("studyId"));
	    	  aStudyPose.setPose(rs.getString("pose"));
	    	  aStudyPose.setPoseStatus(rs.getString("status"));
	    	  listOfStudyConfig.add(aStudyPose);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
		studyConfig.setListOfStudyConfig(listOfStudyConfig);
		
		return studyConfig;
	}

	@Override
	public String saveStudyBehavior(int behaviorId, String behaviorStatus) {
		String returnMessage ="";
		 try{
			  Statement st = connection.createStatement();
		    	  PreparedStatement query = (PreparedStatement) connection.prepareStatement("UPDATE behavior SET status=? where behaviorId=?");
				   
			    	query.setString(1, behaviorStatus);
			    	  query.setInt(2, behaviorId);
			    	  query.executeUpdate();
		  
	       st.close();
	       returnMessage ="Behavior updated Successfully";
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      returnMessage ="Failed";
	    } 
		return returnMessage;

		
	}

	@Override
	public String saveStudyPose(int poseId, String poseStatus) {
		String returnMessage ="";
		 try{
			  Statement st = connection.createStatement();
		    	  PreparedStatement query = (PreparedStatement) connection.prepareStatement("UPDATE poses SET status=? where poseId=?");
				   
			    	query.setString(1, poseStatus);
			    	  query.setInt(2, poseId);
			    	  query.executeUpdate();
		  
	       st.close();
	       returnMessage ="Behavior updated Successfully";
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      returnMessage ="Failed";
	    } 
		return returnMessage;
	}

	@Override
	public Study getStudyColourConfigs(String studyId) {
		Study studyConfig = new Study();
		//Setup arraylist to hold users
		
		List<Study> listOfStudyConfig = new ArrayList<Study>();
		try
	    {
			// create the java statement
	      Statement st = connection.createStatement();
	      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * from colour where studyId=?");
	      query.setString(1, studyId);
	     // execute the query, and get a java resultset
	      ResultSet rs = query.executeQuery();
	      
	         
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  Study aStudyColor = new Study();
	    	  aStudyColor.setColourId(rs.getInt("colourId"));
	    	  aStudyColor.setStudyId(rs.getString("studyId"));
	    	  aStudyColor.setColour(rs.getString("colour"));
	    	  aStudyColor.setColourStatus(rs.getString("status"));
	    	  listOfStudyConfig.add(aStudyColor);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
		studyConfig.setListOfStudyConfig(listOfStudyConfig);
		
		return studyConfig;
	}

	@Override
	public Study getStudyScaleConfigs(String studyId) {
		Study studyConfig = new Study();
		//Setup arraylist to hold users
		
		List<Study> listOfStudyConfig = new ArrayList<Study>();
		try
	    {
			// create the java statement
	      Statement st = connection.createStatement();
	      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * from scale where studyId=?");
	      query.setString(1, studyId);
	     // execute the query, and get a java resultset
	      ResultSet rs = query.executeQuery();
	      
	         
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  Study aStudyScale = new Study();
	    	  aStudyScale.setScaleId(rs.getInt("scaleId"));
	    	  aStudyScale.setStudyId(rs.getString("studyId"));
	    	  aStudyScale.setScale(rs.getString("scale"));
	    	  aStudyScale.setScaleStatus(rs.getString("status"));
	    	  listOfStudyConfig.add(aStudyScale);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
		studyConfig.setListOfStudyConfig(listOfStudyConfig);
		
		return studyConfig;

	}

	@Override
	public String saveStudyColour(int colourId, String colourStatus) {
		String returnMessage ="";
		 try{
			  Statement st = connection.createStatement();
		    	  PreparedStatement query = (PreparedStatement) connection.prepareStatement("UPDATE colour SET status=? where colourId=?");
				   
			    	query.setString(1, colourStatus);
			    	  query.setInt(2, colourId);
			    	  query.executeUpdate();
		  
	       st.close();
	       returnMessage ="Colour updated Successfully";
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      returnMessage ="Failed";
	    } 
		return returnMessage;
	}

	@Override
	public String saveStudyScale(int scaleId, String scaleStatus) {
		String returnMessage ="";
		 try{
			  Statement st = connection.createStatement();
		    	  PreparedStatement query = (PreparedStatement) connection.prepareStatement("UPDATE scale SET status=? where scaleId=?");
				   
			    	query.setString(1, scaleStatus);
			    	  query.setInt(2, scaleId);
			    	  query.executeUpdate();
		  
	       st.close();
	       returnMessage ="Scale updated Successfully";
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      returnMessage ="Failed";
	    } 
		return returnMessage;
	}

	@Override
	public String saveStudyConfig(String studyConfigType, String studyId,
			String ConfigName) {
		studyConfigType.toLowerCase();
		String returnMessage ="Failed";
		 //if(studyConfigType.equals("poses")) studyConfigType = pose;
		 try{
			     Statement st = connection.createStatement();
			     if(studyConfigType.equals("Poses")){
			      	 PreparedStatement query = (PreparedStatement) connection.prepareStatement("INSERT INTO poses(studyId, pose) VALUES(?,?)");
				    	query.setString(1, studyId);
				    	  query.setString(2, ConfigName);
				    	  query.executeUpdate();
		    	  
			     }
			     else{
			    	 PreparedStatement query = (PreparedStatement) connection.prepareStatement("INSERT INTO " +studyConfigType+"(studyId, "+studyConfigType+") VALUES(?,?)");
				    	query.setString(1, studyId);
				    	  query.setString(2, ConfigName);
				    	  query.executeUpdate();
			     }
		  
	       st.close();
	       returnMessage =ConfigName;
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      returnMessage ="Failed";
	    } 
		return returnMessage;
	}

	@Override
	public String deleteLookslikeConfig(int lookslikeId) {
		String returnMessage ="Failed";
		 //if(studyConfigType.equals("poses")) studyConfigType = pose;
		 try{
			     Statement st = connection.createStatement();
			  
			      	 PreparedStatement query = (PreparedStatement) connection.prepareStatement("DELETE FROM lookslike WHERE lookslikeId=?");
				    	query.setInt(1, lookslikeId);
				 
				    	  query.executeUpdate();
		     st.close();
	       returnMessage =Integer.toString(lookslikeId);
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      returnMessage ="Failed";
	    } 
		return returnMessage;
	}

	@Override
	public ImagesList getImageNames() {
		ImagesList theImage = new ImagesList();
		ArrayList<String> imageFilenNames = new ArrayList<String>();
		try(DirectoryStream<Path> ds = 
				//Files.newDirectoryStream(FileSystems.getDefault().getPath("./imagecaptures"))){
				Files.newDirectoryStream(FileSystems.getDefault().getPath(theImagesFolderPath))){
			   
				try{
				 
				 String query = "SELECT * FROM imagecaptures where identificationStatus='n'";
				 Statement st1 = connection.createStatement();
				 ResultSet rs = st1.executeQuery(query);
				 ArrayList<String> dbImagesList = new ArrayList<String>();
				 ArrayList<String> newImagesList = new ArrayList<String>();
				 ArrayList<String> duplicateImagesList = new ArrayList<String>();
				//CREATE A LIST OF NEW IMAGES
				 for (Path p : ds) {
					 String afile = p.getFileName().toString();
					 newImagesList.add(afile);
				 }
				 
				 //GET CURRENT LIST OF IMAGES ALREADY IN THE DATABASE AND CHANGE IF NEW IMAGES LIST HAS IMAGES ALREADY IN DATABASE
				 if(!rs.next()){
					 System.out.println("No images in the database");
				 }else
				 {
					 do {
						 dbImagesList.add(rs.getString("imageID"));
						 if(newImagesList.contains(rs.getString("imageID"))) {
							 duplicateImagesList.add(rs.getString("imageID"));
						 }
					 }while(rs.next());
				 }
				 //REMOVE ANY FOUND DUPLICATE IMAGES FROM THE NEW IMAGES LIST
				 for(int i=0; i<duplicateImagesList.size();i++) {
					 System.out.println("Exists already: "+duplicateImagesList.get(i));
					 imageFilenNames.add(duplicateImagesList.get(i)+": Image already exists");
					 System.out.println("Removing the duplicate from the new images list");
					 newImagesList.remove(duplicateImagesList.get(i));
				 }
				 //ADD NEW IMAGES TO THE DATABASE
				 System.out.println("ADDING NEW IMAGES");
				 for(int j=0; j<newImagesList.size();j++) {
					 System.out.println(newImagesList.get(j));
					 //Insert image data to the database
					 PreparedStatement query1 = (PreparedStatement) connection.prepareStatement("INSERT INTO imagecaptures(imageID, location, sensorID)VALUES(?,?,?)");
	 			      query1.setString(1, newImagesList.get(j));
	 			      query1.setString(2, "NSW");
	 			      query1.setString(3, "NSW Sensor");
	 			     
	 			     // execute the query, and insert the record to the database
	 			        query1.executeUpdate();
		    		//Now copy the image to the server folder
		  			 DBUtility.copyImage(theImagesFolderPath, liveImagesFolderPath, newImagesList.get(j)); 
					 imageFilenNames.add("New image added: "+newImagesList.get(j));
				 }
				 imageFilenNames.add("--------"+newImagesList.size()+" NEW IMAGES ADDED------");    	   
	    	 
				 st1.close();
			}catch (Exception e){
			      System.err.println("Got an exception! ");
			      System.err.println(e.getMessage());
			    }
	    	
	      }catch (IOException e) {
	    	   e.printStackTrace();
	    	}
		
		theImage.setImageNames(imageFilenNames);
		return theImage;
	}

	@Override
	public String activateStudy(String studyId) {
		String returnMessage ="Failed";	
		 try{
			 //First query is to check if there is a record/a study that has been made active if so then return failed 
			 //Second query is to update study with active status
			     Statement st = connection.createStatement();
			  
			      	 PreparedStatement query1 = (PreparedStatement) connection.prepareStatement("SELECT * FROM study WHERE status='Active'");
			      	ResultSet rs = query1.executeQuery();
	 			     if(rs.last()==true){
	 			    	returnMessage ="Failed";
	 			     }else{
	 			    	PreparedStatement query2= (PreparedStatement) connection.prepareStatement("update study set status='Active', date_activated=?, startDate=? where studyId=?");
	 		              query2.setString(1, properDate);
	 		             query2.setDate(2, currentDate);
	 		             query2.setString(3, studyId);
	 		    	        query2.executeUpdate();
	 		    	       returnMessage =studyId;
	 		    	     }
				 
				  st.close();
	     
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      returnMessage ="Failed";
	    } 
		return returnMessage;
	}

	@Override
	public String deActivateStudy(String studyId) {
		String returnMessage ="Failed";
		 try{
			     Statement st = connection.createStatement();
			     PreparedStatement query= (PreparedStatement) connection.prepareStatement("update study set status='Inactive', date_deactivated=? where studyId=?");
	 		              query.setString(1, properDate);
	 		            query.setString(2, studyId);
	 		    	        query.executeUpdate();
	 		    	       returnMessage =studyId;
	 			     st.close();
	     
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      returnMessage ="Failed";
	    } 
		return returnMessage;
	}

	@Override
	public String deactivateUser(int userId) {
		String returnMessage ="Failed";
		 try{
			     Statement st = connection.createStatement();
			     PreparedStatement query= (PreparedStatement) connection.prepareStatement("update members set status='N' where id=?");
	 		              query.setInt(1, userId);
	 		    	        query.executeUpdate();
	 		    	       returnMessage =String.valueOf(userId);
	 			     st.close();
	     
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      returnMessage ="Failed";
	    } 
		return returnMessage;
	}

	@Override
	public String activateUser(String username) {
		String returnMessage ="Failed";
		 try{
			     Statement st = connection.createStatement();
			     PreparedStatement query= (PreparedStatement) connection.prepareStatement("update members set status='A' where uname=?");
	 		              query.setString(1, username);
	 		    	        query.executeUpdate();
	 		    	       returnMessage =username;
	 			     st.close();
	     
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      returnMessage ="Failed";
	    } 
		return returnMessage;
	}

	

}
