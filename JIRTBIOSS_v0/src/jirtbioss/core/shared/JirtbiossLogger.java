package jirtbioss.core.shared;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import jirtbioss.core.server.AdminServiceImpl;
import jirtbioss.core.server.LoginServiceImpl;

public class JirtbiossLogger {
	private FileHandler logFile;
	private SimpleFormatter formatter;
	private HashMap<String, String> loggingFolderPaths = DBUtility.getLoggingPaths();
	private String loggingType;
	
	private String LoggingLevel;
	

	//CONSTRUCTOR
	public JirtbiossLogger(String loggingType, String loggingLevel) {
		super();
		this.loggingType = loggingType;
		LoggingLevel = loggingLevel;
		String jirtBiossLogPath = null;
		if(this.loggingType.toUpperCase().equals("JIRTBIOSS")) {
			jirtBiossLogPath = this.loggingFolderPaths.get("jirtbiossLogPath")+"/JirtBioss.log";
		}else {
			jirtBiossLogPath = this.loggingFolderPaths.get("jirtbiossLogPath")+"/JirtBiossAuth.log";
		}
		
		try {
			this.logFile = new FileHandler(jirtBiossLogPath, 1024 * 1024, 1, true);
			this.formatter = new SimpleFormatter();
			this.logFile.setFormatter(formatter);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//GETTERS AND SETTERS
	public FileHandler getLogFile() {
		return logFile;
	}


	public void setLogFile(FileHandler logFile) {
		this.logFile = logFile;
	}


	public SimpleFormatter getFormatter() {
		return formatter;
	}


	public void setFormatter(SimpleFormatter formatter) {
		this.formatter = formatter;
	}
	
	public String getLoggingType() {
		return loggingType;
	}


	public void setLoggingType(String loggingType) {
		this.loggingType = loggingType;
	}


	public String getLoggingLevel() {
		return LoggingLevel;
	}


	public void setLoggingLevel(String loggingLevel) {
		LoggingLevel = loggingLevel;
	}

	//END OF GETTERS AND SETTERS

	//GENERIC METHOD/INTERFACE  
	public void performLoggig(String className, String methodName, String message) {
		Logger LOGGER = Logger.getLogger(AdminServiceImpl.class.getName());
		message = "Logging class: "+className.toUpperCase()+", " +" Logging method: "+methodName+"\n"+message;
		LOGGER.addHandler(logFile);
		//setting logging level based on the user defined properties
	 	if(this.LoggingLevel.toUpperCase().equals("DEBUG")) {
	 		LOGGER.setLevel(Level.ALL);
	 	}else if (this.LoggingLevel.toUpperCase().equals("WARN")) {
	 		LOGGER.setLevel(Level.WARNING);
	 	}else {
	 		LOGGER.setLevel(Level.INFO);  //DEFAULT LOGGING
	 	}
		
	 	LOGGER.info(message);
	 	logFile.close();
	}

	//END OF LOGGING FOR ADMIN CLASS
	
	//LOGGIN FOR AUTHENTICATION CLASS
	public void setLoggingForAuth(String message) throws SecurityException, IOException {
		this.logFile = new FileHandler("Logs/JirtBiossLogin.log", 1024 * 1024, 1, true);
			
		Logger LOGGER = Logger.getLogger(LoginServiceImpl.class.getName());
	 	LOGGER.addHandler(logFile);
	 	LOGGER.setLevel(Level.INFO);
	 	LOGGER.info(message);
	 	logFile.close();
	 	//END OF LOGGING
	}

}
