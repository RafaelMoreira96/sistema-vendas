package com.sistemavenda.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemavenda.tcc.domain.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

}