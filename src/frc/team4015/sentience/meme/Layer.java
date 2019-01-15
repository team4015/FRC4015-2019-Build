package frc.team4015.sentience.meme;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public abstract class Layer implements Serializable {

    public abstract List<Double> process(List<Double> data) throws IOException;
    public abstract void fit(List<Double> target) throws IOException;
}
