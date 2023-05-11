package com.juhlima.pesquise.controller.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrecoDTO {

	@NotNull
	private Integer estabelecimentoId;

	@NotNull
	private Integer produtoId;

	@NotNull
	private BigDecimal preco;
	
	

}
