package com.juhlima.pesquise.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.juhlima.pesquise.controller.dto.ProdutoDTO;
import com.juhlima.pesquise.entity.Estabelecimento;
import com.juhlima.pesquise.entity.Estoque;
import com.juhlima.pesquise.entity.Produto;
import com.juhlima.pesquise.repository.EstabelecimentoRepository;
import com.juhlima.pesquise.repository.EstoqueRepository;
import com.juhlima.pesquise.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdrutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@PostMapping
	public ResponseEntity<?> cadastrarProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
		Produto produto = new Produto(produtoDTO.getNome(), produtoDTO.getMarca(), produtoDTO.getQtdePorEmbalagem());
		produtoRepository.save(produto);

		Estabelecimento estabelecimento = estabelecimentoRepository.findById(produtoDTO.getEstabelecimentoId())
				.orElseThrow();

		Estoque estoque = new Estoque(estabelecimento, produto, produtoDTO.getQuantidade());
		
		estoqueRepository.save(estoque);

		return ResponseEntity.ok("Produto e estoque cadastrados com sucesso.");
	}

	@GetMapping
	public List<Produto> obterTodos() {
		return produtoRepository.findAll();
	}
	
	@GetMapping("/produtos/baratos")
	public ResponseEntity<List<Estoque>> buscarProdutosMaisBaratosPorEstabelecimento(@RequestParam("estabelecimentoId") Integer estabelecimentoId) {
	    Optional<Estabelecimento> optionalEstabelecimento = estabelecimentoRepository.findById(estabelecimentoId);

	    if (optionalEstabelecimento.isPresent()) {
	        List<Object[]> produtosMaisBaratos = estoqueRepository.buscarProdutosMaisBaratosPorEstabelecimento(optionalEstabelecimento.get().getId_estabelecimento());

	        List<Estoque> estoques = produtosMaisBaratos.stream().map(obj -> {
	            Produto produto = (Produto) obj[0];
	            Estoque estoque = (Estoque) obj[1];
	            estoque.setProduto(produto);
	            return estoque;
	        }).collect(Collectors.toList());

	        return ResponseEntity.ok(estoques);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@GetMapping("{id}")
	public Produto acharPorId(@PathVariable Integer id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		produtoRepository.findById(id).map(produto -> {
			produtoRepository.delete(produto);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
}
