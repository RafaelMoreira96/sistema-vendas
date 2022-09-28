package com.sistemavenda.tcc.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemavenda.tcc.domain.enums.NivelAuth;

@Entity
public class Funcionario extends Pessoa {

    @NotNull
    private String nomeUsuario;

    @NotNull
    private String senha;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDemissao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAdmissao = LocalDate.now();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "NIVELAUTH")
    private Set<Integer> nivelAuth = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Venda> vendas = new ArrayList<>();

    public Funcionario() {
    }

    public Funcionario(String nomeUsuario, String senha, LocalDate dataDemissao, LocalDate dataAdmissao,
            Set<Integer> nivelAuth, List<Venda> vendas) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.dataDemissao = dataDemissao;
        this.dataAdmissao = dataAdmissao;
        this.nivelAuth = nivelAuth;
        this.vendas = vendas;
    }

    public List<Venda> getVendas() {
        return this.vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
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

    public Set<NivelAuth> getNivelAuth() {
        return nivelAuth.stream().map(x -> NivelAuth.toEnum(x)).collect(Collectors.toSet());
    }

    public void setNivelAuth(NivelAuth nivelAuth) {
        this.nivelAuth.add(nivelAuth.getCodigo());
    }

}