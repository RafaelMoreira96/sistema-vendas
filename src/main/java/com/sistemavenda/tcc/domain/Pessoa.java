package com.sistemavenda.tcc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Integer id;

    @NotNull(message = "O campo NOME não pode ficar vazio")
    protected String nomeCompleto;

    protected boolean status;
    protected Endereco endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa")
    protected List<Contato> contatos = new ArrayList<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public Pessoa() {
    }

    public Pessoa(Integer id, String nomeCompleto, boolean status, Endereco endereco, List<Contato> contatos) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.status = status;
        this.endereco = endereco;
        this.contatos = contatos;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
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

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Contato> getContatos() {
        return this.contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pessoa)) {
            return false;
        }
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(nomeCompleto, pessoa.nomeCompleto)
                && status == pessoa.status && Objects.equals(endereco, pessoa.endereco)
                && Objects.equals(contatos, pessoa.contatos) && Objects.equals(dataCriacao, pessoa.dataCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeCompleto, status, endereco, contatos, dataCriacao);
    }

}