package com.sistemavenda.tcc.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class Fornecedor extends Pessoa {
    private static final long serialVersionUID = 1L;

    private String nomeFantasia;
    private String inscricaoEstadual;

    public Fornecedor() {
        super();
    }

    public Fornecedor(Integer id, String cnpj, String cpf, String nomeCompleto, Endereco endereco,
            List<Contato> contatos, String nome, String nomeFantasia, String inscricaoEstadual) {
        super(id, cnpj, cpf, nome, endereco, contatos);
        this.nomeFantasia = nomeFantasia;
        this.inscricaoEstadual = inscricaoEstadual;
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
