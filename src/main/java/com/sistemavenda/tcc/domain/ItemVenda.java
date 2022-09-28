package com.sistemavenda.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    @NotNull
    private String descricao;
    @NotNull
    private String codBarras;
    @NotNull
    private Double precoVendido;
    @NotNull
    private Double quant;


}