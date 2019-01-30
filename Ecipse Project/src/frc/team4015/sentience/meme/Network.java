package frc.team4015.sentience.meme;

import java.io.Serializable;
import java.util.Random;

public class Network implements Serializable {

    protected static final double LEARNING_SPEED = 0.05;
    protected static final Random RANDOM = new Random();

    protected static double sigmoid(double input) {
        return (1 / (1 + Math.pow(Math.E,-input)));
    }

}
