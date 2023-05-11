package com.juhlima.pesquise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.juhlima.pesquise.entity.Estabelecimento;
import com.juhlima.pesquise.entity.Estoque;
import com.juhlima.pesquise.entity.Produto;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

	Optional<Estoque> findByEstabelecimentoAndProduto(Estabelecimento estabelecimento, Produto produto); 

	@Query("SELECT e.produto, MIN(e.preco) FROM Estoque e WHERE e.estabelecimento.id = :estabelecimentoId GROUP BY e.produto.id ORDER BY MIN(e.preco) ASC")
	List<Object[]> buscarProdutosMaisBaratosPorEstabelecimento(@Param("estabelecimentoId") Integer estabelecimentoId);

}
