package com.sistemavenda.tcc.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item_compra")
public class ItemCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer idProduto;
    @NotNull
    private String descricao;
    @NotNull
    private String codBarras;
    @NotNull
    private double precoCompra;
    @NotNull
    private double quant;

    public ItemCompra() {
    }

    public ItemCompra(Integer id, Integer idProduto, String descricao, String codBarras, double precoCompra,
            double quant) {
        this.id = id;
        this.descricao = descricao;
        this.codBarras = codBarras;
        this.precoCompra = precoCompra;
        this.quant = quant;
        this.idProduto = idProduto;
    }

    public Integer getIdProduto() {
        return this.idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
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

    public double getPrecoCompra() {
        return this.precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public double getQuant() {
        return this.quant;
    }

    public void setQuant(double quant) {
        this.quant = quant;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ItemCompra)) {
            return false;
        }
        ItemCompra itemCompra = (ItemCompra) o;
        return Objects.equals(id, itemCompra.id) && Objects.equals(descricao, itemCompra.descricao)
                && Objects.equals(codBarras, itemCompra.codBarras) && precoCompra == itemCompra.precoCompra
                && quant == itemCompra.quant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, codBarras, precoCompra, quant);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", descricao='" + getDescricao() + "'" +
                ", codBarras='" + getCodBarras() + "'" +
                ", precoCompra='" + getPrecoCompra() + "'" +
                ", quant='" + getQuant() + "'" +
                "}";
    }

}
