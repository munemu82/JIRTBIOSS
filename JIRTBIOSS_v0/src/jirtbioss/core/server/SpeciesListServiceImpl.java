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

import jirtbioss.core.client.model.Imageidentity;
import jirtbioss.core.client.model.ImagesList;
import jirtbioss.core.client.model.Species;
import jirtbioss.core.client.model.SpeciesConfiguration;
import jirtbioss.core.client.model.Study;
import jirtbioss.core.client.service.SpeciesListService;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mysql.jdbc.PreparedStatement;

@SuppressWarnings("serial")
public class SpeciesListServiceImpl extends RemoteServiceServlet implements SpeciesListService {
	 private Connection connection = DBUtility.getConnection();
	 private String theImagesFolderPath = DBUtility.getLiveImagesFolderPath();

	@Override
	public ImagesList getImages() {
		ImagesList imagesList = new ImagesList();
		
		//Set up array of images and their paths
		ArrayList<String> speciesImages = new ArrayList<String>();
				 try
		    {
		    
		      //String query = "SELECT * FROM imagecaptures where identificationStatus='n'";
			  String query = "SELECT * FROM imagecaptures where imageID NoT IN(Select imageID from imageidentity)";
		 
		      // create the java statement
		      Statement st = connection.createStatement();
		       
		      // execute the query, and get a java resultset
		      ResultSet rs = st.executeQuery(query);
		       
		      // iterate through the java resultset
		      while (rs.next())
		      {
		    	 // speciesImages.add("imagecaptures/"+rs.getString("imageID")+".jpg");
		    	  speciesImages.add(theImagesFolderPath+rs.getString("imageID"));
		      }
		      st.close();
		      
		    }catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		//Add array of images to the images list object
		
		
		imagesList.setSpeciesImages(speciesImages);
		return imagesList;
	}

	@Override
	public Species getSpeciesList() {
		Species speciesList = new Species();
	
		//Setup array list of speciesnames
		List<Species> theSpeciesList = new ArrayList<Species>();
		 try
		    {
		    
		    //  String query = "SELECT * FROM imageidentity";
			 String query = "SELECT * FROM species";
		      // create the java statement
		      Statement st = connection.createStatement();
		       
		      // execute the query, and get a java resultset
		      ResultSet rs = st.executeQuery(query);
		       
		      // iterate through the java resultset
		      while (rs.next())
		      {
		    	  Species aSpecies = new Species();
		    	  aSpecies.setSpeciesName(rs.getString("speciesName"));
		    	  aSpecies.setSpeciesDescription(rs.getString("speciesDescription"));
		    	  aSpecies.setSpeciesLooklike1(rs.getString("lookslike1"));
		    	  aSpecies.setSpeciesLooklike2(rs.getString("lookslike2"));
		    	  aSpecies.setSpeciesLooklike3(rs.getString("lookslike3"));
		    	  theSpeciesList.add(aSpecies);
		    	  
		      }
		      st.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }

		 speciesList.setListOfSpecies(theSpeciesList);
		
		return speciesList;
	}

