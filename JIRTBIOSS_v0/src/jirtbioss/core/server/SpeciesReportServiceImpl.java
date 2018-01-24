package jirtbioss.core.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jirtbioss.core.client.model.Imageidentity;
import jirtbioss.core.client.model.SpeciesCsvReport;
import jirtbioss.core.client.model.Study;
import jirtbioss.core.client.service.SpeciesReportService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mysql.jdbc.PreparedStatement;

@SuppressWarnings("serial")
public class SpeciesReportServiceImpl extends RemoteServiceServlet implements SpeciesReportService{
	 private Connection connection = DBUtility.getConnection();

	@Override
	public Imageidentity getSpeciesObjects() {
		//record type
				Imageidentity speciesList = new Imageidentity();
				
				//Setup array list of speciesnames
						List<Imageidentity> speciesRecords = new ArrayList<Imageidentity>();
						 try
						    {
						    
						      // create the java statement
						      Statement st = connection.createStatement();
						      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * FROM imageidentity");
						     
						       
						      // execute the query, and get a java resultset
						      ResultSet rs = query.executeQuery();
						       
						      // iterate through the java resultset
						      while (rs.next())
						      {
						    	  //species[index] = rs.getString("species");
						    	  Imageidentity speciesRecord = new Imageidentity();
						    	 // index++;
						    	  speciesRecord.setRecordId(rs.getLong("recordId"));
						    	  speciesRecord.setImageid(rs.getString("imageId"));
						    	  speciesRecord.setBehavior(rs.getString("behavior"));
						    	  speciesRecord.setNumber(rs.getInt("number"));
						    	  speciesRecord.setPose(rs.getString("pose"));
						    	  speciesRecord.setChildren(rs.getString("children"));
						    	  speciesRecord.setScale(rs.getString("scale"));
						    	  speciesRecord.setColor(rs.getString("color"));
						    	  speciesRecord.setSpecies(rs.getString("species"));
						    	  speciesRecord.setStudyId(rs.getString("studyId"));
						    	  speciesRecords.add(speciesRecord);
						      }
						      st.close();
						    }
						    catch (Exception e)
						    {
						      System.err.println("Got an exception! ");
						      System.err.println(e.getMessage());
						    }
						  speciesList.setSpeciesRecords(speciesRecords);
						return speciesList;

	}

	@Override
	public String exportToCsv(String filter, String filterType) {
		//Setup array list of speciesnames
		String returnFlag = " ";
		List<Imageidentity> speciesRecords = new ArrayList<Imageidentity>();
		 try
		    {
		     
		      // create the java statement
		      Statement st = connection.createStatement();
		      PreparedStatement query = (PreparedStatement) connection.prepareStatement("");
		      switch(filterType){
		      		case "SpeciesName_StudyId": query = (PreparedStatement) connection.prepareStatement(filter);
		      						//query.setString(1, filter);
		      			  break;
		      		case "All_Limit": query = (PreparedStatement) connection.prepareStatement(filter);
		      							//query.setString(1, filter);
		      				 break;
		      		case "StudyId_Limit": query = (PreparedStatement) connection.prepareStatement(filter);
		      			  break;
		      		case "SpeciesName_Limit": query = (PreparedStatement) connection.prepareStatement(filter);
		      			  break;
		      		case "SpeciesName_Advanced": query = (PreparedStatement) connection.prepareStatement(filter);
		      			break;
		      			
		      		case "All": query = (PreparedStatement) connection.prepareStatement("SELECT * FROM imageidentity");
		      		
		      		default:  returnFlag = "No query was set correctly, check the logs!!";
		      		       break;
		      }
		      // execute the query, and get a java resultset
		      ResultSet rs = query.executeQuery();
			       
			      // iterate through the java resultset
			      while (rs.next())
			      {
			    	  //species[index] = rs.getString("species");
			    	  Imageidentity speciesRecord = new Imageidentity();
			    	 // index++;
			    	  speciesRecord.setRecordId(rs.getLong("recordId"));
			    	  speciesRecord.setImageid(rs.getString("imageId"));
			    	  speciesRecord.setBehavior(rs.getString("behavior"));
			    	  speciesRecord.setNumber(rs.getInt("number"));
			    	  speciesRecord.setPose(rs.getString("pose"));
			    	  speciesRecord.setChildren(rs.getString("children"));
			    	  speciesRecord.setScale(rs.getString("scale"));
			    	  speciesRecord.setSpecies(rs.getString("species"));
			    	  speciesRecord.setColor(rs.getString("color"));
			    	  speciesRecord.setStudyId(rs.getString("studyId"));
			    	  
			    	  speciesRecords.add(speciesRecord);
			      }
			     
			     //check if records added to the Arraylist successfully
			      if(speciesRecords.size() > 0){
			    //setup report file name 
					String fileName = System.getProperty("user.home")+"/SpeciesReport.csv";
					SpeciesCsvReport.writeCsvFile(fileName, speciesRecords);
					returnFlag ="SpeciesReport.csv file was created successfully and found in your home directory";
			      }
		      st.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		      returnFlag = "Failed to created CSV file...Please try again later";
		    }
		
		return returnFlag;
	}
	
