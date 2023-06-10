package org.example;

import org.example.service.RNAService;
import org.example.view.MenuView;

public class Main {
    public static void main(String[] args) throws Exception {

        RNAService rnaService = new RNAService();
        rnaService.carregaDataSet();
        rnaService.learn();
        MenuView menuView = new MenuView();
    }
}