	@Override
	public Species getSpeciesListLkLike(String looksLikeFilter) {
		//record type
		Species speciesList = new Species();
		
		//Setup array list of speciesnames
				ArrayList<Species> speciesNames = new ArrayList<Species>();
				 try
				    {
				    
					  // create the java statement
				      Statement st = connection.createStatement();
				      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * FROM species where lookslike1=? OR lookslike2=? OR lookslike3=?");
				      query.setString(1, looksLikeFilter);
				      query.setString(2, looksLikeFilter);
				      query.setString(3, looksLikeFilter);
				       
				      // execute the query, and get a java resultset
				      ResultSet rs = query.executeQuery();
				       
				      // iterate through the java resultset
				      while (rs.next())
				      {
				    	  Species aSpecies = new Species();
				    	  aSpecies.setSpeciesName(rs.getString("speciesName"));
				    	  aSpecies.setSpeciesDescription(rs.getString("speciesDescription"));
				    	  speciesNames.add(aSpecies);
				      }
				      st.close();
				    }
				    catch (Exception e)
				    {
				      System.err.println("Got an exception! ");
				      System.err.println(e.getMessage());
				    }

				 speciesList.setListOfSpecies(speciesNames);
				
				return speciesList;

	}
	//insert a new identification record and update the imagecaptures table
	@Override
	public String InsertIdentification(String username, String behavior, String howMany,
			String youngPrent, String userCommment, String imageId, String species, String colour, String pose, String scale) {
		 //success flag 
	      String insertSuccess = "Failed";
	      String currentStudy ="DEFAULT00";
	    //fix imageid which is now = imagecaptures/image_0048.jpg (i.e. including the last folder path)
	     imageId = imageId.substring(imageId.indexOf("/") + 1);
		 System.out.println(imageId); 
		//prepare SQL
	      try
		    {
			 Statement st = connection.createStatement();
			  //Get current studyID
		      PreparedStatement query1 = (PreparedStatement) connection.prepareStatement("SELECT studyId from study where status='Active'");
		      ResultSet rs = query1.executeQuery();
		      while (rs.next())
		      {
		       currentStudy = rs.getString(1);
		      }
		    // First query to insert the new identification into the database QUERY 1
		      PreparedStatement query2 = (PreparedStatement) connection.prepareStatement("INSERT INTO imageidentity(username, behavior, number, children, usercomment, imageId, pose, species, looksLike, scale, studyId, color)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
		      query2.setString(1, username);
		      query2.setString(2, behavior);
		      query2.setString(3, howMany);
		      query2.setString(4, youngPrent);
		      query2.setString(5, userCommment);
		      query2.setString(6, imageId);
		      query2.setString(7, pose);
		      query2.setString(8, species);
		      query2.setString(9, species);
		      query2.setString(10, scale);
		      query2.setString(11, currentStudy);
		      query2.setString(12, colour);
		      
		      // execute the query, and insert the record to the database
		        query2.executeUpdate();
		      
		        
		      //SECOND QUERY TO UPDATE THE IMAGE CAPTURE TO MAKE SURE THAT
		      PreparedStatement query3 = (PreparedStatement) connection.prepareStatement("Update imagecaptures set identificationStatus = ? where imageID = ?");
		      query3.setString(1, "y");
		      query3.setString(2, imageId);
		      //execute the second query to update the identified image 
		      query3.executeUpdate(); 
		       st.close();
		       insertSuccess = "Success";
		    }
	
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		      //debug 
		      System.out.println("Parameters: "+behavior+" "+howMany+" "+youngPrent+" "+imageId+" Current Study is: "+currentStudy);
		    }

		return insertSuccess;
	}

	@Override
	public Species getSpeciesNamesByFilter(String filteredString) {
		//record type
				Species speciesList = new Species();
				
				//Setup array list of speciesnames
						List<Species> filteredSpeciesList = new ArrayList<Species>();
						 try
						    { 
						      // create the java statement
						      Statement st = connection.createStatement();
						      PreparedStatement query = (PreparedStatement) connection.prepareStatement(filteredString);
						    
						      // execute the query, and get a java resultset
						      ResultSet rs = query.executeQuery();
						       
						      // iterate through the java resultset
						      while (rs.next())
						      {
						    	  Species 	filterdSpecies = new Species();
						    	  //speciesNames.add(rs.getString("speciesName"));
						    	  filterdSpecies.setSpeciesName(rs.getString("speciesName"));
						    	  filterdSpecies.setSpeciesDescription(rs.getString("speciesDescription"));
						    	  filteredSpeciesList.add(filterdSpecies);
						      }
						      st.close();
						    }
						    catch (Exception e)
						    {
						      System.err.println("Got an exception! ");
						      System.err.println(e.getMessage());
						    }

						 //speciesList.setFilterdSpeciesList(filteredSpeciesList);
						 speciesList.setFilterdSpeciesList(filteredSpeciesList);
						return speciesList;
	}

