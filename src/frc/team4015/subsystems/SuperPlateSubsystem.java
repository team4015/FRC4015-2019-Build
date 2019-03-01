package frc.team4015.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4015.OI;
import frc.team4015.RobotMap;

public class SuperPlateSubsystem extends Subsystem {

	//Solenoid deployPiston = new Solenoid(RobotMap.PLATE_PISTON_DEPLOY);
	//Solenoid gay = new Solenoid(3);
	//Solenoid straight = new Solenoid(5);
	//Solenoid clawPiston = new Solenoid(RobotMap.PLATE_PISTON_CLAW);
	
	
	
	SolenoidPair plateControl = new SolenoidPair(new Solenoid(RobotMap.PLATE_PISTON_DEPLOY), new Solenoid(RobotMap.PLATE_PISTON_RETRACT));
	SolenoidPair clawControl = new SolenoidPair(new Solenoid(RobotMap.CLAW_PISTON_DEPLOY), new Solenoid(RobotMap.CLAW_PISTON_RETRACT));
	
	boolean plateExtended, clawExtended = false;
	
	public void readyForInput() {
		if (OI.XBoxControllerMisc.getBButtonPressed()) { // OI.JoyStickLeft.getRawButtonPressed(3) || 
			plateControl.toggle();
			plateExtended = !plateExtended;
		}
		if (OI.XBoxControllerMisc.getAButtonPressed()) { // OI.JoyStickLeft.getRawButtonPressed(4) || 
			clawControl.toggle();
			clawExtended = !clawExtended;
		}
	}
	
	public boolean getClawExtended() {return clawExtended;}
	public boolean getPlateExtended() {return plateExtended;}
	
	

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
