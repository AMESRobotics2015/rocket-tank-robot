package org.usfirst.frc.team3243.robot;

import edu.wpi.first.wpilibj.*;

public class InputControl {
	private Joystick stick;
	public InputControl(int stickPin) {
		stick = new Joystick(stickPin);
	}
	
	public double getAxis(int axis){
		return stick.getRawAxis(axis);
	}
	
	public boolean getButton(int button) {
		return stick.getRawButton(button);
	}
}
