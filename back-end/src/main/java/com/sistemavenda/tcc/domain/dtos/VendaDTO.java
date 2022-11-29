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

    private Integer idCliente;
    private Integer idFuncionario;
    private String nomeCliente;
    private String nomeFuncionario;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVenda = LocalDate.now();
    private StatusVenda status = StatusVenda.ANDAMENTO;

    private List<ItemVenda> itens = new ArrayList<>();
    private double valorVenda;
    private Integer formaPagamento;

    public VendaDTO() {
    }

    public VendaDTO(Venda v) {
        this.id = v.getId();
        this.numeroVenda = v.getNumeroVenda();
        this.dataVenda = v.getDataVenda();
        this.status = v.getStatus();
        this.idCliente = v.getCliente().getId();
        this.idFuncionario = v.getFuncionario().getId();
        this.itens = v.getItens();
        this.valorVenda = v.getValorVenda();
        this.formaPagamento = v.getFormaPagamento().getId();
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
        return this.idCliente;
    }

    public void setCliente(Integer cliente) {
        this.idCliente = cliente;
    }

    public Integer getFuncionario() {
        return this.idFuncionario;
    }

    public void setFuncionario(Integer funcionario) {
        this.idFuncionario = funcionario;
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

    public Integer getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdFuncionario() {
        return this.idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Integer getFormaPagamento() {
        return this.formaPagamento;
    }

    public void setFormaPagamento(Integer formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

}