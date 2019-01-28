package frc.team4015.sensors;

import edu.wpi.first.wpilibj.Ultrasonic;

/***
 * @Author: Omar
 * THIS CLASS IS TO GET A THEORETICAL ULTRASONIC SENSOR WORKING THAT WILL RETURN THE RANGE IN MILLIMETERS.
 */
public class rangeFinder {

    Ultrasonic sensor;

    public rangeFinder(int channel,int channel2){
        sensor = new Ultrasonic(channel,channel2); //TODO: NEED TO FIGURE OUT INPUT AND OUTPUT CHANNELS BASED ON WHERE ELECTRICAL TEAM PLUGS THE SENSORS IN
        sensor.setAutomaticMode(true);
    }


    /**
     * This method can be called to return the range being measured from a sensor.
     * @return Range in millimeters measured from the sensor
     */
    public double getRange(){
        double rangeMeasured = sensor.getRangeMM();
        if (sensor.isRangeValid()){
            return rangeMeasured;
        }else{
            return 0.0; // INVALID RANGE MEASUREMENT. NOTHING CAN BE 0M CLOSE TO THE SENSOR
        }
    }

}
