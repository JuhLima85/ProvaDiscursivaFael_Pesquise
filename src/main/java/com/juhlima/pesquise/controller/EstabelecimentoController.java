package com.juhlima.pesquise.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.juhlima.pesquise.entity.Estabelecimento;
import com.juhlima.pesquise.repository.EstabelecimentoRepository;

@RestController
@RequestMapping("/api/estabelecimentos")
public class EstabelecimentoController {
	
	@Autowired
	private EstabelecimentoRepository repository;
		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estabelecimento salvar(@RequestBody @Valid Estabelecimento estabelecimento) {
		return repository.save(estabelecimento);
	}
	
	@GetMapping
	public List<Estabelecimento> obterTodos(){
		return repository.findAll();
	}
	
	 @GetMapping("{id}")
		public Estabelecimento acharPorId(@PathVariable Integer id) {
			return repository
					.findById(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado"));
		}
	 
	  @DeleteMapping("{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deletar(@PathVariable Integer id) {
	    	repository
	    	   .findById(id)
	    	   .map(estabelecimento -> {
	    		   repository.delete(estabelecimento);
	    		   return Void.TYPE;
	    	   })
	    	   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado"));
	    }
	  
	  @PutMapping("{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Estabelecimento estabelecimentoAtualizado) {
	    	repository
	 	           .findById(id)
	 	           .map(estabelecimento -> {
	 	        	  estabelecimento.setNomeFantasia(estabelecimentoAtualizado.getNomeFantasia());
	 	        	 estabelecimento.setEndereco(estabelecimentoAtualizado.getEndereco());
	 	        	 estabelecimento.setCidade(estabelecimentoAtualizado.getCidade());
	 	        	estabelecimento.setQtdeLojas(estabelecimentoAtualizado.getQtdeLojas());
	 		          return repository.save(estabelecimento);
	 	   })
	 	   .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado"));
	    }
	 

}
