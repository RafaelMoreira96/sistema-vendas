package com.sistemavenda.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemavenda.tcc.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}