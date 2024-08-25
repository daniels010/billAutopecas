package com.example.billAutopecas.data.models;

public class Produto {
    private String codigo;
    private String descricao;
    private String marca;
    private float valorEntrada;
    private float valorSaida;
    private int quantidadeAtual;

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public String getCodigo(){
        return codigo;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public String getMarca(){
        return marca;
    }

    public void setValorEntrada(float valorEntrada){
        this.valorEntrada = valorEntrada;
    }

    public float getValorEntrada(){
        return valorEntrada;
    }

    public void setValorSaida(float valorSaida){
        this.valorSaida = valorSaida;
    }

    public float getValorSaida(){
        return valorSaida;
    }

    public void setQuantidadeAtual(int quantidadeAtual){
        this.quantidadeAtual = quantidadeAtual;
    }

    public int getQuantidadeAtual(){
        return quantidadeAtual;
    }
}
