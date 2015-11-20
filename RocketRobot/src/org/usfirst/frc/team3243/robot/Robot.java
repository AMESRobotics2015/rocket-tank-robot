
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
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	motor = new MotorControl(RobotMap.PIN_SPEED_LEFT,RobotMap.PIN_SPEED_RIGHT);
    	input = new InputControl(RobotMap.PIN_JOY);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	motor.teleopDrive(
    			input.getAxis(RobotMap.LEFT_X),
    			input.getAxis(RobotMap.LEFT_Y),
    			input.getButton(RobotMap.TURN_TOGGLE),
    			input.getButton(RobotMap.RUN_TOGGLE)
    			);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
