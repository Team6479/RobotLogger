package loggers;

import java.util.Date;

import edu.wpi.first.wpilibj.DriverStation;

//this logs the status of the robot, such as batter and radio connection
public class StatusLogger extends Logger {

	//objects that are being logged, ds has access to most status
	private DriverStation ds;
	
	//override constructors
	public StatusLogger(String folderPath, Date date) {
		super(folderPath, LoggerType.STATUS, date);
		init();
	}
	//init objects that will be logged
	public void init() {
		ds = DriverStation.getInstance();
	}

	//log the status
	@Override
	public String log() {
		//get all of the statuses to log
		
		//connection
		boolean connectedToDS = ds.isDSAttached();
		
		//power
		double batteryVoltage = ds.getBatteryVoltage();
		boolean brownout = ds.isBrownedOut();
		
		//mode
		boolean disabled = ds.isDisabled();
		boolean teleop = ds.isOperatorControl();
		boolean autonomous = ds.isAutonomous();
		
		//controls
		String controls = "";
		for(int i = 0; i < DriverStation.kJoystickPorts; i++) {
			controls += String.format("[%s on port: %d]", ds.getJoystickName(i), i);
		}
	
		//format output
		String output = String.format("Connected to DS: %b%tBattery Level: .2%fV%tBrownout: %b%tDisabled: %b%t"
				+ "Autonomous: %b%tTeleop: %b%tControllers: %s", 
				connectedToDS, batteryVoltage, brownout, disabled, teleop, autonomous, controls);
		
		
		return output;
	}

}
