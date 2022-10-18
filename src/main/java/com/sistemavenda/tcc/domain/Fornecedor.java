package com.sistemavenda.tcc.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class Fornecedor extends Pessoa {
    private String nomeFantasia;
    private String inscricaoEstadual;
    private boolean status = true;

    public Fornecedor() {
        super();
    }

    public Fornecedor(Integer id, String nome) {
        super(id, nome);
    }

    public Fornecedor(Integer id, String cnpj, String cpf, String nome, Endereco endereco,
            List<Contato> contatos, String nomeFantasia, String inscricaoEstadual) {
        super(id, cnpj, cpf, nome, endereco, contatos);
        this.nomeFantasia = nomeFantasia;
        this.inscricaoEstadual = inscricaoEstadual;
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

    public String getNomeFantasia() {
        return this.nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getInscricaoEstadual() {
        return this.inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Fornecedor)) {
            return false;
        }
        Fornecedor fornecedor = (Fornecedor) o;
        return Objects.equals(nomeFantasia, fornecedor.nomeFantasia)
                && Objects.equals(inscricaoEstadual, fornecedor.inscricaoEstadual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeFantasia, inscricaoEstadual);
    }

    @Override
    public String toString() {
        return "{" +
                " cnpj='" + getCnpj() + "'" +
                ", nomeFantasia='" + getNomeFantasia() + "'" +
                ", inscricaoEstadual='" + getInscricaoEstadual() + "'" +
                "}";
    }

}
