package com.juhlima.pesquise.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoDTO {
	
	private String nome;
	private String marca;
	private String qtdePorEmbalagem;
	private Integer quantidade;
	private Integer estabelecimentoId;

}
