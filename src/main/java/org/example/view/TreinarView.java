package org.example.view;

import org.example.dto.RNALearnRequest;
import org.example.service.RNAService;

import javax.swing.*;

public class TreinarView extends JFrame {

    private JPanel treinamentoPanel;
    private JPanel configsPanel;
    private JTextField neuroniosTxtField;
    private JSpinner numCamadasSpinner;
    private JComboBox taxaAprendizadoComboBox;
    private JComboBox taxaErroComboBox;
    private JButton treinarButton;
    private JSlider neuroniosSlider;
    private JSlider maxIteracoesSlider;
    private JLabel neuroniosValLabel;
    private JLabel itreracoesValLabel;
    private JLabel statusLabel;
    private JButton continuarButton;
    private JTextField taxaAprendizadoTxtField;
    private JTextField taxaErroTxtField;
    private JPanel statusPanel;
    private JSpinner neuroniosSpinner;
    private JTextField limiteTxtField;
    private JSpinner limiteIteracoesSpinner;

    public TreinarView(RNAService service) {
        treinarButton.addActionListener(a -> {
            continuarButton.setEnabled(false);
            RNALearnRequest request = new RNALearnRequest();
            request.setNumLayers((Integer) numCamadasSpinner.getValue());
            request.setNumNeuronios((Integer) neuroniosSpinner.getValue());
            request.setMaxIterationNum(Integer.parseInt(limiteTxtField.getText()));
            request.setLearningRate(Double.parseDouble(taxaAprendizadoTxtField.getText()));
            request.setErrorRate(Double.parseDouble(taxaErroTxtField.getText()));

            statusLabel.setText("Aprendendo...");
            super.update(this.getGraphics());

            try {
                String learnResponse = service.learn(request);
                statusLabel.setText(learnResponse);
                continuarButton.setEnabled(true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        continuarButton.addActionListener(a -> {
            this.dispose();
        });

        setTitle("Aprendizado RNA");
        setContentPane(treinamentoPanel);
        setSize(550, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
