package com.sistemavenda.tcc.domain.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistemavenda.tcc.domain.Contato;
import com.sistemavenda.tcc.domain.Endereco;
import com.sistemavenda.tcc.domain.Funcionario;

public class FuncionarioDTO {
    private Integer id;

    @NotNull(message = "O campo NOME não pode ficar vazio")
    protected String nome; // ou razao social
    @NotNull(message = "O campo 'CPF' precisa ser preenchido")
    private String cpf;
    @NotNull(message = "O campo 'ENDERECO' precisa ser preenchido")
    private Endereco endereco;
    @NotNull(message = "O campo 'CONTATOS' precisa ser preenchido")
    protected List<Contato> contatos = new ArrayList<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCadastro = LocalDate.now();
    @NotNull(message = "O campo 'NOMEUSUARIO' precisa ser preenchido")
    private String nomeUsuario;
    @NotNull(message = "O campo 'NOMEUSUARIO' precisa ser preenchido")
    private String senha;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDemissao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAdmissao = LocalDate.now();
    private Set<Integer> nivelAuth = new HashSet<>();

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Funcionario f) {
        this.id = f.getId();
        this.nome = f.getNome();
        this.cpf = f.getCpf();
        this.endereco = f.getEndereco();
        this.contatos = f.getContatos();
        this.dataDemissao = f.getDataDemissao();
        this.nomeUsuario = f.getNomeUsuario();
        this.senha = f.getSenha();
        this.nivelAuth = f.getNivelAuth().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
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

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getNomeUsuario() {
        return this.nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataDemissao() {
        return this.dataDemissao;
    }

    public void setDataDemissao(LocalDate dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public LocalDate getDataAdmissao() {
        return this.dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Set<Integer> getNivelAuth() {
        return this.nivelAuth;
    }

    public void setNivelAuth(Set<Integer> nivelAuth) {
        this.nivelAuth = nivelAuth;
    }

}