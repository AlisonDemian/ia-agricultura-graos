package org.example.view;

import javax.swing.*;

public class PesquisarView extends JFrame{
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
    private JPanel chuvaPanel;
    private JButton buscarButton;

    public PesquisarView() {
        setContentPane(pesquisarPanel);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
