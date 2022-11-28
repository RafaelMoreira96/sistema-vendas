package com.sistemavenda.tcc.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item_venda")
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer idProduto;
    @NotNull
    private String descricao;
    @NotNull
    private String codBarras;
    @NotNull
    private double precoVendido;
    @NotNull
    private double quant;

    public ItemVenda() {
    }

    public ItemVenda(Integer id, Integer idProduto, String descricao, String codBarras, double precoVendido,
            double quant) {
        this.id = id;
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.codBarras = codBarras;
        this.precoVendido = precoVendido;
        this.quant = quant;
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

    public double getPrecoVendido() {
        return this.precoVendido;
    }

    public void setPrecoVendido(double precoVendido) {
        this.precoVendido = precoVendido;
    }

    public double getQuant() {
        return this.quant;
    }

    public void setQuant(double quant) {
        this.quant = quant;
    }

    public ItemVenda id(Integer id) {
        setId(id);
        return this;
    }

    public ItemVenda descricao(String descricao) {
        setDescricao(descricao);
        return this;
    }

    public ItemVenda codBarras(String codBarras) {
        setCodBarras(codBarras);
        return this;
    }

    public ItemVenda precoVendido(double precoVendido) {
        setPrecoVendido(precoVendido);
        return this;
    }

    public ItemVenda quant(double quant) {
        setQuant(quant);
        return this;
    }

    public Integer getIdProduto() {
        return this.idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ItemVenda)) {
            return false;
        }
        ItemVenda itemVenda = (ItemVenda) o;
        return Objects.equals(id, itemVenda.id) && Objects.equals(idProduto, itemVenda.idProduto)
                && Objects.equals(descricao, itemVenda.descricao) && Objects.equals(codBarras, itemVenda.codBarras)
                && precoVendido == itemVenda.precoVendido && quant == itemVenda.quant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idProduto, descricao, codBarras, precoVendido, quant);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", idProduto='" + getIdProduto() + "'" +
                ", descricao='" + getDescricao() + "'" +
                ", codBarras='" + getCodBarras() + "'" +
                ", precoVendido='" + getPrecoVendido() + "'" +
                ", quant='" + getQuant() + "'" +
                "}";
    }

}