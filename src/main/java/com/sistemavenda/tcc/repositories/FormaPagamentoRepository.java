package com.sistemavenda.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemavenda.tcc.domain.FormaPagamento;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer> {

}