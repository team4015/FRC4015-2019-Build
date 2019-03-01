package frc.team4015.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class EverythingLol extends CommandGroup {
	
	public EverythingLol() {
		
		addParallel(new CargoControl());
		addParallel(new PlateControl());
		addParallel(new WhyDrive());
		
	}

}
