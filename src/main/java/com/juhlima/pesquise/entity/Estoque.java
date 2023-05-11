package com.juhlima.pesquise.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "estabelecimento_id")	
	private Estabelecimento estabelecimento;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	private BigDecimal preco;

	private Integer quantidade;

	public Estoque(Estabelecimento estabelecimento, Produto produto, Integer quantidade) {
		super();
		this.estabelecimento = estabelecimento;
		this.produto = produto;
		this.quantidade = quantidade;
	}

	
	

}
