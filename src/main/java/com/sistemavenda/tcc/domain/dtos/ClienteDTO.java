package com.sistemavenda.tcc.domain.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistemavenda.tcc.domain.Cliente;
import com.sistemavenda.tcc.domain.Contato;
import com.sistemavenda.tcc.domain.Endereco;

public class ClienteDTO {
    private Integer id;

    @NotNull(message = "O campo 'NOME' precisa ser preenchido")
    private String nome; // ou razao social
    @NotNull(message = "O campo 'CPF' precisa ser preenchido")
    private String cpf;
    @NotNull(message = "O campo 'ENDERECO' precisa ser preenchido")
    private Endereco endereco;
    @NotNull(message = "O campo 'CONTATOS' precisa ser preenchido")
    private List<Contato> contatos = new ArrayList<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro = LocalDate.now();

    private boolean status = true;

    public ClienteDTO() {
        super();
    }

    public ClienteDTO(Cliente c) {
        this.id = c.getId();
        this.nome = c.getNome();
        this.cpf = c.getCpf();
        this.endereco = c.getEndereco();
        this.contatos = c.getContatos();
        this.dataCadastro = c.getDataCadastro();
        this.status = c.getStatus();
    }

    public ClienteDTO(Optional<Cliente> c) {
        this.id = c.get().getId();
        this.nome = c.get().getNome();
        this.cpf = c.get().getCpf();
        this.endereco = c.get().getEndereco();
        this.contatos = c.get().getContatos();
        this.dataCadastro = c.get().getDataCadastro();
        this.status = c.get().getStatus();
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