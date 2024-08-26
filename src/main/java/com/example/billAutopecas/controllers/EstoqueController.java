package com.example.billAutopecas.controllers;

import com.example.billAutopecas.data.models.Produto;
import com.example.billAutopecas.data.structures.Lista;
import com.example.billAutopecas.data.services.ListaTranslationLayer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class EstoqueController {

    private final Lista lista = new Lista(); //Usando uma instância simples para exemplo
    private final ListaTranslationLayer listaService = new ListaTranslationLayer(lista);

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
        if (produto.getQuantidadeAtual() < 0) {
            return new ResponseEntity<>("Quantidade atual não pode ser negativa", HttpStatus.BAD_REQUEST);
        }

        // Adiciona o produto à lista
        lista.adicionarProduto(produto);

        // Retorna uma resposta com status 200 OK
        return new ResponseEntity<>("Produto adicionado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> buscarProdutoPorCodigo(@PathVariable String codigo) {
        Produto produto = lista.buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Produto>> listarProdutos() {
        //usando o serviço para listar todos os produtos
        List<Produto> produtos = listaService.listarTodosProdutos();

        //verifica se a lista está vazia
        if (produtos.isEmpty()) {
            //404 se tiver vazia
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            //retorna a lista e 200 se nao tiver
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        }
    }
}