	@Override
	public SpeciesConfiguration speciesLookslikeList() {
		SpeciesConfiguration listOfLookslike = new SpeciesConfiguration();
		//Setup array list of speciesnames
		ArrayList<String> theLookslikeList = new ArrayList<String>();
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
		    	  //species[index] = rs.getString("species");
		    	 // index++;
		    	  theLookslikeList.add(rs.getString("speciesName"));
		      }
		      st.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		 //set a return list
		 listOfLookslike.setLooksLikeList(theLookslikeList);
		return listOfLookslike;
	}

	@Override
	public Study getActiveStudyColors() {
		Study studyConfig = new Study();
		
		//Setup array list of speciesnames
		List<Study> studyConfigs = new ArrayList<Study>();
		 try
		    {
		    
		    //  String query = "SELECT * FROM imageidentity";
			 String query1 = "SELECT * FROM colour where status='A'";
		      // create the java statement
		      Statement st = connection.createStatement();
		       
		      // execute the query, and get a java resultset
		      ResultSet rs = st.executeQuery(query1);
		       
		      // iterate through the java resultset
		      while (rs.next())
		      {
		    	  Study aStudy = new Study();
		    	  aStudy.setColour(rs.getString("colour"));
		    	  aStudy.setStudyId(rs.getString("studyId"));
		    	  studyConfigs.add(aStudy);
		    	
		      }
		      st.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		 

		 studyConfig.setListOfStudyConfig(studyConfigs);
		
		return studyConfig;

	}

	@Override
	public Study getActiveStudyBehaviors() {
Study studyConfig = new Study();
		
		//Setup array list of speciesnames
		List<Study> studyConfigs = new ArrayList<Study>();
		 try
		    {
		    
		    //  String query = "SELECT * FROM imageidentity";
			 String query1 = "SELECT * FROM behavior where status='A'";
		      // create the java statement
		      Statement st = connection.createStatement();
		       
		      // execute the query, and get a java resultset
		      ResultSet rs = st.executeQuery(query1);
		       
		      // iterate through the java resultset
		      while (rs.next())
		      {
		    	  Study aStudy = new Study();
		    	  aStudy.setBehavior(rs.getString("behavior"));
		    	  aStudy.setStudyId(rs.getString("studyId"));
		    	  studyConfigs.add(aStudy);
		    	
		      }
		      st.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		 

		 studyConfig.setListOfStudyConfig(studyConfigs);
		
		return studyConfig;
	}

	@Override
	public Study getActiveStudyPoses() {
		Study studyConfig = new Study();
		//Setup array list of speciesnames
				List<Study> studyConfigs = new ArrayList<Study>();
				 try{
				    //  String query = "SELECT * FROM imageidentity";
					 String query1 = "SELECT * FROM poses where status='A'";
				      // create the java statement
				      Statement st = connection.createStatement();
				       
				      // execute the query, and get a java resultset
				      ResultSet rs = st.executeQuery(query1);
				       
				      // iterate through the java resultset
				      while (rs.next()) {
				    	  Study aStudy = new Study();
				    	  aStudy.setPose(rs.getString("pose"));
				    	  aStudy.setStudyId(rs.getString("studyId"));
				    	  studyConfigs.add(aStudy);
				    	}
				      st.close();
				    }
				    catch (Exception e)
				    {
				      System.err.println("Got an exception! ");
				      System.err.println(e.getMessage());
				    }
				 studyConfig.setListOfStudyConfig(studyConfigs);
				return studyConfig;
			}

	@Override
	public String getActiveStudy() {
		String theStudyId ="Not Set";
		try{
		    //  String query = "SELECT * FROM imageidentity";
			 String query1 = "SELECT studyId FROM study where status='Active'";
		      // create the java statement
		      Statement st = connection.createStatement();
		       
		      // execute the query, and get a java resultset
		      ResultSet rs = st.executeQuery(query1);
		      while (rs.next()){
		      theStudyId =  rs.getString("studyId");
		      }
		      st.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		return theStudyId;

		
	}

	@Override
	public Study getActiveStudyScales() {
		Study studyConfig = new Study();
		//Setup array list of speciesnames
				List<Study> studyConfigs = new ArrayList<Study>();
				 try{
				    //  String query = "SELECT * FROM imageidentity";
					 String query1 = "SELECT * FROM scale where status='A'";
				      // create the java statement
				      Statement st = connection.createStatement();
				       
				      // execute the query, and get a java resultset
				      ResultSet rs = st.executeQuery(query1);
				       
				      // iterate through the java resultset
				      while (rs.next()) {
				    	  Study aStudy = new Study();
				    	  aStudy.setScale(rs.getString("scale"));
				    	  aStudy.setStudyId(rs.getString("studyId"));
				    	  studyConfigs.add(aStudy);
				    	}
				      st.close();
				    }
				    catch (Exception e)
				    {
				      System.err.println("Got an exception! ");
				      System.err.println(e.getMessage());
				    }
				 studyConfig.setListOfStudyConfig(studyConfigs);
				return studyConfig;

	}


}
