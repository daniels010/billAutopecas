package com.example.billAutopecas.data.services;

import com.example.billAutopecas.data.models.Produto;
import com.example.billAutopecas.data.structures.Lista;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ListaService {
    private final Lista lista = new Lista(); // Instância manual

    public String listarTodosProdutos() {
        return lista.listarTodosProdutosFront();
    }

    public boolean adicionarProduto(Produto produto) {
        return lista.adicionarProduto(produto);
    }

    public Produto buscarProdutoPorCodigo(String codigo) {
        return lista.buscarProdutoPorCodigo(codigo);
    }

    public boolean reporEstoque(String codigo, int quantidade) {
        return lista.reporEstoque(codigo, quantidade);
    }

    public boolean venderProduto(String codigo, int quantidade) {
        return lista.venderProduto(codigo, quantidade);
    }

    public boolean alterarPrecoSaidaIndividual(String codigo, float novoPreco) {
        return lista.alterarPrecoSaidaIndividual(codigo, novoPreco);
    }

    public boolean alterarPrecoSaidaTodos(float desconto) {
        return lista.alterarPrecoSaidaTodos(desconto);
    }

    public boolean alterarPrecoEntrada(String codigo, float novoPreco) {
        return lista.alterarPrecoEntradaIndividual(codigo, novoPreco);
    }

    /*
    public String mostrarRelatorioDeVendas() {
        // Atualizamos o método para retornar uma String para o controller
        return lista.mostrarRelatorioDeVendasFront();
    }
    */

    /*
    public String mostrarRelatorioDeEstoque() {
        // Atualizamos o método para retornar uma String para o controller
        return lista.mostrarRelatorioDeEstoqueFront();
    }*/

    public String gerarRelatorioVendas() {
        return lista.gerarRelatorioVendas();
    }

    // Chama o método para gerar relatório de estoque
    public String gerarRelatorioEstoque() {
        return lista.gerarRelatorioEstoque();
    }
}
