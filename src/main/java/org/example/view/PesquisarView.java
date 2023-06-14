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
    private JPanel recomendacaoPanel;
    private JPanel respostaAdrenaPanel;
    private JTextArea respostaAdrenaTxtArea;
    private JButton sobreButton;
    private JTextField iteracaoTxtField;
    private JTextField taxaErroTxtField;
    private JTextField taxaAprendizadoTxtField;
    private JLabel iteracaoLabel;
    private JLabel taxaErroLabel;
    private JLabel taxaAprendizadoLabel;
    private JPanel configsAprendizadoPanel;
    private JTextArea entradaUsuarioTxtArea;
    private JLabel resultadoNumLabel;

    private String nEntradaBinaria;
    private String pEntradaBinaria;
    private String kEntradaBinaria;
    private String tempEntradaBinaria;
    private String humEntradaBinaria;
    private String phEntradaBinaria;
    private String chuvaEntradaBinaria;
    private RNAService service;
    private RNARecognizeResponse rnaRecognizeResponse = new RNARecognizeResponse();

    public PesquisarView(RNAService rnaService) {
        service = rnaService;
        rnaRecognizeResponse = new RNARecognizeResponse();


        //configs de aprendizado
        iteracaoTxtField.setText(String.valueOf(rnaService.getIteracoes()));
        taxaErroTxtField.setText(String.valueOf(rnaService.getErrorRate()));
        taxaAprendizadoTxtField.setText(String.valueOf(rnaService.getLearningRate()));

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

        sobreButton.addActionListener(a -> {
            new SobreView();
        });


        buscarButton.addActionListener(a -> {

            nEntradaBinaria = convertIntToBinary(Integer.toBinaryString(nSlider.getValue()), "%8s");
            pEntradaBinaria = convertIntToBinary(Integer.toBinaryString(pSlider.getValue()), "%8s");
            kEntradaBinaria = convertIntToBinary(Integer.toBinaryString(kSlider.getValue()), "%8s");
            tempEntradaBinaria = convertIntToBinary(Integer.toBinaryString(temperaturaSlider.getValue()), "%6s");
            humEntradaBinaria = convertIntToBinary(Integer.toBinaryString(humidadeSlider.getValue()), "%7s");
            phEntradaBinaria = convertIntToBinary(Integer.toBinaryString(phSlider.getValue()), "%4s");
            chuvaEntradaBinaria =  convertIntToBinary(Integer.toBinaryString(chuvaSlider.getValue()), "%9s");
            String requestStr = nEntradaBinaria
                    + pEntradaBinaria
                    + kEntradaBinaria
                    + tempEntradaBinaria
                    + humEntradaBinaria
                    + phEntradaBinaria
                    + chuvaEntradaBinaria;

            System.out.println("Entradas: " + requestStr);

            try {
                rnaRecognizeResponse = service.recognize(requestStr);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            resultadoField.setText(rnaRecognizeResponse.getGrao());
            entradaUsuarioTxtArea.setText(nEntradaBinaria + ", " + pEntradaBinaria + ", " + kEntradaBinaria + ", "
                    + tempEntradaBinaria + ", " + humEntradaBinaria + ", " + phEntradaBinaria + ", " + chuvaEntradaBinaria);
            respostaAdrenaTxtArea.setText(rnaRecognizeResponse.getValor());
        });

        setTitle("Pesquisa de grãos");
        setContentPane(pesquisarPanel);
        setSize(600, 900);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private String convertIntToBinary(String num, String numBits) {
        return String.format(numBits, num).replaceAll(" ", "0");
    }
}
