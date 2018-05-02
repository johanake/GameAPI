package app.logger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*
 * Serverlogger handles two seperate log functions to log server events and errors
 * 
 * 
 * @author Johan Åkesson
 *	2018/04/25
 */

public class ServerLogger {
	private static String userDir = System.getProperty("user.dir");
	private static Logger eventLog = Logger.getLogger("eventLog");
	private static Logger errorLog = Logger.getLogger("errorLog");
	private static FileHandler fhEventLog;
	private static FileHandler fhErrorLog;
	private static SimpleFormatter sfLog;
	
	private static ServerLogger instance = null;  
	
	public ServerLogger(){

	}
	
	 /**
	  * Returns a singleton instance of ServerLogger or create an instance if ServerLogger has not been created
	 * @return instance of ServerLogger
	 */
	public static ServerLogger getInstance() {  
		    if(instance == null) {  
		    	setup();  
		        instance = new ServerLogger ();  
		    }  
		    return instance;  
		 }  

	 /**
	 * Sets up the logger and creates the needed folders,
	 * 
	 */
	private static void setup(){
		 sfLog = new SimpleFormatter();
			
			try {
				File logDir = new File(userDir + "/logs");
				File eventDir = new File(userDir +"/logs/eventlog/");
				File errorDir = new File(userDir +"/logs/errorlog/");
				if(!logDir.exists() || !eventDir.exists() || !errorDir.exists()){
					logDir.mkdir();
					eventDir.mkdir();
					errorDir.mkdir();
				}
				
				fhEventLog = new FileHandler(userDir + "/logs/eventlog/eventlog" + new SimpleDateFormat("ddMMYY").format(new Date()) + ".log", true);
				fhEventLog.setFormatter(sfLog);
				eventLog.addHandler(fhEventLog);
				eventLog.setUseParentHandlers(false);	
		
				
				fhErrorLog = new FileHandler(userDir + "/logs/errorlog/errorlog" + new SimpleDateFormat("ddMMYY").format(new Date()) + ".log", true);
				fhErrorLog.setFormatter(sfLog);
				errorLog.addHandler(fhErrorLog);
				errorLog.setUseParentHandlers(false);				
				
			} catch (SecurityException | IOException e) {
				e.printStackTrace();
				
			}
			
	 }
	
	/**
	 * Saves message to the event log
	 * @param msg message to be saved
	 */
	public void eventLog(String msg){
		eventLog.info(msg);
	
		
	}
	
	/**
	 * Saves message to the error log
	 * @param msg message to be saved
	 */
	public void errorLog(String msg){
		errorLog.info(msg);
		
	}
	
	


}
