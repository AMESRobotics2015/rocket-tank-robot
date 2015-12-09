package org.usfirst.frc.team3243.robot;

import edu.wpi.first.wpilibj.*;
import java.util.List;
import java.util.ArrayList;

public class InputControl {
	private Joystick stick;
	private List<Integer> prevPressedButtons;
	public InputControl(int stickPin) {
		stick = new Joystick(stickPin);
		prevPressedButtons = new ArrayList<Integer>();
	}
	
	public double getAxis(int axis){
		return stick.getRawAxis(axis);
	}
	
	public boolean getButton(int button) {
		boolean pressed = stick.getRawButton(button);
		return pressed;
	}
	
	public void updateButtons() {
		for (int button=1;button<=stick.getButtonCount();button++) {
			boolean pressed = stick.getRawButton(button);
			if (pressed&&!prevPressedButtons.contains(button)){
				prevPressedButtons.add(button);
			}
			if (!pressed&&prevPressedButtons.contains(button)){
				prevPressedButtons.remove(prevPressedButtons.indexOf(button));
			}
		}
	}
	
	public boolean getButtonTapped(int button) {
		return (!prevPressedButtons.contains(button))&&getButton(button);
	}
}
