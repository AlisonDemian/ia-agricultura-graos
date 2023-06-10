package org.example.view;

import javax.swing.*;

public class SobreView extends JFrame{
    private JTextArea sobreTxtArea;
    private JPanel sobrePanel;

    public SobreView() {
        setContentPane(sobrePanel);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
