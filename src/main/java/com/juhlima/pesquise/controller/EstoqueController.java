package com.juhlima.pesquise.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juhlima.pesquise.controller.dto.PrecoDTO;
import com.juhlima.pesquise.entity.Estabelecimento;
import com.juhlima.pesquise.entity.Estoque;
import com.juhlima.pesquise.entity.Produto;
import com.juhlima.pesquise.repository.EstabelecimentoRepository;
import com.juhlima.pesquise.repository.EstoqueRepository;
import com.juhlima.pesquise.repository.ProdutoRepository;

@RestController
@RequestMapping("api/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;
    
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @PostMapping
    public ResponseEntity<?> cadastrarPreco(@Valid @RequestBody PrecoDTO precoDTO) {
        Optional<Estabelecimento> optionalEstabelecimento = estabelecimentoRepository.findById(precoDTO.getEstabelecimentoId());
        Optional<Produto> optionalProduto = produtoRepository.findById(precoDTO.getProdutoId());

        if (optionalEstabelecimento.isPresent() && optionalProduto.isPresent()) {
            Optional<Estoque> optionalEstoque = estoqueRepository.findByEstabelecimentoAndProduto(optionalEstabelecimento.get(), optionalProduto.get());

            if (optionalEstoque.isPresent()) {
                Estoque estoqueAtual = optionalEstoque.get();
                estoqueAtual.setPreco(precoDTO.getPreco());

                estoqueRepository.save(estoqueAtual);
                return ResponseEntity.ok("Preço cadastrado com sucesso.");
            } else {
                return ResponseEntity.badRequest().body("Estoque não encontrado para o estabelecimento e produto informados.");
            }
        } else {
            return ResponseEntity.badRequest().body("Estabelecimento ou Produto não encontrado.");
        }
    }

    
    /*
    @PostMapping
    public ResponseEntity<?> cadastrarPreco(@Valid @RequestBody PrecoDTO precoDTO) {
        Optional<Estoque> estoque = estoqueRepository.findByEstabelecimentoAndProduto(precoDTO.getEstabelecimentoId(), precoDTO.getProdutoId());

        if (estoque.isPresent()) {
            Estoque estoqueAtual = estoque.get();
            estoqueAtual.setPreco(precoDTO.getPreco());

            estoqueRepository.save(estoqueAtual);
            return ResponseEntity.ok("Preço cadastrado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Estoque não encontrado para o estabelecimento e produto informados.");
        }
    }*/

    
    /*
    //"/cadastrar-preco"
    @PostMapping()
    public ResponseEntity<String> cadastrarPreco(
        @RequestParam Integer idProduto,
        @RequestParam Integer idEstabelecimento,
        @RequestParam Double preco,
        @RequestParam Integer quantidade
    ) {
        // Busca o objeto Produto no banco de dados
        Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);
        if (!optionalProduto.isPresent()) {
            return ResponseEntity.badRequest().body("Produto não encontrado");
        }
        Produto produto = optionalProduto.get();

        // Busca o objeto Estabelecimento no banco de dados
        Optional<Estabelecimento> optionalEstabelecimento = estabelecimentoRepository.findById(idEstabelecimento);
        if (!optionalEstabelecimento.isPresent()) {
            return ResponseEntity.badRequest().body("Estabelecimento não encontrado");
        }
        Estabelecimento estabelecimento = optionalEstabelecimento.get();

        // Busca o objeto Estoque no banco de dados para verificar se já existe um registro para esse produto e estabelecimento
        Optional<Estoque> optionalEstoque = estoqueRepository.findByProdutoAndEstabelecimento(produto, estabelecimento);

        if (optionalEstoque.isPresent()) {
            // Atualiza o preço e quantidade do produto nesse estoque
            Estoque estoque = optionalEstoque.get();
            estoque.setPreco(preco);
            estoque.setQuantidade(quantidade);
            estoqueRepository.save(estoque);
        } else {
            // Cria um novo objeto Estoque e salva no banco de dados
            Estoque estoque = new Estoque();
            estoque.setProduto(produto);
            estoque.setEstabelecimento(estabelecimento);
            estoque.setPreco(preco);
            estoque.setQuantidade(quantidade);
            estoqueRepository.save(estoque);
        }

        return ResponseEntity.ok().body("Preço cadastrado com sucesso");
    }*/


}