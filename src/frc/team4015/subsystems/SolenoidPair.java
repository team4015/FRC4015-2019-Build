package frc.team4015.subsystems;

import edu.wpi.first.wpilibj.Solenoid;

class SolenoidPair {

	private Solenoid openSolenoid;
	private Solenoid closeSolenoid;
	private boolean opened = false;
	
	SolenoidPair(Solenoid openSolenoid, Solenoid closeSolenoid) {
		this.openSolenoid = openSolenoid;
		this.closeSolenoid = closeSolenoid;
		this.openSolenoid.set(opened);
		this.closeSolenoid.set(!opened);
	}
	
	protected void toggle() {
		opened = !opened;
		this.openSolenoid.set(opened);
		this.closeSolenoid.set(!opened);
	}
	
	protected void reset() {
		opened = false;
		this.openSolenoid.set(opened);
		this.closeSolenoid.set(!opened);
	}
	
}
