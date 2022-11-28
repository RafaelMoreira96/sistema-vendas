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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistemavenda.tcc.domain.dtos.VendaDTO;
import com.sistemavenda.tcc.domain.enums.StatusVenda;

@Entity
@Table(name = "venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer numeroVenda;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVenda = LocalDate.now();

    private StatusVenda status = StatusVenda.ANDAMENTO;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @OneToMany
    private List<ItemVenda> itens = new ArrayList<>();

    private double valorVenda;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id")
    private FormaPagamento formaPagamento;

    public Venda() {
    }

    public Venda(Integer id, Integer numeroVenda, Cliente cliente,
            Funcionario funcionario, List<ItemVenda> itens, FormaPagamento formaPagamento) {
        this.id = id;
        this.numeroVenda = numeroVenda;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.itens = itens;
        this.formaPagamento = formaPagamento;
    }

    public Venda(VendaDTO vDTO) {
        this.id = vDTO.getId();
        this.numeroVenda = vDTO.getNumeroVenda();
        this.itens = vDTO.getItens();
        this.formaPagamento = vDTO.getFormaPagamento();
    }

    public List<ItemVenda> getItens() {
        return this.itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public double getValorVenda() {
        return this.valorVenda;
    }

    public void setValorVenda(List<ItemVenda> itens) {
        for (ItemVenda itemVenda : itens) {
            this.valorVenda += (itemVenda.getPrecoVendido() * itemVenda.getQuant());
        }
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
        return this.itens;
    }

    public void setListaProdutos(List<ItemVenda> produtos) {
        this.itens = produtos;
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
                && Objects.equals(itens, venda.itens)
                && Objects.equals(formaPagamento, venda.formaPagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroVenda, dataVenda, status, cliente, funcionario, itens, formaPagamento);
    }

}
