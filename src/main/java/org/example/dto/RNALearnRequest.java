package org.example.dto;

public class RNALearnRequest {

    private double errorRate;

    private double learningRate;

    private int maxIterationNum;

    private int numLayers;

    private int numNeuronios;


    public double getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(double errorRate) {
        this.errorRate = errorRate;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public int getMaxIterationNum() {
        return maxIterationNum;
    }

    public void setMaxIterationNum(int maxIterationNum) {
        this.maxIterationNum = maxIterationNum;
    }

    public int getNumLayers() {
        return numLayers;
    }

    public void setNumLayers(int numLayers) {
        this.numLayers = numLayers;
    }

    public int getNumNeuronios() {
        return numNeuronios;
    }

    public void setNumNeuronios(int numNeuronios) {
        this.numNeuronios = numNeuronios;
    }
}
