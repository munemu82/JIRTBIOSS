package jirtbioss.core.client.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ashraf_sarhan
 *
 */
public class SpeciesCsvReader {
	
	//Delimiter used in CSV file
	//private static final String COMMA_DELIMITER = ",";
	private static final String COMMA_DELIMITER = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
	
	//Student attributes index
	private static final int SPECIES_ID_IDX = 0;
	private static final int SPECIES_NAME_IDX = 1;
	private static final int SPECIES_DESC_IDX = 2;
	private static final int SPECIES_POSE_IDX = 3;
	private static final int SPECIES_COLOUR_IDX = 4;
	private static final int SPECIES_SCALE_IDX = 5;
	private static final int SPECIES_LOOKSLIKE_1 = 6; 
	private static final int SPECIES_LOOKSLIKE_2 = 7; 
	private static final int SPECIES_LOOKSLIKE_3 = 8; 
	
	public static List<Species> readCsvFile(String fileName) {

		BufferedReader fileReader = null;
		//Create a new list of species to be filled by CSV file data 
    	List<Species> listOfSpecies = new ArrayList<Species>();
        try {
        	
            String line = "";
            
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));
            
            //Read the CSV file header to skip it
            fileReader.readLine();
            
            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                	//Create a new student object and fill his  data
                	Species species = new Species(tokens[SPECIES_ID_IDX], tokens[SPECIES_NAME_IDX], tokens[SPECIES_DESC_IDX], tokens[SPECIES_POSE_IDX], tokens[SPECIES_COLOUR_IDX], tokens[SPECIES_SCALE_IDX], tokens[SPECIES_LOOKSLIKE_1], tokens[SPECIES_LOOKSLIKE_2], tokens[SPECIES_LOOKSLIKE_3]);
                	listOfSpecies.add(species);
				}
            }
           
        } 
        catch (Exception e) {
        	System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
            	System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
		return listOfSpecies;

	}

}
