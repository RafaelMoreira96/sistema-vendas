package com.sistemavenda.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemavenda.tcc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}