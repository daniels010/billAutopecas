package com.example.billAutopecas.data.models;

public class Produto {
    private String codigo;
    private String descricao;
    private String marca;
    private float valorEntrada;
    private float valorSaida;
    private int quantidadeAtual;

    private int quantidadeVendida;

    //deixar isso aq congelado acho q foi ideia burra
    //private float descontoTotal = 0;

    //construtor
    public Produto(String codigo, String descricao, String marca, float valorEntrada, float valorSaida, int quantidadeAtual) {
        this.codigo = codigo;
        this.descricao = descricao.toUpperCase();
        this.marca = marca.toUpperCase();
        this.valorEntrada = valorEntrada;
        this.valorSaida = valorSaida;
        this.quantidadeAtual = quantidadeAtual;
        this.quantidadeVendida = 0;
    }

    //getters
    public String getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }
    public String getMarca() { return marca; }
    public float getValorEntrada() { return valorEntrada; }
    public float getValorSaida() { return valorSaida; }
    public int getQuantidadeAtual() { return quantidadeAtual; }

    public int getQuantidadeVendida() { return quantidadeVendida; }

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

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    //metodo pra aplicar desconto, na real é so um set do descontoTotal
    public void aplicarDesconto(double percentualDesconto) {
        this.valorSaida -= (float) (this.valorSaida * (percentualDesconto / 100));
    }

    public void aplicarAumento(double percentualAumento) {
        this.valorSaida += (float) (this.valorSaida * (percentualAumento / 100));
    }
}
