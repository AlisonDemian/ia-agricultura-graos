package org.example.service;

import ADReNA_API.Data.DataSet;
import ADReNA_API.Data.DataSetObject;
import ADReNA_API.NeuralNetwork.Backpropagation;
import org.example.dto.RNALearnRequest;
import org.example.dto.RNARecognizeResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class RNAService {

    public static final String CSV_PATH = "C:\\DataSet_All_Bin.xlsx.csv";

    private DataSet dataSet;

    private static Backpropagation backpropagation;

    private int iteracoes;

    private double errorRate = 0.005;

    private double learningRate = 0.1;

    private int maxIterationNum = 500000;

    private int numLayers = 1;

    private int numNeuronios = 50;

    public RNAService() {
        dataSet = new DataSet(50, 5);

    }

    public void carregaDataSet() throws Exception {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
            System.out.println(records);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var count = 0;
        for (List<String> row : records) {
            if (count != 0) {
                for (String values : row) {  // percorre cada linha
                    String value = "";
                    Object[] columnValues = row.toArray();
                    String result = (String) columnValues[columnValues.length - 1]; // pega a resposta que ta na ultima coluna
                    for (int i = 0; i < columnValues.length - 1; i++) {// percorre e pega o valor de todas as colunas dentro da linha (menos a ultima que é a resposta)
                        value += (String) columnValues[i];
                    }
                    double[] results = getDoubleArrayFromString(value);
                    double[] resultDouble = getDoubleArrayFromString(result);
                    dataSet.Add(new DataSetObject(results,resultDouble));
                    System.out.println();

                }
            }
            count++;
        }

    }

    public String learn(RNALearnRequest request) throws Exception {
        if(request.getNumLayers() <= 0) {
            backpropagation = new Backpropagation(50, 5);
        }
        else {
            int[] layers = new int[request.getNumLayers()];
            for(int i = 0; i < layers.length; i++) {
                layers[i] = request.getNumNeuronios();
            }

            backpropagation = new Backpropagation(50,5, layers);
        }

        backpropagation.SetLearningRate(request.getLearningRate());
        backpropagation.SetErrorRate(request.getErrorRate());
        backpropagation.SetMaxIterationNumber(request.getMaxIterationNum());

        System.out.println(dataSet.GetList());
        System.out.println("inicio treino: " + new Date());
        backpropagation.Learn(dataSet);
        System.out.println("final do treino: " + new Date());
        iteracoes = backpropagation.GetIterationNumber();
        errorRate = backpropagation.GetErrorRate();
        learningRate = backpropagation.GetLearningRate();

        return "Aprendizado finalizado com sucesso!\n "+ iteracoes +" iterações realizadas";
    }

    public RNARecognizeResponse recognize(String requestValues) throws Exception {
        double[] request = getDoubleArrayFromString(requestValues);
        double[] recognizeResponse = backpropagation.Recognize(request);

        String responseNums = Arrays.toString(recognizeResponse);
        String grao = getGrao(recognizeResponse);

        return new RNARecognizeResponse(grao, responseNums);
    }

    private static double[] getDoubleArrayFromString(String value) {
        Pattern pattern = Pattern.compile("");
        return pattern.splitAsStream(value)
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    private String getGrao(double[] values) {
        String responseStr = "";
        for(double val : values) {
            responseStr += Math.round(val);
        }

        switch (responseStr) {
            case "00001":
                return "Arroz";
            case "00010":
                return "Milho";
            case "00011":
                return "Grão de bico";
            case "00100":
                return "Feijão";
            case "00101":
                return "Feijão guandu";
            case "00110":
                return "Feijão-mariposa";
            case "00111":
                return "Feijão moyashi";
            case "01000":
                return "Feijão preto";
            case "01001":
                return "Lentilha";
            case "01010":
                return "Romã";
            case "01011":
                return "Banana";
            case "01100":
                return "Manga";
            case "01101":
                return "Uva";
            case "01110":
                return "Melancia";
            case "01111":
                return "Melão";
            case "10000":
                return "Maçã";
            case "10001":
                return "Laranja";
            case "10010":
                return "Mamão";
            case "10011":
                return "Coco";
            case "10100":
                return "Algodão";
            case "10101":
                return "Juta";
            case "10110":
                return "Café";

            default:
                return "Não há grão compatível.";

        }
    }

    public int getIteracoes() {
        return iteracoes;
    }

    public void setIteracoes(int iteracoes) {
        this.iteracoes = iteracoes;
    }

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
