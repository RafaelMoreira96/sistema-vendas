package com.sistemavenda.tcc.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistemavenda.tcc.domain.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    @Query("SELECT v FROM Venda v WHERE v.dataVenda BETWEEN :dataInicial AND :dataFinal AND v.status = 2")
    List<Venda> findByDataVendaBetween(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);

    @Query("SELECT v FROM Venda v WHERE v.dataVenda BETWEEN :dataInicial AND :dataFinal AND v.status = 1")
    List<Venda> findCancelVendasByDate(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);
}