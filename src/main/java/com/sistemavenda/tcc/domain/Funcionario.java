package com.sistemavenda.tcc.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
        super();
    }

    public Funcionario(Integer id, String cnpj, String cpf, String nomeCompleto, Endereco endereco,
            List<Contato> contatos, String nome, String nomeUsuario,
            String senha, Set<Integer> nivelAuth, List<Venda> vendas) {
        super(id, cnpj, cpf, nome, endereco, contatos);
        this.id = id;
        this.nome = nomeCompleto;
        this.endereco = endereco;
        this.contatos = contatos;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.vendas = vendas;
        setAuth(NivelAuth.PADRAO);
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

    public void setAuth(NivelAuth nivelAuth) {
        this.nivelAuth.add(nivelAuth.getCodigo());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Funcionario)) {
            return false;
        }
        Funcionario funcionario = (Funcionario) o;
        return Objects.equals(nomeUsuario, funcionario.nomeUsuario) && Objects.equals(senha, funcionario.senha)
                && Objects.equals(dataDemissao, funcionario.dataDemissao)
                && Objects.equals(dataAdmissao, funcionario.dataAdmissao)
                && Objects.equals(nivelAuth, funcionario.nivelAuth) && Objects.equals(vendas, funcionario.vendas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeUsuario, senha, dataDemissao, dataAdmissao, nivelAuth, vendas);
    }

    @Override
    public String toString() {
        return "{" +
                " nomeUsuario='" + getNomeUsuario() + "'" +
                ", senha='" + getSenha() + "'" +
                ", dataDemissao='" + getDataDemissao() + "'" +
                ", dataAdmissao='" + getDataAdmissao() + "'" +
                ", nivelAuth='" + getNivelAuth() + "'" +
                ", vendas='" + getVendas() + "'" +
                "}";
    }

}