
package org.usfirst.frc.team3243.robot;

import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	private MotorControl motor;
	private InputControl input;
	private PneumaticControl air;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	motor = new MotorControl(RobotMap.PIN_SPEED_LEFT,RobotMap.PIN_SPEED_RIGHT);
    	input = new InputControl(RobotMap.PIN_JOY);
    	if (RobotMap.PNEUMATIC_ON) {
    		air = new PneumaticControl(RobotMap.PIN_CANNON);
    	}
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	anyPeriodic();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	motor.teleopDrive(
    			input.getAxis(RobotMap.LEFT_X),
    			input.getAxis(RobotMap.LEFT_Y)
    			);
    	if (RobotMap.PNEUMATIC_ON) {
	    	if (input.getButton(RobotMap.FIRE)) {
	    		air.fire();
	    	}
	    	if (input.getButton(RobotMap.COMP_TOGGLE)) {
	    		air.setCompressor(!air.getCompressorOn());
	    	}
	    	if (input.getButton(RobotMap.FIRE_EMPTY)) {
	    		air.fireUntilEmpty();
	    	}
	    	if (input.getButton(RobotMap.FIRE_STOP)) {
	    		air.stopFire();
	    	}
    	}
    	anyPeriodic();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	anyPeriodic();
    }
    
    /**
     * This function is called periodically no matter what operation mode
     */
    public void anyPeriodic() {
    	if (RobotMap.PNEUMATIC_ON) {
    		air.checkCannon();
    	}
    }
}
