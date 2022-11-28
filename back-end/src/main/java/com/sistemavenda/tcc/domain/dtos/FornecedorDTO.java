package com.sistemavenda.tcc.domain.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistemavenda.tcc.domain.Contato;
import com.sistemavenda.tcc.domain.Endereco;
import com.sistemavenda.tcc.domain.Fornecedor;

public class FornecedorDTO {
    private Integer id;

    @NotNull(message = "O campo NOME deve ser preenchido")
    private String nome; // ou razao social
    @NotNull(message = "O campo NOME FANTASIA deve ser preenchido")
    private String nomeFantasia;
    @NotNull(message = "O campo INSCRICAO ESTADUAL deve ser preenchido")
    private String inscricaoEstadual;
    @NotNull(message = "O campo CNPJ deve ser preenchido")
    private String cnpj;

    @NotNull(message = "O campo ENDERECO deve ser preenchido")
    private Endereco endereco;

    private List<Contato> contatos = new ArrayList<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro = LocalDate.now();
    private boolean status;

    public FornecedorDTO() {
    }

    public FornecedorDTO(Fornecedor f) {
        this.id = f.getId();
        this.nome = f.getNome();
        this.cnpj = f.getCnpj();
        this.endereco = f.getEndereco();
        this.contatos = f.getContatos();
        this.nomeFantasia = f.getNomeFantasia();
        this.inscricaoEstadual = f.getInscricaoEstadual();
        this.status = f.getStatus();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
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

    public boolean isStatus() {
        return this.status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}