	@Override
	public Study getStudyIds() {
		//Setup array list of speciesnames
		Study theStudy = new Study();
				ArrayList<String> studyIds = new ArrayList<String>();
				 try
				    {
				    
				      String query = "SELECT * FROM study";
				 
				      // create the java statement
				      Statement st = connection.createStatement();
				       
				      // execute the query, and get a java resultset
				      ResultSet rs = st.executeQuery(query);
				       
				      // iterate through the java resultset
				      while (rs.next())
				      {
				    	  studyIds.add(rs.getString("studyId"));
				      }
				      st.close();
				    }
				    catch (Exception e)
				    {
				      System.err.println("Got an exception! ");
				      System.err.println(e.getMessage());
				    }
				 theStudy.setStudyIds(studyIds);
				return theStudy;
	}
	//this is the same the first RPC but the SQL changes
	@Override
	public Imageidentity getSpeciesByName(String speciesName) {
		//record type
		Imageidentity speciesList = new Imageidentity();
		
		//Setup array list of speciesnames
				List<Imageidentity> speciesRecords = new ArrayList<Imageidentity>();
				 try
				    {
				    
				      // create the java statement
				      Statement st = connection.createStatement();
				      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * FROM imageidentity where species LIKE?");
				      String speciesNameParameter = '%'+speciesName+'%';
				      query.setString(1, speciesNameParameter);
				      
				      // execute the query, and get a java resultset
				      ResultSet rs = query.executeQuery();
				       
				      // iterate through the java resultset
				      while (rs.next())
				      {
				    	  //species[index] = rs.getString("species");
				    	  Imageidentity speciesRecord = new Imageidentity();
				    	 // index++;
				    	  speciesRecord.setRecordId(rs.getLong("recordId"));
				    	  speciesRecord.setImageid(rs.getString("imageId"));
				    	  speciesRecord.setBehavior(rs.getString("behavior"));
				    	  speciesRecord.setNumber(rs.getInt("number"));
				    	  speciesRecord.setPose(rs.getString("pose"));
				    	  speciesRecord.setChildren(rs.getString("children"));
				    	  speciesRecord.setScale(rs.getString("scale"));
				    	  speciesRecord.setColor(rs.getString("color"));
				    	  speciesRecord.setSpecies(rs.getString("species"));
				    	  speciesRecord.setStudyId(rs.getString("studyId"));
				    	  
				    	  speciesRecords.add(speciesRecord);
				      }
				      st.close();
				    }
				    catch (Exception e)
				    {
				      System.err.println("Got an exception! ");
				      System.err.println(e.getMessage());
				    }
				 speciesList.setSpeciesRecordsFilteredName(speciesRecords);
				
				return speciesList;
	}

