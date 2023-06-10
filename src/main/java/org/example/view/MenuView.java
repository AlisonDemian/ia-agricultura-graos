package org.example.view;

import javax.swing.*;

public class MenuView extends JFrame{
    private JPanel menuPanel;
    private JPanel botoesPanel;
    private JButton pesquisarButton;
    private JButton treinarButton;
    private JLabel tituloLabel;
    private JButton sobreButton;

    public MenuView() {

        pesquisarButton.addActionListener(a -> {
            new PesquisarView();
        });

        sobreButton.addActionListener(a -> {
            new SobreView();
        });

        setContentPane(menuPanel);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
