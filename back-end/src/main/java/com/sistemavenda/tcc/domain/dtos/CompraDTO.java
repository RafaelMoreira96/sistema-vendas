package com.sistemavenda.tcc.domain.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistemavenda.tcc.domain.Compra;
import com.sistemavenda.tcc.domain.ItemCompra;
import com.sistemavenda.tcc.domain.enums.StatusCompra;

public class CompraDTO {
    private Integer id;
    private Integer numeroCompra;

    private Integer fornecedor;
    private Integer funcionario;
    private String nomeFornecedor;
    private String nomeFuncionario;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVenda = LocalDate.now();
    private StatusCompra status = StatusCompra.ANDAMENTO;

    private List<ItemCompra> itens = new ArrayList<>();
    private double valorCompra;

    public CompraDTO() {
    }

    public CompraDTO(Compra c) {
        this.id = c.getId();
        this.numeroCompra = c.getNumeroCompra();
        this.fornecedor = c.getFornecedor().getId();
        this.funcionario = c.getFuncionario().getId();
        this.nomeFornecedor = c.getFornecedor().getNome();
        this.nomeFuncionario = c.getFuncionario().getNome();
        this.itens = c.getItens();
        this.valorCompra = c.getValorTotal();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroCompra() {
        return this.numeroCompra;
    }

    public void setNumeroCompra(Integer numeroCompra) {
        this.numeroCompra = numeroCompra;
    }

    public Integer getFornecedor() {
        return this.fornecedor;
    }

    public void setFornecedor(Integer fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Integer getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Integer funcionario) {
        this.funcionario = funcionario;
    }

    public String getNomeFornecedor() {
        return this.nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getNomeFuncionario() {
        return this.nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public LocalDate getDataVenda() {
        return this.dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public StatusCompra getStatus() {
        return this.status;
    }

    public void setStatus(StatusCompra status) {
        this.status = status;
    }

    public List<ItemCompra> getItens() {
        return this.itens;
    }

    public void setItens(List<ItemCompra> itens) {
        this.itens = itens;
    }

    public double getValorVenda() {
        return this.valorCompra;
    }

    public void setValorVenda(double valorVenda) {
        this.valorCompra = valorVenda;
    }

}
