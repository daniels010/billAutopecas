package com.example.billAutopecas.data.structures;

import com.example.billAutopecas.data.models.Venda;

public class NoVenda {
    private Venda venda;
    private NoVenda proximo;

    public NoVenda(Venda venda) {
        this.venda = venda;
        this.proximo = null;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public NoVenda getProximo() {
        return proximo;
    }

    public void setProximo(NoVenda proximo) {
        this.proximo = proximo;
    }
}
