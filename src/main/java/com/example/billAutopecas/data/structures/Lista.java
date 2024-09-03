package com.example.billAutopecas.data.structures;

import com.example.billAutopecas.data.models.Produto;
import com.example.billAutopecas.data.models.Venda;

public class Lista {
    private No inicio;
    private NoVenda inicioVendas;

    // Construtor iniciando lista vazia
    public Lista() {
        this.inicio = null;
        this.inicioVendas = null;
    }

    public No getInicio() {
        return inicio;
    }

    // Adiciona um produto na lista
    public boolean adicionarProduto(Produto produto) {
        Produto produtoExistente = buscarProdutoPorCodigo(produto.getCodigo());

        if (produtoExistente != null) {
            return false; // Produto já existe
        } else {
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

                novoNo.setProximo(temp.getProximo());
                temp.setProximo(novoNo);
            }

            return true; // Produto adicionado com sucesso
        }
    }

    // Busca um produto por código
    public Produto buscarProdutoPorCodigo(String codigo) {
        No temp = inicio;
        while (temp != null) {
            if (temp.getProduto().getCodigo().equals(codigo)) {
                return temp.getProduto();
            }
            temp = temp.getProximo();
        }
        return null; // Produto não encontrado
    }

    // Lista todos os produtos
    public Lista listarTodosProdutos() {
        Lista listaProdutos = new Lista();
        No temp = inicio;
        while (temp != null) {
            listaProdutos.adicionarProduto(temp.getProduto());
            temp = temp.getProximo();
        }
        return listaProdutos;
    }

    // Formata a lista de produtos para o frontend
    public String listarTodosProdutosFront() {
        StringBuilder response = new StringBuilder();
        No temp = inicio;

        if (temp == null) {
            return "Nenhum produto encontrado";
        }

        while (temp != null) {
            Produto produto = temp.getProduto();
            response.append("Código: ").append(produto.getCodigo())
                    .append(", Descrição: ").append(produto.getDescricao())
                    .append(", Marca: ").append(produto.getMarca())
                    .append(", Valor Entrada: ").append(produto.getValorEntrada())
                    .append(", Valor Saída: ").append(produto.getValorSaida())
                    .append(", Estoque: ").append(produto.getQuantidadeAtual())
                    .append("\n");
            temp = temp.getProximo();
        }

        return response.toString();
    }

    // Repor estoque de um produto
    public void reporEstoque(String codigo, int quantidade) {
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            produto.setQuantidadeAtual(produto.getQuantidadeAtual() + quantidade);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    // Vender um produto
    public boolean venderProduto(String codigo, int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Quantidade de venda deve ser maior que 0.");
            return false;
        }

        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            if (produto.getQuantidadeAtual() >= quantidade) {
                produto.setQuantidadeAtual(produto.getQuantidadeAtual() - quantidade);
                produto.setQuantidadeVendida(produto.getQuantidadeVendida() + quantidade);

                // Registra a venda com o preço atual do produto
                float precoVenda = produto.getValorSaida();
                Venda novaVenda = new Venda(codigo, quantidade, precoVenda);
                NoVenda novoNoVenda = new NoVenda(novaVenda);

                if (inicioVendas == null) {
                    inicioVendas = novoNoVenda;
                } else {
                    NoVenda temp = inicioVendas;
                    while (temp.getProximo() != null) {
                        temp = temp.getProximo();
                    }
                    temp.setProximo(novoNoVenda);
                }

                // Retorna true se a venda foi realizada com sucesso
                return true;
            } else {
                System.out.println("Estoque insuficiente.");
                return false;
            }
        } else {
            System.out.println("Produto não encontrado.");
            return false;
        }
    }

    public boolean alterarPrecoSaidaTodos(float percentualAlteracao) {
        if (percentualAlteracao == 0) {
            return false; // Nenhuma alteração se o percentual for zero
        }

        // Verifica se há produtos na lista
        if (inicio == null) {
            return false; // Nenhum produto em estoque
        }

        No atual = inicio;

        while (atual != null) {
            Produto produto = atual.getProduto();
            float precoAtual = produto.getValorSaida();
            float fatorAlteracao = percentualAlteracao / 100;

            // Calcula o novo preço com base no percentual fornecido
            float novoPreco = precoAtual * (1 + fatorAlteracao);

            // Garante que o preço não fique negativo
            if (novoPreco < 0) {
                novoPreco = 0;
            }

            produto.setValorSaida(novoPreco);

            atual = atual.getProximo(); // Passa para o próximo nó
        }

        return true; // Retorna true se a operação for realizada com sucesso
    }

    // Alterar preço individual de um produto
    public boolean alterarPrecoSaidaIndividual(String codigo, float novoPreco) {
        if (novoPreco < 0) {
            return false; // Preço inválido
        }

        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            produto.setValorSaida(novoPreco);
            return true; // Alteração bem-sucedida
        } else {
            return false; // Produto não encontrado
        }
    }

    public boolean alterarPrecoEntradaIndividual(String codigo, float novoPreco) {
        if (novoPreco < 0) {
            return false; // Preço inválido
        }

        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            produto.setValorEntrada(novoPreco);
            return true; // Alteração bem-sucedida
        } else {
            return false; // Produto não encontrado
        }
    }


    // Gerar relatório de vendas
    public String gerarRelatorioVendas() {
        StringBuilder relatorioVendas = new StringBuilder();
        double valorTotalGeral = 0.0;

        NoVenda tempVenda = inicioVendas;
        while (tempVenda != null) {
            Venda venda = tempVenda.getVenda();
            String codigo = venda.getCodigoProduto();
            double valorVenda = venda.getPrecoVenda();
            int quantidadeVendida = venda.getQuantidade();
            double valorTotal = valorVenda * quantidadeVendida;

            // Adicionar informações da venda ao relatório
            relatorioVendas.append("Produto: ").append(codigo)
                    .append(", Quantidade Vendida: ").append(quantidadeVendida)
                    .append(", Preço de Venda: ").append(valorVenda)
                    .append(", Valor Total: ").append(valorTotal)
                    .append("\n");

            // Atualizar o valor total geral
            valorTotalGeral += valorTotal;

            tempVenda = tempVenda.getProximo();
        }

        // Adicionar o total geral das vendas
        relatorioVendas.append("Valor Total Geral das Vendas: ").append(valorTotalGeral);

        return relatorioVendas.toString();
    }

    //Relatório de estoque
    public String gerarRelatorioEstoque() {
        StringBuilder relatorioEstoque = new StringBuilder();

        No temp = inicio;
        while (temp != null) {
            Produto produto = temp.getProduto();
            relatorioEstoque.append("Produto: ").append(produto.getDescricao())
                    .append(", Quantidade em Estoque: ").append(produto.getQuantidadeAtual())
                    .append("\n");
            temp = temp.getProximo();
        }

        return relatorioEstoque.toString();
    }

    /* APOSENTADOS Q TALVEZ PRECISE DEPOIS DEIXE AI

    // Aplicar desconto a um produto específico
    public void aplicarDesconto(String codigo, double percentualDesconto) {
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            produto.aplicarDesconto(percentualDesconto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    // Aplicar aumento a um produto específico
    public void aplicarAumento(String codigo, double percentualAumento) {
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            produto.aplicarAumento(percentualAumento);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
     */
}
