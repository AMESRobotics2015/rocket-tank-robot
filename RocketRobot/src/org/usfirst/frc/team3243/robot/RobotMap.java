package org.usfirst.frc.team3243.robot;

public class RobotMap {
	public static final int 
		PIN_SPEED_LEFT=1,//Left Motor
		PIN_SPEED_RIGHT=2,//Right Motor
		PIN_SPEED_TURRET=3,//Turret Motor (Up/down)
		PIN_SPEED_PIVOT=4,//Pivot Motor (Left/right)
		PIN_SWITCH_TURRET=1,//Turret Limit Switch
		PIN_SWITCH_PIVOT=2,//Pivot Limit Switch
		PIN_JOY=0,
		PIN_CANNON=0;
	public static final int 
		LEFT_X=0,
		LEFT_Y=1,
		RIGHT_X=2,
		RIGHT_Y=3;
	public static final int
		TURN_TOGGLE=5,
		RUN_TOGGLE=6,
		COMP_TOGGLE=2,
		TURRET_MANUAL=3,
		PRESET_FRONT=4,
		PRESET_MID=9,
		PRESET_BACK=10,
		FIRE=1,
		FIRE_EMPTY=3;
	public static final int
		CLICKS_FRONT=3,
		CLICKS_MID=5,
		CLICKS_BACK=7;
	public static final double
		TURRET_RATE = 0.1,//Speed per click
		PIVOT_RATE = 0.1;
	public static final boolean
		PNEUMATIC_ON=true;//compressor and cannon can only be used when this is true.
}