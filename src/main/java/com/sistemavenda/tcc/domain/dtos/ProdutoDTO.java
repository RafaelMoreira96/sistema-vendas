package com.sistemavenda.tcc.domain.dtos;

import javax.validation.constraints.NotNull;

import com.sistemavenda.tcc.domain.Produto;

public class ProdutoDTO {
    private Integer id;

    @NotNull(message = "O campo DESCRICAO deve ser preenchido")
    private String descricao;
    @NotNull(message = "O campo CODBARRAS deve ser preenchido")
    private String codBarras;
    @NotNull(message = "O campo PRECOATACADO deve ser preenchido")
    private double precoAtacado;
    @NotNull(message = "O campo PRECOVAREJO deve ser preenchido")
    private double precoVarejo;
    private boolean status;
    private double qteEstoque;
    private double qteMin;
    private double qteMax;

    public ProdutoDTO() {
        super();
    }

    public ProdutoDTO(Produto p) {
        this.id = p.getId();
        this.descricao = p.getDescricao();
        this.codBarras = p.getCodBarras();
        this.precoAtacado = p.getPrecoAtacado();
        this.precoVarejo = p.getPrecoVarejo();
        this.status = p.getStatus();
        this.qteEstoque = p.getQteEstoque();
        this.qteMin = p.getQteMin();
        this.qteMax = p.getQteMax();
    }

    public boolean isStatus() {
        return this.status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public double getPrecoAtacado() {
        return this.precoAtacado;
    }

    public void setPrecoAtacado(double precoAtacado) {
        this.precoAtacado = precoAtacado;
    }

    public double getPrecoVarejo() {
        return this.precoVarejo;
    }

    public void setPrecoVarejo(double precoVarejo) {
        this.precoVarejo = precoVarejo;
    }

    public double getQteEstoque() {
        return this.qteEstoque;
    }

    public void setQteEstoque(double qteEstoque) {
        this.qteEstoque = qteEstoque;
    }

    public double getQteMin() {
        return this.qteMin;
    }

    public void setQteMin(double qteMin) {
        this.qteMin = qteMin;
    }

    public double getQteMax() {
        return this.qteMax;
    }

    public void setQteMax(double qteMax) {
        this.qteMax = qteMax;
    }

}