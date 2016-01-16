package org.usfirst.frc.team2506.robot;

import edu.wpi.first.wpilibj.*;
import java.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RoboLib {
	private DoubleTankDrive driveTrain;
	private Joystick joystick;
	private Map<Integer, Solenoid[]> solenoids = new HashMap<Integer, Solenoid[]>();
	
	public RoboLib (int joystickChan) {
		joystick = new Joystick(joystickChan);
	}
	
	public RoboLib (Joystick joystick) {
		this.joystick = joystick;
	}
	
	public void setupDriveTrain (int frontLeft, int rearLeft, int frontRight, int rearRight) {
		driveTrain = new DoubleTankDrive(frontLeft, rearLeft, frontRight, rearRight);
	}
	
	public void mapPistonToButton (int button, int solenoidOn, int solenoidOff) {
		Solenoid[] sols = new Solenoid[2];
		sols[0] = new Solenoid(solenoidOn);
		sols[1] = new Solenoid(solenoidOff);
		
		solenoids.put(button, sols);
	}
	
	public void swapPiston (Solenoid solenoidOn, Solenoid solenoidOff) {
		if (solenoidOn.get() && !solenoidOff.get()) {
			solenoidOn.set(false);
			solenoidOff.set(true);
		} else if (solenoidOff.get() && !solenoidOn.get()) {
			solenoidOn.set(true);
			solenoidOff.set(false);
		}
	}
	
	public void main () {
		driveTrain.Drive(joystick);
		for (Entry<Integer, Solenoid[]> entry : solenoids.entrySet())
			if (joystick.getRawButton(entry.getKey()))
				swapPiston(entry.getValue()[0], entry.getValue()[1]);
	}
}
