package org.example.view;

import org.example.dto.RNARecognizeResponse;
import org.example.service.RNAService;

import javax.swing.*;

public class PesquisarView extends JFrame {
    private JPanel pesquisarPanel;
    private JLabel tituloLabel;
    private JSlider nSlider;
    private JSlider pSlider;
    private JSlider kSlider;
    private JSlider temperaturaSlider;
    private JSlider humidadeSlider;
    private JSlider phSlider;
    private JSlider chuvaSlider;
    private JPanel nPanel;
    private JPanel pPanel;
    private JPanel kPanel;
    private JPanel temperaturaPanel;
    private JPanel humidadePanel;
    private JPanel phPanel;
    private JPanel pluviosidadePanel;
    private JButton buscarButton;
    private JTextField resultadoField;
    private JLabel nLabel;
    private JLabel pLabel;
    private JLabel kLabel;
    private JLabel tempLabel;
    private JLabel humidLabel;
    private JLabel phLabel;
    private JLabel chuvaLabel;
    private JLabel resultadoNumLabel;

    private RNAService service;
    private RNARecognizeResponse rnaRecognizeResponse = new RNARecognizeResponse();

    public PesquisarView() {

        service = new RNAService();
        rnaRecognizeResponse = new RNARecognizeResponse();

        nSlider.addChangeListener(event -> {
            nLabel.setText(String.valueOf(nSlider.getValue()));
        });
        pSlider.addChangeListener(event -> {
            pLabel.setText(String.valueOf(pSlider.getValue()));
        });
        kSlider.addChangeListener(event -> {
            kLabel.setText(String.valueOf(kSlider.getValue()));
        });
        phSlider.addChangeListener(event -> {
            phLabel.setText(String.valueOf(phSlider.getValue()));
        });
        humidadeSlider.addChangeListener(event -> {
            humidLabel.setText(String.valueOf(humidadeSlider.getValue()));
        });
        temperaturaSlider.addChangeListener(event -> {
            tempLabel.setText(String.valueOf(temperaturaSlider.getValue()));
        });
        chuvaSlider.addChangeListener(event -> {
            chuvaLabel.setText(String.valueOf(chuvaSlider.getValue()));
        });

        buscarButton.addActionListener(a -> {
            String requestStr = convertIntToBinary(Integer.toBinaryString(nSlider.getValue()), "%8s")
                    + convertIntToBinary(Integer.toBinaryString(pSlider.getValue()), "%8s")
                    + convertIntToBinary(Integer.toBinaryString(kSlider.getValue()), "%8s")
                    + convertIntToBinary(Integer.toBinaryString(temperaturaSlider.getValue()), "%6s")
                    + convertIntToBinary(Integer.toBinaryString(humidadeSlider.getValue()), "%7s")
                    + convertIntToBinary(Integer.toBinaryString(phSlider.getValue()), "%4s")
                    + convertIntToBinary(Integer.toBinaryString(chuvaSlider.getValue()), "%9s");

            try {
                rnaRecognizeResponse = service.recognize(requestStr);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            resultadoField.setText(rnaRecognizeResponse.getGrao());
            resultadoNumLabel.setText(rnaRecognizeResponse.getValor());
        });

        setContentPane(pesquisarPanel);
        setSize(500, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private String convertIntToBinary(String num, String numBits) {
        return String.format(numBits, num).replaceAll(" ", "0");
    }
}
