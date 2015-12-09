package org.usfirst.frc.team3243.robot;

import edu.wpi.first.wpilibj.*;

public class MotorControl {
	private SpeedController leftMotor,rightMotor;
	//Turret controls vertical aim, pivot controls horizontal aim.
	private SpeedController turret,pivot;
	private DigitalInput turretSwitch,pivotSwitch;//Limit Switches
	private RobotDrive drive;
	private double
		runCapFactor = 0.8, //Forward and Backward
		turnCapFactor = 1.0, //Turning
		turretCapFactor = 0.7, //Vertical Aim
		pivotCapFactor = 0.5, //Horizontal aim
		turretRate = 0.1,
		pivotRate = 0.1;
	private boolean manualTurret = true,manualPivot = true;
	private int tclicksLeft = 0,turretPhase = 0;
	private int pclicksLeft = 2,pivotPhase = 2;
	private Counter turretCounter,pivotCounter;
	private Timer turretTimer,pivotTimer;
	
	public MotorControl(int pinLeft,int pinRight,int pinTurret,int pinTSwitch,int pinPivot,int pinPSwitch,
			double turretRate,double pivotRate) {
		leftMotor = new Victor(pinLeft);
		rightMotor = new Victor(pinRight);
		turret = new VictorSP(pinTurret);
		pivot = new VictorSP(pinPivot);
		turretSwitch = new DigitalInput(pinTSwitch);
		pivotSwitch = new DigitalInput(pinPSwitch);
		drive = new RobotDrive(leftMotor,rightMotor);
		turretCounter = new Counter(turretSwitch);
		pivotCounter = new Counter(pivotSwitch);
		turretTimer = new Timer();
		pivotTimer = new Timer();
		this.pivotRate = pivotRate;
		this.turretRate = turretRate;
	}
	
	public void teleopDrive(double xAxis,double yAxis) {
		xAxis *= turnCapFactor;
		yAxis *= runCapFactor;
		xAxis = ramp(xAxis);
		yAxis = ramp(yAxis);
		drive.arcadeDrive(yAxis,xAxis);
	}
	
	public void autonomousDrive(double speed,double turn) {
		drive.drive(speed, turn);
	}
	
	public void setTurretPosition(int clicks) {
		turret.set(-0.3);
		turretPhase = 0;
		tclicksLeft = clicks;
		turretCounter.reset();
		manualTurret = false;
		turretTimer.reset();
	}
	
	public void setPivotPosition(int clicks) {
		pivot.set(-0.3);
		turretPhase = 0;
		pclicksLeft = clicks;
		pivotCounter.reset();
		manualPivot = false;
		pivotTimer.reset();
	}
	
	public void updateTurretPivot() {
		if (!manualTurret) {
			if (turretPhase == 0) {
				if (turretTimer.get() > 1.0) {
					turretPhase = 1;
					turret.set(0);
				}
			}
			else if (turretPhase == 1) {
				if (turretCounter.get()<tclicksLeft) {
					turret.set(turretRate);
				}
				else {
					turretPhase = 2;
				}
			}
		}
		if (!manualPivot) {
			if (pivotPhase == 0) {
				if (pivotTimer.get() > 1.0) {
					pivotPhase = 1;
					pivot.set(0);
				}
			}
			else if (pivotPhase == 1) {
				if (pivotCounter.get()<pclicksLeft) {
					pivot.set(pivotRate);
				}
				else {
					pivotPhase = 2;
				}
			}
		}
	}
	
	public boolean atSetAimPosition() {
		return pivotPhase == 2 && turretPhase == 2;
	}
	
	public void abortSetAim() {
		turret.set(0);
		pivot.set(0);
		manualPivot = true;
		manualTurret = true;
	}
	
	public void teleopAim(double xAxis,double yAxis) {
		//if (manualTurret) {
			turret.set(ramp(yAxis*turretCapFactor));
		//}
		//if (manualPivot) {
			pivot.set(ramp(xAxis*pivotCapFactor));
		//}
	}
	
	public static double ramp(double input) {
		input = Math.min(Math.max(-1, input),1);
		double rampedVal;
		rampedVal = Math.pow(input,1);
		rampedVal = Math.min(Math.max(-1,rampedVal),1);
		return rampedVal;
	}
}
