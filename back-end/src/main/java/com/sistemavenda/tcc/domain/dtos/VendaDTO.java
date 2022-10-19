package com.sistemavenda.tcc.domain.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistemavenda.tcc.domain.FormaPagamento;
import com.sistemavenda.tcc.domain.ItemVenda;
import com.sistemavenda.tcc.domain.Venda;
import com.sistemavenda.tcc.domain.enums.StatusVenda;

public class VendaDTO {
    private Integer id;
    private Integer numeroVenda;

    private Integer cliente;
    private Integer funcionario;
    private String nomeCliente;
    private String nomeFuncionario;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVenda = LocalDate.now();
    private StatusVenda status = StatusVenda.ANDAMENTO;

    private List<ItemVenda> itens = new ArrayList<>();
    private double valorVenda;
    private FormaPagamento formaPagamento;

    public VendaDTO() {
    }

    public VendaDTO(Venda v) {
        this.id = v.getId();
        this.numeroVenda = v.getNumeroVenda();
        this.dataVenda = v.getDataVenda();
        this.status = v.getStatus();
        this.cliente = v.getCliente().getId();
        this.funcionario = v.getFuncionario().getId();
        this.itens = v.getItens();
        this.valorVenda = v.getValorVenda();
        this.formaPagamento = v.getFormaPagamento();
        this.nomeCliente = v.getCliente().getNome();
        this.nomeFuncionario = v.getFuncionario().getNome();
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeFuncionario() {
        return this.nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
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

    public Integer getCliente() {
        return this.cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Integer funcionario) {
        this.funcionario = funcionario;
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

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public FormaPagamento getFormaPagamento() {
        return this.formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

}