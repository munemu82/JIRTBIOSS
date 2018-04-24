package jirtbioss.core.client.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpeciesCsvReport {
	//Delimiter used in CSV file
		private static final String COMMA_DELIMITER = ",";
		private static final String NEW_LINE_SEPARATOR = "\n";
		//CSV file header
		private static final String FILE_HEADER = "RecordID,ImageID,Behavior,No.OfSepcies,Pose,Children(y/n),Scale,SpeciesName, colour, studyId";
		public static void writeCsvFile(String fileName, List<Imageidentity> speciesList) {
			FileWriter fileWriter = null;
			
			try {
				fileWriter = new FileWriter(fileName);

				//Write the CSV file header
				fileWriter.append(FILE_HEADER.toString());
				
				//Add a new line separator after the header
				fileWriter.append(NEW_LINE_SEPARATOR);
				
				//Write a new species list to the CSV file
				for (Imageidentity imageIdentity : speciesList) {
					fileWriter.append(String.valueOf(imageIdentity.getRecordId()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(imageIdentity.getImageid());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(imageIdentity.getBehavior());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(imageIdentity.getNumber()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(imageIdentity.getPose());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(imageIdentity.getChildren());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(imageIdentity.getScale());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(imageIdentity.getSpecies());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(imageIdentity.getColor());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(imageIdentity.getStudyId());
					fileWriter.append(NEW_LINE_SEPARATOR);
				}

				
				
				System.out.println("CSV file was created successfully !!!");
				
			} catch (Exception e) {
				//will modify this to be writing to a log file
				System.out.println("Error in CsvFileWriter !!!");
				e.printStackTrace();
			} finally {
				
				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					//will modify this to be writing to a log file
					System.out.println("Error while flushing/closing fileWriter !!!");
	                e.printStackTrace();
				}
				
			}
			
		}
}
