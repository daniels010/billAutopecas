package com.example.billAutopecas.data.services;

import com.example.billAutopecas.data.models.Produto;
import com.example.billAutopecas.data.structures.Lista;
import com.example.billAutopecas.data.structures.No;
import java.util.ArrayList;
import java.util.List;

public class ListaTranslationLayer {
    private final Lista lista;

    public ListaTranslationLayer(Lista lista) {
        this.lista = lista;
    }

    public List<Produto> listarTodosProdutos() {
        List<Produto> produtos = new ArrayList<>();
        No temp = lista.getInicio(); // Use o getter para acessar o início da lista
        while (temp != null) {
            produtos.add(temp.getProduto());
            temp = temp.getProximo();
        }
        return produtos;
    }
}
