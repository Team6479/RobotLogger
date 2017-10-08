package loggers;

import java.util.Date;

public class ErrorLogger extends Logger {

	// override constructors
	public ErrorLogger(String folderPath, Date date) {
		super(folderPath, LoggerType.STATUS, date);
		init();
	}

	// init objects that will be logged
	public void init() {
		// intercept HAL.sendError
		// get emergency stop
	}

	// log the status
	@Override
	public String log() {
		// get all of the errors

		// format output
		String output = String.format("");

		return output;
	}
}
