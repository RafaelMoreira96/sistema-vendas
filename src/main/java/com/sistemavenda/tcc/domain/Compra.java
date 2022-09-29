package com.sistemavenda.tcc.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCompra;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    private double valorTotal;

    @JsonIgnore
    @OneToMany(mappedBy = "compra")
    private List<ItemCompra> produtos = new ArrayList<>();

    public Compra() {
    }

    public Compra(Integer id, LocalDate dataCompra, Fornecedor fornecedor, Funcionario funcionario, double valorTotal,
            List<ItemCompra> produtos) {
        this.id = id;
        this.dataCompra = dataCompra;
        this.fornecedor = fornecedor;
        this.funcionario = funcionario;
        this.valorTotal = valorTotal;
        this.produtos = produtos;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataCompra() {
        return this.dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Fornecedor getFornecedor() {
        return this.fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public double getValorTotal() {
        return this.valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemCompra> getProdutos() {
        return this.produtos;
    }

    public void setProdutos(List<ItemCompra> produtos) {
        this.produtos = produtos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Compra)) {
            return false;
        }
        Compra compra = (Compra) o;
        return Objects.equals(id, compra.id) && Objects.equals(dataCompra, compra.dataCompra)
                && Objects.equals(fornecedor, compra.fornecedor) && Objects.equals(funcionario, compra.funcionario)
                && valorTotal == compra.valorTotal && Objects.equals(produtos, compra.produtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCompra, fornecedor, funcionario, valorTotal, produtos);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", dataCompra='" + getDataCompra() + "'" +
                ", fornecedor='" + getFornecedor() + "'" +
                ", funcionario='" + getFuncionario() + "'" +
                ", valorTotal='" + getValorTotal() + "'" +
                ", produtos='" + getProdutos() + "'" +
                "}";
    }

}