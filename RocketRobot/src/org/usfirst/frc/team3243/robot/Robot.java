
package org.usfirst.frc.team3243.robot;

import edu.wpi.first.wpilibj.*;
import org.usfirst.frc.team3243.robot.instruction.*;
import java.util.Stack;

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
	private DigitalInput doTurn;
	private Stack<Instruction> autonomousRoutine;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	motor = new MotorControl(RobotMap.PIN_SPEED_LEFT,RobotMap.PIN_SPEED_RIGHT,
    			RobotMap.PIN_SPEED_TURRET,RobotMap.PIN_SWITCH_TURRET,RobotMap.PIN_SPEED_PIVOT,RobotMap.PIN_SWITCH_PIVOT,
    			RobotMap.TURRET_RATE,RobotMap.PIVOT_RATE);
    	input = new InputControl(RobotMap.PIN_JOY);
    	autonomousRoutine = new Stack<Instruction>();
    	if (RobotMap.PNEUMATIC_ON) {
    		air = new PneumaticControl(RobotMap.PIN_CANNON);
    		air.setCompressor(true);
    	}
    	//SET UP AUTONOMOUS ROUTINE
    	//Move out to circles
    	//Turn to face hoop
    	//Move to appropriate circle
    	//Aim at target
    	//Fire
    	//Move back to center circle
    	//Turn to face reload station
    	//Return to reload station
    	//Wait for reload or stop program
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	while (autonomousRoutine.size()>0){
    		autonomousRoutine.peek().execute();
    		if (autonomousRoutine.peek().finished()) {
    			autonomousRoutine.pop();
    		}
    	}
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
    	motor.teleopAim(
    			input.getAxis(RobotMap.RIGHT_X),
    			input.getAxis(RobotMap.RIGHT_Y)
    			);
    	if (input.getButton(RobotMap.TURRET_MANUAL)) {
    		motor.abortSetAim();//Immediately set to manual aim.
    	}
    	/*if (input.getButton(RobotMap.PRESET_FRONT)) {
    		motor.setTurretPosition(RobotMap.CLICKS_FRONT);
    	}
    	if (input.getButton(RobotMap.PRESET_MID)) {
    		motor.setTurretPosition(RobotMap.CLICKS_MID);
    	}
    	if (input.getButton(RobotMap.PRESET_BACK)) {
    		motor.setTurretPosition(RobotMap.CLICKS_BACK);
    	}*/
    	if (RobotMap.PNEUMATIC_ON) {
	    	if (input.getButton(RobotMap.FIRE)) {
	    		air.fire();
	    	}
	    	/*if (input.getButtonTapped(RobotMap.COMP_TOGGLE)) {
	    		boolean wasOn = air.getCompressorOn();
	    		air.setCompressor(!wasOn);
	    	}*/
	    	air.setCompressor(true);
	    	/*if (input.getButton(RobotMap.FIRE_EMPTY)) {
	    		air.fireUntilEmpty();
	    	}*/
    	}
    	motor.updateTurretPivot();
    	input.updateButtons();
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
    	//Turn off cannon once its timer goes off
    	if (RobotMap.PNEUMATIC_ON) {
    		air.checkCannon();
    	}
    }
}
