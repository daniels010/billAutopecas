package com.example.billAutopecas.data.models;

public class Produto {
    private String codigo;
    private String descricao;
    private String marca;
    private float valorEntrada;
    private float valorSaida;
    private int quantidadeAtual;
    private float descontoTotal = 0;

    //construtor
    public Produto(String codigo, String descricao, String marca, float valorEntrada, float valorSaida, int quantidadeAtual) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.marca = marca;
        this.valorEntrada = valorEntrada;
        this.valorSaida = valorSaida * (1 - descontoTotal);
        this.quantidadeAtual = quantidadeAtual;
    }

    //getters
    public String getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }
    public String getMarca() { return marca; }
    public float getValorEntrada() { return valorEntrada; }
    public float getValorSaida() { return valorSaida; }
    public int getQuantidadeAtual() { return quantidadeAtual; }

    //setters
    public void setValorEntrada(float valorEntrada) {
        this.valorEntrada = valorEntrada;
    }
    public void setValorSaida(float valorSaida) {
        this.valorSaida = valorSaida;
    }
    public void setQuantidadeAtual(int quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    //metodo pra aplicar desconto, na real é so um set do descontoTotal
    public void aplicarDesconto(float percentual) {
        this.descontoTotal = percentual/100;
    }
}
