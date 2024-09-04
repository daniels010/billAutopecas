package com.example.billAutopecas.controllers;

import com.example.billAutopecas.data.models.Produto;
import com.example.billAutopecas.data.services.ListaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class EstoqueController {

    private final ListaService listaService;

    public EstoqueController(ListaService listaService) {
        this.listaService = listaService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarProduto(@RequestBody Produto produto) {
        // Validação dos campos obrigatórios
        if (produto.getCodigo() == null || produto.getCodigo().isEmpty()) {
            return new ResponseEntity<>("Código do produto é obrigatório", HttpStatus.BAD_REQUEST);
        }
        if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
            return new ResponseEntity<>("Descrição do produto é obrigatória", HttpStatus.BAD_REQUEST);
        }
        if (produto.getValorEntrada() <= 0) {
            return new ResponseEntity<>("Valor de entrada deve ser maior que zero", HttpStatus.BAD_REQUEST);
        }
        if (produto.getValorSaida() <= 0) {
            return new ResponseEntity<>("Valor de saída deve ser maior que zero", HttpStatus.BAD_REQUEST);
        }
        if (produto.getQuantidadeAtual() <= 0) {
            return new ResponseEntity<>("Quantidade atual deve ser maior que 0", HttpStatus.BAD_REQUEST);
        }

        boolean adicionado = listaService.adicionarProduto(produto);
        if (adicionado) {
            return new ResponseEntity<>("Produto adicionado com sucesso", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Produto já existe", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> buscarProdutoPorCodigo(@PathVariable String codigo) {
        Produto produto = listaService.buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<String> listarProdutos() {
        String produtos = listaService.listarTodosProdutos();

        if (produtos.trim().isEmpty()) {
            // Retorna a mensagem e o status 404 quando não há produtos
            return new ResponseEntity<>("Nenhum produto encontrado", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        }
    }

    @PostMapping("/repor-estoque")
    public ResponseEntity<String> reporEstoque(@RequestParam String codigo, @RequestParam int quantidade) {
        boolean sucesso = listaService.reporEstoque(codigo, quantidade);

        if (sucesso) {
            return new ResponseEntity<>("Estoque atualizado com sucesso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Falha ao repor o estoque. Verifique o código e a quantidade.", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/vender-produto")
    public ResponseEntity<String> venderProduto(@RequestParam String codigo, @RequestParam int quantidade) {
        boolean vendaRealizada = listaService.venderProduto(codigo, quantidade);

        if (vendaRealizada) {
            return new ResponseEntity<>("Produto vendido com sucesso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erro ao vender produto. Verifique o código do produto ou a quantidade.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/alterar-preco-saida")
    public ResponseEntity<String> alterarPrecoSaida(@RequestParam String codigo, @RequestParam float novoPreco) {
        boolean sucesso = listaService.alterarPrecoSaidaIndividual(codigo, novoPreco);
        if (sucesso) {
            return new ResponseEntity<>("Preço de saída alterado com sucesso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Falha ao alterar o preço de saída. Verifique o código e o valor.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/alterar-preco-saida-todos")
    public ResponseEntity<String> alterarPrecoSaidaTodos(@RequestParam float novoPreco) {
        boolean sucesso = listaService.alterarPrecoSaidaTodos(novoPreco);
        if (sucesso) {
            return new ResponseEntity<>("Preço de saída alterado com sucesso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Falha ao alterar o preço de saída. Não há produtos em estoque", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/alterar-preco-entrada")
    public ResponseEntity<String> alterarPrecoEntrada(@RequestParam String codigo, @RequestParam float novoPreco) {
        boolean sucesso = listaService.alterarPrecoEntrada(codigo, novoPreco);
        if (sucesso) {
            return new ResponseEntity<>("Preço de entrada alterado com sucesso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Falha ao alterar o preço de entrada. Verifique o código e o valor.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/vendas")
    public ResponseEntity<String> relatorioVendas() {
        try {
            String relatorio = listaService.gerarRelatorioVendas();
            if (relatorio.isEmpty()) {
                return new ResponseEntity<>("Nenhuma venda registrada", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(relatorio, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao gerar relatório de vendas", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estoque")
    public ResponseEntity<String> relatorioEstoque() {
        try {
            String relatorio = listaService.gerarRelatorioEstoque();
            if (relatorio.isEmpty()) {
                return new ResponseEntity<>("Nenhum produto em estoque", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(relatorio, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao gerar relatório de estoque", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
