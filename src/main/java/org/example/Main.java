package org.example;

import org.example.dto.RNALearnRequest;
import org.example.service.RNAService;
import org.example.view.PesquisarView;

public class Main {
    public static void main(String[] args) throws Exception {
        RNAService rnaService = new RNAService();
        rnaService.carregaDataSet();
        PesquisarView pesquisarView = new PesquisarView(rnaService);
    }
}