	@Override
	public Imageidentity getSpeciesByAdvancedFilters(String studyId,
			String speciesName, String NumOfRecord) {
		//record type
				Imageidentity speciesList = new Imageidentity();
				String speciesNameParameter = '%'+speciesName+'%';
				 //int numberOfRecords = Integer.parseInt(NumOfRecord);
				//Setup array list of speciesnames
						List<Imageidentity> speciesRecords = new ArrayList<Imageidentity>();
						 try
						    {
						    
						      // create the java statement
						      Statement st = connection.createStatement();
						      ResultSet rs = null;
						      PreparedStatement query = null;
						      if(!"".equals(speciesName) && !"".equals(NumOfRecord) && !"".equals(studyId)){
						    	   query = (PreparedStatement) connection.prepareStatement("SELECT * FROM imageidentity where species LIKE? AND studyId=? LIMIT "+NumOfRecord);
						    	   query.setString(1, speciesNameParameter);
						    	   query.setString(2, studyId);
						    	   //query.setString(3, NumOfRecord);
						    	    rs= query.executeQuery();
						    	 // rs.setFetchSize(numberOfRecords);
						      }else if(!"".equals(speciesName) && "".equals(studyId) && "".equals(NumOfRecord)){
						    	  query = (PreparedStatement) connection.prepareStatement("SELECT * FROM imageidentity where species LIKE?");
						    	   query.setString(1, speciesNameParameter);
						    	   rs= query.executeQuery();
						      }else if(!"".equals(studyId) && "".equals(speciesName) && "".equals(NumOfRecord)){
						    	  query = (PreparedStatement) connection.prepareStatement("SELECT * FROM imageidentity where studyId=?");
						    	   query.setString(1, studyId);
						    	   rs= query.executeQuery();
						      }else if(!"".equals(studyId) && !"".equals(speciesName) && "".equals(NumOfRecord)){
						    	  query = (PreparedStatement) connection.prepareStatement("SELECT * FROM imageidentity where studyId=? AND species LIKE?");
						    	   query.setString(1, studyId);
						    	   query.setString(2, speciesNameParameter);
						    	   rs= query.executeQuery();
						      }else if(!"".equals(studyId) && !"".equals(NumOfRecord)){
						    	  query = (PreparedStatement) connection.prepareStatement("SELECT * FROM imageidentity where studyId=? LIMIT "+NumOfRecord);
						    	   query.setString(1, studyId);
						    	   rs= query.executeQuery();
						      }else if("".equals(studyId) && "".equals(studyId) && !"".equals(NumOfRecord)){
						    	  query = (PreparedStatement) connection.prepareStatement("SELECT * FROM imageidentity LIMIT "+NumOfRecord);
						    	  rs= query.executeQuery();
						      }
					
						     
						      // iterate through the java resultset
						      while (rs.next())
						      {
						    	  //species[index] = rs.getString("species");
						    	  Imageidentity speciesRecord = new Imageidentity();
						    	 // index++;
						    	  speciesRecord.setRecordId(rs.getLong("recordId"));
						    	  speciesRecord.setImageid(rs.getString("imageId"));
						    	  speciesRecord.setBehavior(rs.getString("behavior"));
						    	  speciesRecord.setNumber(rs.getInt("number"));
						    	  speciesRecord.setPose(rs.getString("pose"));
						    	  speciesRecord.setChildren(rs.getString("children"));
						    	  speciesRecord.setScale(rs.getString("scale"));
						    	  speciesRecord.setColor(rs.getString("color"));
						    	  speciesRecord.setSpecies(rs.getString("species"));
						    	  speciesRecord.setStudyId(rs.getString("studyId"));
						    	  speciesRecords.add(speciesRecord);
						      }
						      st.close();
						    }
						    catch (Exception e)
						    {
						      System.err.println("Got an exception! ");
						      System.err.println(e.getMessage());
						    }
						 speciesList.setSpeciesRecordsAdvancedFilters(speciesRecords);
						
						return speciesList;
	}

	@Override
	public Imageidentity getSpeciesByStudyId(String studyId) {
		//record type
				Imageidentity speciesList = new Imageidentity();
				
				//Setup array list of speciesnames
						List<Imageidentity> speciesRecords = new ArrayList<Imageidentity>();
						 try
						    {
						    
						      // create the java statement
						      Statement st = connection.createStatement();
						      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * FROM imageidentity where studyId=?");
						      
						      query.setString(1, studyId);
						      
						      // execute the query, and get a java resultset
						      ResultSet rs = query.executeQuery();
						       
						      // iterate through the java resultset
						      while (rs.next())
						      {
						    	  //species[index] = rs.getString("species");
						    	  Imageidentity speciesRecord = new Imageidentity();
						    	 // index++;
						    	  speciesRecord.setRecordId(rs.getLong("recordId"));
						    	  speciesRecord.setImageid(rs.getString("imageId"));
						    	  speciesRecord.setBehavior(rs.getString("behavior"));
						    	  speciesRecord.setNumber(rs.getInt("number"));
						    	  speciesRecord.setPose(rs.getString("pose"));
						    	  speciesRecord.setChildren(rs.getString("children"));
						    	  speciesRecord.setScale(rs.getString("scale"));
						    	  speciesRecord.setColor(rs.getString("color"));
						    	  speciesRecord.setSpecies(rs.getString("species"));
						    	  speciesRecord.setStudyId(rs.getString("studyId"));
						    	  
						    	  speciesRecords.add(speciesRecord);
						      }
						      st.close();
						    }
						    catch (Exception e)
						    {
						      System.err.println("Got an exception! ");
						      System.err.println(e.getMessage());
						    }
						 speciesList.setSpeciesRecordsByStudyId(speciesRecords);
						
						return speciesList;

	}

