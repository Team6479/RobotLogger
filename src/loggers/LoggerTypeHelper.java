package loggers;

public final class LoggerTypeHelper {
	
	public static String format(LoggerType type) {
		switch(type) {
		case STATUS:
			return "StatusLog";
		case WARNING:
			return "WarningLog";
		case ERROR:
			return "ErrorLog";
		default:
			return "Log";
		}
	}
}

