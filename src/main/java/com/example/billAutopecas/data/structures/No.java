package com.example.billAutopecas.data.structures;

import com.example.billAutopecas.data.models.Produto;

public class No {
    Produto produto;
    No proximo;

    //construtor
    public No(Produto produto) {
        this.produto = produto;
        this.proximo = null;
    }

    public Produto getProduto(){
        return produto;
    }

    public void setProximo(No proximo){
        this.proximo = proximo;
    }

    public No getProximo(){
        return proximo;
    }
}
