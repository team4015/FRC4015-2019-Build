package frc.team4015.sentience.meme;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//$Robots&in#SPACE!!
// R12,16
// I5

public class FullyConnected extends Layer implements Serializable {

    private List<List<Double>> weights = null;
    private List<Double> lastOutput = null;
    private List<Double> lastInput = null;

    public FullyConnected(List<List<Double>> weights) {
        this.weights = weights;
    }

    public FullyConnected(int neuronNumber, int inputVolume) {
        if (neuronNumber < 1) {
            System.out.println("Neuron number must be greater than or equal to 1.");
        }
        else {
            weights = new ArrayList<>();
            for (int layerConstruct = 0; layerConstruct < neuronNumber; layerConstruct++) {
                List<Double> layerToAdd = new ArrayList<>();
                for (int toAdd = 0; toAdd < inputVolume; toAdd++) {
                    layerToAdd.add(Network.RANDOM.nextDouble());
                }
                weights.add(layerToAdd);
            }
        }
    }

    @Override
    public List<Double> process(List<Double> data) throws IOException {

        List<Double> preparedOutput = new ArrayList<>();
        for (List<Double> weightList : weights) {
            int tracker = 0;
            double sum = 0;
            for (double input : data) {
                sum = sum + input * weightList.get(tracker);
                tracker++;
            }
            preparedOutput.add(Network.sigmoid(sum));
        }
        lastOutput = preparedOutput;
        lastInput = data;
        return preparedOutput;
    }

    @Override
    public void fit(List<Double> target) throws IOException {
        if (target.size() !=  lastOutput.size()) {
            throw new IOException("Target size must be equal to that of last output!");
        }
        if (lastInput == null || lastOutput == null) {
            throw new IOException("Must have previously processed input to fit!");
        }
        List<Double> weightedTargets = new ArrayList<>();
        int tracker = 0;
        for (double last : lastOutput) {
            weightedTargets.add(Math.pow(last - target.get(tracker),2));
            tracker++;
        }

    }

}
