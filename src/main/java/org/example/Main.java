package org.example;

import org.example.service.RNAService;
import org.example.view.PesquisarView;

public class Main {
    public static void main(String[] args) throws Exception {

        RNAService rnaService = new RNAService();
        rnaService.carregaDataSet();
        rnaService.learn();
        PesquisarView pesquisarView = new PesquisarView(rnaService);
    }
}