package com.example.billAutopecas.data.structures;

import com.example.billAutopecas.data.models.Produto;

import java.util.ArrayList;
import java.util.List;

public class Lista {
    private No inicio;

    //construtor iniciando lista vazia
    public Lista() {
        this.inicio = null;
    }

    //to usando esse metodo aqui pra retornar
    //pra minha camada que vai encapsular a lista e mandar pro front, APENAS
    public No getInicio() {
        return inicio;
    }

    //TODO:DECIDIR SE REALMENTE VOU CONTINUAR JUNTANDO O METODO DE REPOR NESSE
    //TODO:TALVEZ NOTIFICAR QUE JA EXISTE, DAR A OPCAO E AI ROTEAR PRO ENDPOINT DE REPOR, PENSAREI
    //inserindo ordenado pelo nome ja receba
    public void adicionarProduto(Produto produto) {
        //usando o metodo q criei pra ver se tem repetido pq tinha esquecido xiii
        Produto produtoExistente = buscarProdutoPorCodigo(produto.getCodigo());

        if (produtoExistente != null) {
            //Se o produto já existir, aumenta SO a quantidade
            produtoExistente.setQuantidadeAtual(
                    produtoExistente.getQuantidadeAtual() + produto.getQuantidadeAtual());
        } else {
            //se n existir, cria
            No novoNo = new No(produto);

            if (inicio == null || inicio.getProduto().getDescricao().compareTo(produto.getDescricao()) > 0) {
                novoNo.setProximo(inicio);
                inicio = novoNo;
            } else {
                No temp = inicio;

                while (temp.getProximo() != null &&
                        temp.getProximo().getProduto().getDescricao().compareTo(produto.getDescricao()) < 0) {
                    temp = temp.getProximo();
                }

                //inserindo o produto de fato na lista
                novoNo.setProximo(temp.getProximo());
                temp.setProximo(novoNo);
            }
        }
    }

    public Produto buscarProdutoPorCodigo(String codigo) {
        No temp = inicio;
        while (temp != null) {
            if (temp.getProduto().getCodigo().equals(codigo)) {
                return temp.getProduto();
            }
            temp = temp.getProximo();
        }
        return null; //produto não encontrado
    }

    public List<Produto> listarTodosProdutos() {
        List<Produto> produtos = new ArrayList<>();
        No temp = inicio;
        while (temp != null) {
            produtos.add(temp.getProduto());
            temp = temp.getProximo();
        }
        return produtos;
    }
    public void mostrarProdutos() {
        if (inicio == null) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        No temp = inicio;
        while (temp != null) {
            Produto produto = temp.getProduto();
            System.out.println("Código: " + produto.getCodigo() + ", Descrição: " + produto.getDescricao() +
                    ", Marca: " + produto.getMarca() + ", Valor Entrada: " + produto.getValorEntrada() +
                    ", Valor Saída: " + produto.getValorSaida() + ", Estoque: " + produto.getQuantidadeAtual());
            temp = temp.getProximo();
        }
    }

    public void mostrarProdutoIndividual(String codigo) {
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            System.out.println("Código: " + produto.getCodigo() + ", Descrição: " + produto.getDescricao() +
                    ", Marca: " + produto.getMarca() + ", Valor Entrada: " + produto.getValorEntrada() +
                    ", Valor Saída: " + produto.getValorSaida() + ", Estoque: " + produto.getQuantidadeAtual());
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    //vulgo somando no qtdEstoque atual
    public void reporEstoque(String codigo, int quantidade) {
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            produto.setQuantidadeAtual(produto.getQuantidadeAtual() + quantidade);
            System.out.println("Estoque do produto " + produto.getDescricao() + " atualizado.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    //quase a mesma coisa so q diminuindo do estoque se tiver
    public void venderProduto(String codigo, int quantidade) {
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            if (produto.getQuantidadeAtual() >= quantidade) {
                produto.setQuantidadeAtual(produto.getQuantidadeAtual() - quantidade);
                System.out.println("Venda realizada. Estoque atualizado.");
            } else {
                System.out.println("Estoque insuficiente.");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    //ALTERADOR INDIVIDUAL DE PRECO
    public void alterarPrecoIndividual(String codigo, float novoPreco) {
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            produto.setValorSaida(novoPreco);
            System.out.println("Valor de saída atualizado.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    //mudanco preco DENTRO DA LISTA em todos os produtos
    public void aplicarAlteracaoDePrecos(float percentual) {
        No temp = inicio;
        while (temp != null) {
            Produto produto = temp.getProduto();
            produto.aplicarDesconto(percentual);  // Aplica o desconto individualmente
            temp = temp.getProximo();
        }
        System.out.println("Desconto aplicado a todos os produtos.");
    }

    //esse metodo aqui eu to so MOSTRANDO com um desconto temporario, so pa ser massa
    public void mostrarProdutosComDesconto(float percentual) {
        No temp = inicio;
        while (temp != null) {
            Produto produto = temp.getProduto();
            float valorComDesconto = produto.getValorSaida() * (1 - percentual / 100);
            System.out.println("Produto: " + produto.getDescricao() + " - Valor com desconto: " + valorComDesconto);
            temp = temp.getProximo();
        }
    }
    public void mostrarRelatorioDeVendas() {
        if (inicio == null) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }
        No temp = inicio;
        while (temp != null) {
            Produto produto = temp.getProduto();
            System.out.println("Produto: " + produto.getDescricao() + ", Quantidade em Estoque: " +
                    produto.getQuantidadeAtual() + ", Valor de Saída: " + produto.getValorSaida());
            temp = temp.getProximo();
        }
    }

    public void mostrarRelatorioDeEstoque() {
        if (inicio == null) {
            System.out.println("Nenhum produto em estoque.");
            return;
        }
        No temp = inicio;
        while (temp != null) {
            Produto produto = temp.getProduto();
            System.out.println("Produto: " + produto.getDescricao() + ", Quantidade em Estoque: " +
                    produto.getQuantidadeAtual());
            temp = temp.getProximo();
        }
    }
}
