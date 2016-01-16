package org.usfirst.frc.team2506.robot;

import edu.wpi.first.wpilibj.*;

public class DoubleTankDrive {
	private RobotDrive frontDrive;
	private RobotDrive rearDrive;
	
	public DoubleTankDrive(int frontLeft, int rearLeft, int frontRight, int rearRight) {
		frontDrive = new RobotDrive(frontLeft, frontRight);
		rearDrive = new RobotDrive(rearLeft, rearRight);
	}
	
	public void Drive (Joystick joy) {
		Drive (joy.getRawAxis(0), joy.getRawAxis(7));
	}
	
	public void Drive (double x, double y) {
		frontDrive.tankDrive(x, y);
		rearDrive.tankDrive(x, y);
	}
}
