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
import com.sistemavenda.tcc.domain.dtos.CompraDTO;
import com.sistemavenda.tcc.domain.enums.StatusCompra;

@Entity
@Table(name = "compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer numeroCompra;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCompra = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @OneToMany
    private List<ItemCompra> itens = new ArrayList<>();
    private StatusCompra status = StatusCompra.ANDAMENTO;

    private double valorTotal;

    public Compra() {
    }

    public Compra(Integer id, Fornecedor fornecedor, Funcionario funcionario, double valorTotal,
            List<ItemCompra> itens) {
        this.id = id;
        this.fornecedor = fornecedor;
        this.funcionario = funcionario;
        this.valorTotal = valorTotal;
        this.itens = itens;
    }

    public Compra(CompraDTO cDTO) {
        this.id = cDTO.getId();
        this.numeroCompra = cDTO.getNumeroCompra();
        this.itens = cDTO.getItens();
    }

    public StatusCompra getStatus() {
        return this.status;
    }

    public void setStatus(StatusCompra status) {
        this.status = status;
    }

    public Integer getNumeroCompra() {
        return this.numeroCompra;
    }

    public void setNumeroCompra(Integer numeroCompra) {
        this.numeroCompra = numeroCompra;
    }

    public List<ItemCompra> getItens() {
        return this.itens;
    }

    public void setItens(List<ItemCompra> itens) {
        this.itens = itens;
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

    public void setValorTotal(List<ItemCompra> itens) {
        for (ItemCompra itemCompra : itens) {
            this.valorTotal += (itemCompra.getPrecoCompra() * itemCompra.getQuant());
        }
    }

    public List<ItemCompra> getProdutos() {
        return this.itens;
    }

    public void setProdutos(List<ItemCompra> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Compra)) {
            return false;
        }
        Compra compra = (Compra) o;
        return Objects.equals(id, compra.id) && Objects.equals(numeroCompra, compra.numeroCompra)
                && Objects.equals(dataCompra, compra.dataCompra) && Objects.equals(fornecedor, compra.fornecedor)
                && Objects.equals(funcionario, compra.funcionario) && valorTotal == compra.valorTotal
                && Objects.equals(itens, compra.itens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroCompra, dataCompra, fornecedor, funcionario, valorTotal, itens);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", numeroCompra='" + getNumeroCompra() + "'" +
                ", dataCompra='" + getDataCompra() + "'" +
                ", fornecedor='" + getFornecedor() + "'" +
                ", funcionario='" + getFuncionario() + "'" +
                ", valorTotal='" + getValorTotal() + "'" +
                ", itens='" + getItens() + "'" +
                "}";
    }

}