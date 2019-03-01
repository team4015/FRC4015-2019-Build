package frc.team4015.subsystems;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4015.OI;
import frc.team4015.RobotMap;

public class CargoSubsystem extends Subsystem {

	PWMTalonSRX cargoTalon = new PWMTalonSRX(RobotMap.CARGO_INTAKE);
	PWMTalonSRX cargoTalon2 = new PWMTalonSRX(RobotMap.CARGO_INTAKE2);
	//Spark SpeedControl = new Spark(RobotMap.CARGO_INTAKE);
	
	
	
	
	public void readyForInput() {
		
		//cargoTalon.set(OI.XBoxControllerMisc.getY(Hand.kLeft));
		
		if (OI.XBoxControllerMisc.getBumper(Hand.kLeft) ) {
			intake();
		}else if(OI.XBoxControllerMisc.getBumper(Hand.kRight)) {
			outtake();
		}else {
			idle();
		}
	
	}
	
    private void intake() {
    	cargoTalon.set(0.6);
    	cargoTalon2.set(0.6);
    }
    
    private void outtake() {
    	cargoTalon.set(-0.6);
    	cargoTalon2.set(-0.6);

    }

    private void idle() {
    	cargoTalon.stopMotor();
    	cargoTalon2.stopMotor();
    }

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
