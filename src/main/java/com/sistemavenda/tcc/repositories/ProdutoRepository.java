package com.sistemavenda.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemavenda.tcc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}