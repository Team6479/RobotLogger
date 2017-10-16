package loggers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.wpi.first.wpilibj.Utility;

//abstarct logger that all sub loggers inherit from
public abstract class Logger {

	// start date and time
	private Date start;
	// thread to write log
	private Thread logger;
	// print writer that will write the log files
	private PrintWriter write;
	// filepath for log
	private String folderPath;
	// logger type
	private LoggerType type;

	// initialize with time parameter
	public Logger(String folderPath, LoggerType type, Date date) {
		init(folderPath, type, date);
	}

	// generic init
	private void init(String folderPath, LoggerType type, Date date) {
		// init time
		start = date;

		// init type
		this.type = type;

		//date formatter
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH~mm~ss");
		//format date
		String formattedDate = formatter.format(start);

		// init printwriter
		try {
			write = new PrintWriter(folderPath + "/" + LoggerTypeHelper.format(type) + "_" + formattedDate);
		}
		catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}

		// logger thread
		logger = new Thread(() -> {
			// while the thread is still running
			while (!Thread.interrupted()) {
				// write the log
				writeLog();

				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		});
		// make killable by jvm
		logger.setDaemon(true);
		// start thread
		logger.start();
	}

	// gets what is to be logged and logs it
	private void writeLog() {
		// get the timestamp, convert to seconds
		double currentTime = Utility.getFPGATime() / 1000;
		// get the text to log
		String textToLog = log();

		// formatted log
		String newEntry = String.format("Time .2%f:%t%s", currentTime, textToLog);

		// write it to file it
		write.print(newEntry);
	}

	// overriden to get log text
	public abstract String log();

	// func to close log
	public void close() {
		write.close();
	}
}
