package com.sistemavenda.tcc.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;

    @CPF
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Venda> compras = new ArrayList<>();


    public Cliente() {
        super();
    }

    public Cliente(Integer id, String nomeCompleto, boolean status, Endereco endereco, List<Contato> contatos, String cpf) {
        super(id, nomeCompleto, status, endereco, contatos);
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Venda> getCompras() {
        return this.compras;
    }

    public void setCompras(List<Venda> compras) {
        this.compras = compras;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cliente)) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf) && Objects.equals(compras, cliente.compras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, compras);
    }
 
}