package robotLogger;

import java.util.Date;

import loggers.*;

//interface class for logging util
public class RobotLogger {
	
	//folder where logs are kept
	public static final String LOG_FOLDER = "/home/lvuser/RobotLogs";
	
	
	//singleton instance
	private static final RobotLogger instance = new RobotLogger();
	//access logger
	public RobotLogger getInstance() {
		return instance;
	}
	
	//init is private so no one can touch, keep your paws off
	private RobotLogger() {
		//create the start Date
		Date date = new Date();
		
		//make the logs
		statLog = new StatusLogger(LOG_FOLDER, date);
	}
	
	//logs status of robot
	private StatusLogger statLog;
	
	
}
