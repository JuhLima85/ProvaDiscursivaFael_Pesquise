package com.juhlima.pesquise.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_produto;
	private String nome;
	private String marca;
	private String qtdePorEmbalagem;
	
	public Produto(String nome, String marca, String qtdePorEmbalagem) {
		super();
		this.nome = nome;
		this.marca = marca;
		this.qtdePorEmbalagem = qtdePorEmbalagem;
	}


}
