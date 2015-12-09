package org.usfirst.frc.team3243.robot;

import edu.wpi.first.wpilibj.*;

public class PneumaticControl {
	private Compressor comp;
	private Solenoid cannon;
	private double fireDelay=0.2;
	private Timer fireTimer;
	private boolean set,emptyFire;
	
	public PneumaticControl(int cannonPin) {
		comp = new Compressor();
		cannon = new Solenoid(cannonPin);
		fireTimer = new Timer();
	}
	
	public void setCompressor(boolean set) {
		this.set = set;
		if (set) {
			comp.start();
		}
		else {
			comp.stop();
		}
	}
	public boolean getCompressorOn() {
		return set;
	}
	
	public boolean getCharged(){
		return comp.getPressureSwitchValue();
	}
	
	public void fire() {
		cannon.set(true);
		emptyFire = false;
		fireTimer.reset();
		fireTimer.start();
	}
	
	public void fireUntilEmpty(){
		cannon.set(!emptyFire);
		emptyFire = !emptyFire;
	}
	
	public void checkCannon() {
		if (fireTimer.get() >= fireDelay) {
			fireTimer.reset();
			fireTimer.stop();
			if (!emptyFire) {
				cannon.set(false);
			}
		}
	}
}
