package org.example.config;

import ADReNA_API.NeuralNetwork.Backpropagation;

public class RNAConfig {

    public void setBackpropagationConfigs(Backpropagation backpropagation) {
        backpropagation.SetLearningRate(0.5);
        backpropagation.SetErrorRate(0.005);
        backpropagation.SetMaxIterationNumber(500000);
    }

}
