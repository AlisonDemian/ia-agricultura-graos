package org.example.service;

import ADReNA_API.Data.DataSet;
import ADReNA_API.Data.DataSetObject;
import ADReNA_API.NeuralNetwork.Backpropagation;
import org.example.config.RNAConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class RNAService {

    public static final String CSV_PATH = "D:\\DataSet_All_Bin.xlsx.csv";

    private DataSet dataSet;

    private RNAConfig rnaConfig;

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

    public void learn() throws Exception {
        int[] layers = new int[1];
        layers[0] = 50;
        Backpropagation backpropagation = new Backpropagation(50,5, layers);

        backpropagation.SetLearningRate(0.3);
        backpropagation.SetErrorRate(0.010);
        backpropagation.SetMaxIterationNumber(500000);

        System.out.println(dataSet.GetList());
        System.out.println("inicio treino: " + new Date());
        backpropagation.Learn(dataSet);
        System.out.println("final do treino: " + new Date());
        double[] request = new double[] {0,1,0,1,1,0,1,0,0,0,1,0,1,0,1,0,0,0,1,0,1,0,1,1,0,1,0,1,0,1,1,0,1,0,0,1,0,0,1,1,1,0,1,1,0,0,1,0,1,1};
        System.out.println("inicio reconhecimento: " + new Date());
        double[] response = backpropagation.Recognize(request);
        System.out.println("reconheceu: " + new Date());
        System.out.println("response: " + response[0]);
    }


    private static double[] getDoubleArrayFromString(String value) {
        Pattern pattern = Pattern.compile("");
        return pattern.splitAsStream(value)
                .mapToDouble(Double::parseDouble)
                .toArray();
    }


}
