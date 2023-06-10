package org.example.dto;

public class RNARecognizeResponse {

    private String grao;
    private String valor;

    public RNARecognizeResponse() {}

    public RNARecognizeResponse(String grao, String valor) {
        this.grao = grao;
        this.valor = valor;
    }

    public String getGrao() {
        return grao;
    }

    public void setGrao(String grao) {
        this.grao = grao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
