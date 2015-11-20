package org.usfirst.frc.team3243.robot;

import edu.wpi.first.wpilibj.*;

public class MotorControl {
	private Victor leftMotor,rightMotor;
	private RobotDrive drive;
	private double
		runCapFactor = 0.6, //Forward and Backward
		turnCapFactor = 0.7; //Turning
	private boolean 
		turboRun = false,
		turboTurn = false;
	
	public MotorControl(int pinLeft,int pinRight) {
		leftMotor = new Victor(pinLeft);
		rightMotor = new Victor(pinRight);
		drive = new RobotDrive(leftMotor,rightMotor);
	}
	
	public void teleopDrive(double xAxis,double yAxis,boolean turnToggle,boolean runToggle) {
		if (turnToggle) {
			turboTurn = !turboTurn;
		}
		if (runToggle) {
			turboRun = !turboRun;
		}
		if (!turboTurn) {
			xAxis *= turnCapFactor;
		}
		if (!turboRun) {
			yAxis *= runCapFactor;
		}
		drive.arcadeDrive(xAxis,yAxis);
	}
	
	public void setTurboRun(boolean turboRun) {
		this.turboRun = turboRun;
	}
	
	public void setTurboTurn(boolean turboTurn) {
		this.turboTurn = turboTurn;
	}
	
	public boolean getTurboRun() {
		return this.turboRun;
	}
	
	public boolean getTurboTurn() {
		return this.turboTurn;
	}
}
