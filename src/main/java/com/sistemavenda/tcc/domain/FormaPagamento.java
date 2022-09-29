package com.sistemavenda.tcc.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "forma_pagamento")
public class FormaPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    @NotNull
    private String descricao;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "venda_id")
    @MapsId
    private Venda venda;

    public FormaPagamento() {
    }

    public FormaPagamento(Integer id, String descricao, Venda venda) {
        this.id = id;
        this.descricao = descricao;
        this.venda = venda;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Venda getVenda() {
        return this.venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FormaPagamento)) {
            return false;
        }
        FormaPagamento formaPagamento = (FormaPagamento) o;
        return Objects.equals(id, formaPagamento.id) && Objects.equals(descricao, formaPagamento.descricao)
                && Objects.equals(venda, formaPagamento.venda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, venda);
    }

}