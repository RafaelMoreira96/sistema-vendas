package com.sistemavenda.tcc.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.sistemavenda.tcc.domain.enums.StatusVenda;

@Entity
public class Venda {
    private Integer id;
    private Integer numeroVenda;
    private LocalDate dataVenda = LocalDate.now();
    private StatusVenda status;

}
