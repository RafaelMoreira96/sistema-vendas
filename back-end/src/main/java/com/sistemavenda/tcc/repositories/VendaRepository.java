package com.sistemavenda.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemavenda.tcc.domain.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

}