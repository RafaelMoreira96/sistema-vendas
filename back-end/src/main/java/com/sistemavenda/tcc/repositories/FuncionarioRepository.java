package com.sistemavenda.tcc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemavenda.tcc.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    
    Optional<Funcionario> findByCpf(String cpf);
    Optional<Funcionario> findByNomeUsuario(String nomeUsuario);
}