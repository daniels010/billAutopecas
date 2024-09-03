package com.example.billAutopecas.data.models;

public class Venda {
    private String codigoProduto;
    private int quantidade;
    private float precoVenda;

    public Venda(String codigoProduto, int quantidade, float precoVenda) {
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public float getPrecoVenda() {
        return precoVenda;
    }
}
