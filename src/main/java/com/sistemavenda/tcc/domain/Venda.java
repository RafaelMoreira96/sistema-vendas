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
import com.sistemavenda.tcc.domain.enums.StatusVenda;

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private Integer numeroVenda;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVenda = LocalDate.now();

    private StatusVenda status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @OneToMany
    private List<ItemVenda> listaProdutos = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id")
    private FormaPagamento formaPagamento;

    public Venda() {
    }

    public Venda(Integer id, Integer numeroVenda, LocalDate dataVenda, StatusVenda status, Cliente cliente,
            Funcionario funcionario, List<ItemVenda> listaProdutos, FormaPagamento formaPagamento) {
        this.id = id;
        this.numeroVenda = numeroVenda;
        this.dataVenda = dataVenda;
        this.status = status;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.listaProdutos = listaProdutos;
        this.formaPagamento = formaPagamento;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroVenda() {
        return this.numeroVenda;
    }

    public void setNumeroVenda(Integer numeroVenda) {
        this.numeroVenda = numeroVenda;
    }

    public LocalDate getDataVenda() {
        return this.dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public StatusVenda getStatus() {
        return this.status;
    }

    public void setStatus(StatusVenda status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<ItemVenda> getListaProdutos() {
        return this.listaProdutos;
    }

    public void setListaProdutos(List<ItemVenda> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public FormaPagamento getFormaPagamento() {
        return this.formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Venda)) {
            return false;
        }
        Venda venda = (Venda) o;
        return Objects.equals(id, venda.id) && Objects.equals(numeroVenda, venda.numeroVenda)
                && Objects.equals(dataVenda, venda.dataVenda) && Objects.equals(status, venda.status)
                && Objects.equals(cliente, venda.cliente) && Objects.equals(funcionario, venda.funcionario)
                && Objects.equals(listaProdutos, venda.listaProdutos)
                && Objects.equals(formaPagamento, venda.formaPagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroVenda, dataVenda, status, cliente, funcionario, listaProdutos, formaPagamento);
    }

}
