package org.example.view;

import javax.swing.*;

public class SobreView extends JFrame{
    private JTextArea sobreTxtArea;
    private JPanel sobrePanel;

    public SobreView() {

        sobreTxtArea.append("O objetivo desta aplicação é recomendar o grão mais adequado para o plantio de acordo com as condições climáticas e do solo.");
        sobreTxtArea.append("\n\nObservação: Os campos temperatura, umidade e pluviosidade devem ser preenchidos de acordo com a média da estação do local.");
        sobreTxtArea.append("\n\nAlison, Joana e Yalim");
        setTitle("Sobre");
        setContentPane(sobrePanel);
        setSize(925, 150);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
