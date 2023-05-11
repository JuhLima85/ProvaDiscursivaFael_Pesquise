package com.juhlima.pesquise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juhlima.pesquise.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
