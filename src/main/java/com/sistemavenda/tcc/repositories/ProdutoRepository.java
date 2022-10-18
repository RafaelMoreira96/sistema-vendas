package com.sistemavenda.tcc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemavenda.tcc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Optional<Produto> findByCodBarras(String codBarras);

    Produto save(Optional<Produto> p);
}