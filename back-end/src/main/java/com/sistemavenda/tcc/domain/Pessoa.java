package com.sistemavenda.tcc.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Integer id;

    @NotNull(message = "O campo NOME não pode ficar vazio")
    protected String nome; // ou razao social

    @CNPJ
    protected String cnpj;
    @CPF
    protected String cpf;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    protected Endereco endereco;

    @OneToMany
    @JoinColumn(name = "pessoa_id")
    protected List<Contato> contatos = new ArrayList<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCadastro = LocalDate.now();

    public Pessoa() {
        super();
    }

    public Pessoa(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Pessoa(Integer id, String cnpj, String cpf, String nome, Endereco endereco, List<Contato> contatos) {
        super();
        this.id = id;
        this.cnpj = cnpj;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.contatos = contatos;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public LocalDate getDataCadastro() {
        return this.dataCadastro;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pessoa)) {
            return false;
        }
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(cnpj, pessoa.cnpj) && Objects.equals(cpf, pessoa.cpf)
                && Objects.equals(nome, pessoa.nome) && Objects.equals(endereco, pessoa.endereco)
                && Objects.equals(contatos, pessoa.contatos) && Objects.equals(dataCadastro, pessoa.dataCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj, cpf, nome, endereco, contatos, dataCadastro);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", cnpj='" + getCnpj() + "'" +
                ", cpf='" + getCpf() + "'" +
                ", nome='" + getNome() + "'" +
                ", endereco='" + getEndereco() + "'" +
                ", contatos='" + getContatos() + "'" +
                ", dataCadastro='" + getDataCadastro() + "'" +
                "}";
    }

}