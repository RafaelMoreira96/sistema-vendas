package com.sistemavenda.tcc.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistemavenda.tcc.domain.Compra;

public interface CompraRepository extends JpaRepository<Compra, Integer> {
    @Query("SELECT v FROM Compra v WHERE v.dataCompra BETWEEN :dataInicial AND :dataFinal")
    List<Compra> findByDataVendaBetween(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);
}