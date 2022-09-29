package com.sistemavenda.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemavenda.tcc.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}