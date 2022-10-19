package com.sistemavenda.tcc.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente extends Pessoa {
    private boolean status = true;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Venda> compras = new ArrayList<>();

    public Cliente() {
        super();
    }
    public Cliente(Integer id, String nome) {
        super(id, nome);
    }
   
    public Cliente(Integer id, String cnpj, String cpf, String nomeCompleto, Endereco endereco,
            List<Contato> contatos) {
        super(id, cnpj, cpf, nomeCompleto, endereco, contatos);
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
        return status == cliente.status && Objects.equals(compras, cliente.compras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, compras);
    }
}