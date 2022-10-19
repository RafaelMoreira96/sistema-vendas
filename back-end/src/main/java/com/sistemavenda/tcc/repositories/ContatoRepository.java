package com.sistemavenda.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemavenda.tcc.domain.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {

}