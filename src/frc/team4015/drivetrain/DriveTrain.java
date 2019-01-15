package frc.team4015.drivetrain;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team4015.OI;
import frc.team4015.RobotMap;

public class DriveTrain {

    private static final double THROTTLE_SMOOTHING_FACTOR = 0.05;
    private static final double TURN_SMOOTHING_FACTOR = 0.05;

    private PWMTalonSRX leftTalon = new PWMTalonSRX(RobotMap.LEFT_MOTOR);
    private PWMTalonSRX rightTalon = new PWMTalonSRX(RobotMap.RIGHT_MOTOR);
    private DifferentialDrive robotDrive = new DifferentialDrive(leftTalon,rightTalon);
    private boolean throttleSmoothingMode = false;
    private boolean turnSmoothingMode = false;
    private boolean slowMode = false;
    private double lastThrottleMagnitude = 0;
    private double lastRotationMagnitude = 0;

    public void drive() {
        double speed = OI.Joystick.getY();
        double rotation = OI.Joystick.getX();
        if (slowMode) {
            speed = speed / 2;
            rotation = rotation / 2;
        }
        if (throttleSmoothingMode) {
            int sign = (int) (speed / Math.abs(speed));
            if (Math.abs(speed) - lastThrottleMagnitude > THROTTLE_SMOOTHING_FACTOR) {
                speed = sign * (lastThrottleMagnitude + THROTTLE_SMOOTHING_FACTOR);
            }
        }
        if (turnSmoothingMode) {
            int sign = (int) (rotation / Math.abs(rotation));
            if (Math.abs(rotation) - lastRotationMagnitude > TURN_SMOOTHING_FACTOR) {
                rotation = sign * (lastRotationMagnitude + TURN_SMOOTHING_FACTOR);
            }
        }
        lastThrottleMagnitude = Math.abs(speed);
        lastRotationMagnitude = Math.abs(rotation);
        robotDrive.arcadeDrive(speed, OI.Joystick.getX());
    }

}
