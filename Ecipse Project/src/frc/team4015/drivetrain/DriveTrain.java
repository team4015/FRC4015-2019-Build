package frc.team4015.drivetrain;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team4015.OI;
import frc.team4015.RobotMap;

public class DriveTrain {

    private static final double THROTTLE_SMOOTHING_FACTOR = 0.05;
    private static final double TURN_SMOOTHING_FACTOR = 0.05;

    private PWMTalonSRX leftTalonFront = new PWMTalonSRX(RobotMap.LEFT_MOTOR_FRONT);
    private PWMTalonSRX leftTalonRear = new PWMTalonSRX(RobotMap.LEFT_MOTOR_REAR);
    private PWMTalonSRX rightTalonFront = new PWMTalonSRX(RobotMap.RIGHT_MOTOR_FRONT);
    private PWMTalonSRX rightTalonRear = new PWMTalonSRX(RobotMap.RIGHT_MOTOR_REAR);
    private SpeedControllerGroup leftSide = new SpeedControllerGroup(leftTalonFront, leftTalonRear);
    private SpeedControllerGroup rightSide = new SpeedControllerGroup(rightTalonFront, rightTalonRear);
    private DifferentialDrive robotDrive = new DifferentialDrive(leftSide, rightSide);
    private final double SLOWING_CONSTANT = 2;
    private DriveMode driveMode = DriveMode.FAST;
    
    enum DriveMode {
    	FAST,
    	MEDIUM,
    	SLOW
    }

    public void drive() {
    	
    	if(OI.JoyStickRight.getRawButtonPressed(3)) {
    		switch(driveMode) {
    		case MEDIUM:
    			driveMode = DriveMode.FAST;
    			break;
    		case SLOW:
    			driveMode = DriveMode.MEDIUM;
    			break;
			default:
				break;
    		}
    	}
    	if(OI.JoyStickRight.getRawButtonPressed(4)) {
    		switch(driveMode) {
    		case MEDIUM:
    			driveMode = DriveMode.SLOW;
    			break;
    		case FAST:
    			driveMode = DriveMode.MEDIUM;
    			break;
			default:
				break;
    		}
    	}
    	
    	if(OI.JoyStickRight.getRawButtonPressed(4)) {
    		if (driveMode.equals(DriveMode.SLOW)) {
    			driveMode = DriveMode.FAST;
    		}
    		else {
    			driveMode = DriveMode.SLOW;
    		}
    	}
    	
    	double slowMod;
    	switch(driveMode) {
    		case FAST:
    			slowMod = 1;
    			break;
    		case MEDIUM:
    			slowMod = Math.sqrt(SLOWING_CONSTANT);
    			break;
    		case SLOW:
    			slowMod = SLOWING_CONSTANT;
    			break;
    		default:
    			slowMod = 1;
    	}
    	
    	robotDrive.tankDrive(OI.JoyStickLeft.getY() / slowMod, OI.JoyStickRight.getY() / slowMod);
    }

}
