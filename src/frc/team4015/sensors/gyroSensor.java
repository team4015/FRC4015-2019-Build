package frc.team4015.sensors;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.team4015.Robot;

/****
 * @Author: Omar Alam
 * This class is meant to operate the gyroscope that could be theoretically put on the robot and be used for many purposes.
 * It does not measure the actual heading of the robot yet, but could be used to measure it since it gives us the angular velocity
 * of the robot.
 */
public class gyroSensor extends Robot {

    private AnalogGyro gyro1;

    private double heading = 0.0; //HEADING CAN BE B/W 0-359.99

    public gyroSensor(){
        gyro1 = new AnalogGyro(0);
        initialize();
    }

    public boolean initialize(){
        //gyro1.setSensitivity(0.0126); TODO: Needs to be looked up in spec sheet if implemented FRC MANUAL PAGE: 164
        gyro1.initGyro();
        gyro1.calibrate();

        return true;
    }

    public void updateHeading(){
        gyro1.reset();
        double angle = gyro1.getAngle();
        heading+=angle;
    }

    public double getRobotHeading(){
        return heading;
    }




}
