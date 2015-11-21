package org.usfirst.frc.team3243.robot;

import edu.wpi.first.wpilibj.*;

public class PneumaticControl {
	private Compressor comp;
	private Solenoid cannon;
	private double fireDelay=0.2;
	private Timer fireTimer;
	private boolean set;
	
	public PneumaticControl(int cannonPin) {
		comp = new Compressor();
		cannon = new Solenoid(cannonPin);
		fireTimer = new Timer();
	}
	
	public void setCompressor(boolean set) {
		if (set) {
			comp.start();
			set = true;
		}
		else {
			comp.stop();
			set = false;
		}
	}
	public boolean getCompressorOn() {
		return set;
	}
	
	public void fire() {
		cannon.set(true);
		fireTimer.reset();
		fireTimer.start();
	}
	
	public void fireUntilEmpty(){
		cannon.set(true);
	}
	
	public void stopFire() {
		cannon.set(false);
	}
	
	public void checkCannon() {
		if (fireTimer.get() >= fireDelay) {
			fireTimer.reset();
			fireTimer.stop();
			cannon.set(false);
		}
	}
}
