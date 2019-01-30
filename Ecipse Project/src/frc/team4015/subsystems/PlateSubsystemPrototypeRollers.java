/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4015.subsystems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4015.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class PlateSubsystemPrototypeRollers extends Subsystem implements CargoSubsystem
{
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private PWMTalonSRX intakeMotor = new PWMTalonSRX(RobotMap.CARGO_INTAKE);

    @Override
    public void outTake() {

    }

    @Override
    public void hold() {

    }

    @Override
    public void intake() {

    }

    public void initDefaultCommand()
    {
        // TODO: Set the default command for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }
}
