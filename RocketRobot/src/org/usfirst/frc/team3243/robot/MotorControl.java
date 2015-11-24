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
		xAxis = Math.min(Math.max(-1,ramp(xAxis)),1);
		yAxis = Math.min(Math.max(-1,ramp(yAxis)),1);
		xAxis *= turnCapFactor;
		yAxis *= runCapFactor;
		drive.arcadeDrive(yAxis,xAxis);
	}
	
	public void teleopAim(double xAxis,double yAxis) {
		
	}
	
	//Don't forget to cap with Math.min and/or Math.max if needed!
	public static double ramp(double input) {
		double rampedVal;
		rampedVal = Math.pow(4*input/3, 3);
		return rampedVal;
	}
}