	@Override
	public String exportToCsvByStudyId(String studyId) {
		//Setup array list of speciesnames
				String returnFlag = "Failed to created CSV file...Please try again later";
				List<Imageidentity> speciesRecords = new ArrayList<Imageidentity>();
				 try
				    {
				     
				      // create the java statement
				      Statement st = connection.createStatement();
				      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * FROM imageidentity where studyId=?");
				      	query.setString(1, studyId);
				      				
				      // execute the query, and get a java resultset
				      ResultSet rs = query.executeQuery();
				       
				      // iterate through the java resultset
				      while (rs.next())
				      {
				    	  //species[index] = rs.getString("species");
				    	  Imageidentity speciesRecord = new Imageidentity();
				    	 // index++;
				    	  speciesRecord.setRecordId(rs.getLong("recordId"));
				    	  speciesRecord.setImageid(rs.getString("imageId"));
				    	  speciesRecord.setBehavior(rs.getString("behavior"));
				    	  speciesRecord.setNumber(rs.getInt("number"));
				    	  speciesRecord.setPose(rs.getString("pose"));
				    	  speciesRecord.setChildren(rs.getString("children"));
				    	  speciesRecord.setScale(rs.getString("scale"));
				    	  speciesRecord.setSpecies(rs.getString("species"));
				    	  speciesRecord.setColor(rs.getString("color"));
				    	  speciesRecord.setStudyId(rs.getString("studyId"));
				    	  
				    	  speciesRecords.add(speciesRecord);
				      }
				      st.close();
				     //check if records added to the Arraylist successfully
				      if(speciesRecords.size() > 0){
				    //setup report file name 
						String fileName = System.getProperty("user.home")+"/SpeciesReport.csv";
						SpeciesCsvReport.writeCsvFile(fileName, speciesRecords);
						returnFlag ="SpeciesReport.csv file was created successfully and found in your home directory";
				      }
				    }
				    catch (Exception e)
				    {
				      System.err.println("Got an exception! ");
				      System.err.println(e.getMessage());
				    }
				 return returnFlag;
	}

	@Override
	public String exportToCsvBySpeciesName(String speciesName) {
		//Setup array list of speciesnames
		String returnFlag = "Failed to created CSV file...Please try again later";
		List<Imageidentity> speciesRecords = new ArrayList<Imageidentity>();
		 try
		    {
		     
		      // create the java statement
		      Statement st = connection.createStatement();
		      PreparedStatement query = (PreparedStatement) connection.prepareStatement("SELECT * FROM imageidentity where species LIKE?");
		      String speciesNameParameter = '%'+speciesName+'%';
		      	query.setString(1, speciesNameParameter);
		      				
		      // execute the query, and get a java resultset
		      ResultSet rs = query.executeQuery();
		       
		      // iterate through the java resultset
		      while (rs.next())
		      {
		    	  //species[index] = rs.getString("species");
		    	  Imageidentity speciesRecord = new Imageidentity();
		    	 // index++;
		    	  speciesRecord.setRecordId(rs.getLong("recordId"));
		    	  speciesRecord.setImageid(rs.getString("imageId"));
		    	  speciesRecord.setBehavior(rs.getString("behavior"));
		    	  speciesRecord.setNumber(rs.getInt("number"));
		    	  speciesRecord.setPose(rs.getString("pose"));
		    	  speciesRecord.setChildren(rs.getString("children"));
		    	  speciesRecord.setScale(rs.getString("scale"));
		    	  speciesRecord.setSpecies(rs.getString("species"));
		    	  speciesRecord.setColor(rs.getString("color"));
		    	  speciesRecord.setStudyId(rs.getString("studyId"));
		    	  
		    	  speciesRecords.add(speciesRecord);
		      }
		      st.close();
		     //check if records added to the Arraylist successfully
		      if(speciesRecords.size() > 0){
		    //setup report file name 
				String fileName = System.getProperty("user.home")+"/SpeciesReport.csv";
				SpeciesCsvReport.writeCsvFile(fileName, speciesRecords);
				returnFlag ="SpeciesReport.csv file was created successfully and found in your home directory";
		      }
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		 return returnFlag;

	}
}