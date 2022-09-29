package com.sistemavenda.tcc.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    @NotNull
    private String descricao;
    @NotNull
    private String codBarras;
    @NotNull
    private double precoAtacado;
    @NotNull
    private double precoVarejo;

    private double qteEstoque;
    private double qteMin;
    private double qteMax;

    public Produto() {
    }

    public Produto(Integer id, String descricao, String codBarras, Double precoAtacado, Double precoVarejo,
            Double qteEstoque, Double qteMin, Double qteMax) {
        this.id = id;
        this.descricao = descricao;
        this.codBarras = codBarras;
        this.precoAtacado = precoAtacado;
        this.precoVarejo = precoVarejo;
        this.qteEstoque = qteEstoque;
        this.qteMin = qteMin;
        this.qteMax = qteMax;
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

    public String getCodBarras() {
        return this.codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public Double getPrecoAtacado() {
        return this.precoAtacado;
    }

    public void setPrecoAtacado(Double precoAtacado) {
        this.precoAtacado = precoAtacado;
    }

    public Double getPrecoVarejo() {
        return this.precoVarejo;
    }

    public void setPrecoVarejo(Double precoVarejo) {
        this.precoVarejo = precoVarejo;
    }

    public Double getQteEstoque() {
        return this.qteEstoque;
    }

    public void setQteEstoque(Double qteEstoque) {
        this.qteEstoque = qteEstoque;
    }

    public Double getQteMin() {
        return this.qteMin;
    }

    public void setQteMin(Double qteMin) {
        this.qteMin = qteMin;
    }

    public Double getQteMax() {
        return this.qteMax;
    }

    public void setQteMax(Double qteMax) {
        this.qteMax = qteMax;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Produto)) {
            return false;
        }
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(descricao, produto.descricao)
                && Objects.equals(codBarras, produto.codBarras) && Objects.equals(precoAtacado, produto.precoAtacado)
                && Objects.equals(precoVarejo, produto.precoVarejo) && Objects.equals(qteEstoque, produto.qteEstoque)
                && Objects.equals(qteMin, produto.qteMin) && Objects.equals(qteMax, produto.qteMax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, codBarras, precoAtacado, precoVarejo, qteEstoque, qteMin, qteMax);
    }

}