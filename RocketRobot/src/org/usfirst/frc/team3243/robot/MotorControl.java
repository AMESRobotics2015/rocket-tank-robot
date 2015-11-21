package org.usfirst.frc.team3243.robot;

import edu.wpi.first.wpilibj.*;

public class MotorControl {
	private Victor leftMotor,rightMotor;
	private RobotDrive drive;
	private double
		runCapFactor = 0.5, //Forward and Backward
		turnCapFactor = 0.5; //Turning
	
	public MotorControl(int pinLeft,int pinRight) {
		leftMotor = new Victor(pinLeft);
		rightMotor = new Victor(pinRight);
		drive = new RobotDrive(leftMotor,rightMotor);
	}
	
	public void teleopDrive(double xAxis,double yAxis) {
		xAxis = ramp(xAxis);
		yAxis = ramp(yAxis);
		xAxis *= turnCapFactor;
		yAxis *= runCapFactor;
		drive.arcadeDrive(yAxis,xAxis);
	}
	
	public static double ramp(double input) {
		double rampedVal;
		rampedVal = Math.pow(input, 3);
		return rampedVal;
	}
}
