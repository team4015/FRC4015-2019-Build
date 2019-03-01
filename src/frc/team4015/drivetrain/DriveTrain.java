package frc.team4015.drivetrain;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team4015.OI;
import frc.team4015.RobotMap;

public class DriveTrain extends Subsystem {

    private PWMTalonSRX leftTalonFront = new PWMTalonSRX(RobotMap.LEFT_MOTOR_FRONT);
    private PWMTalonSRX leftTalonRear = new PWMTalonSRX(RobotMap.LEFT_MOTOR_REAR);
    private PWMTalonSRX rightTalonFront = new PWMTalonSRX(RobotMap.RIGHT_MOTOR_FRONT);
    private PWMTalonSRX rightTalonRear = new PWMTalonSRX(RobotMap.RIGHT_MOTOR_REAR);
    private SpeedControllerGroup leftSide = new SpeedControllerGroup(leftTalonFront, leftTalonRear);
    private SpeedControllerGroup rightSide = new SpeedControllerGroup(rightTalonFront, rightTalonRear);
    private DifferentialDrive robotDrive = new DifferentialDrive(leftSide, rightSide);
    private final double SLOWING_CONSTANT = 2;
    private boolean safe = false;
    private DriveMode driveMode = DriveMode.FAST;
    
    enum DriveMode {
    	FAST,
    	MEDIUM,
    	SLOW
    }
    
    public void kill() {
    	safe = true;
    }
    
    public void unkill() {
    	safe = false;
    }

    public void drive() {
    	if (OI.XBoxController.getXButtonPressed()) { // OI.JoyStickRight.getRawButtonPressed(5) || 
    		unkill();
    	}
    	if(OI.XBoxController.getAButtonPressed()) { // A Slows OI.JoyStickRight.getRawButtonPressed(3) || 
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
    	if(OI.XBoxController.getBButtonPressed()) { // B speeds OI.JoyStickRight.getRawButtonPressed(4) || 
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
    	if (!safe) {
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
    	//robotDrive.arcadeDrive(-OI.JoyStickRight.getY() / slowMod, OI.JoyStickRight.getX() / slowMod); // X IS INVERTED!!!!!!!!
    	//robotDrive.arcadeDrive((OI.XBoxController.getTriggerAxis(Hand.kRight) - OI.XBoxController.getTriggerAxis(Hand.kLeft)) / slowMod, OI.XBoxController.getX(Hand.kLeft) / slowMod);
    	robotDrive.arcadeDrive((OI.XBoxController.getY(Hand.kLeft)) / slowMod, OI.XBoxController.getX(Hand.kLeft) / slowMod);
    	
    	//robotDrive.tankDrive(OI.JoyStickLeft.getY() / slowMod, OI.JoyStickRight.getY() / slowMod);
    	}
    	else {
    		robotDrive.stopMotor();
    	}
    }

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
