package com.sistemavenda.tcc.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "O campo 'número' não pode ficar em branco")
    private String numero;
    private String tipo;

    public Contato() {
    }

    public Contato(Integer id, String numero, String tipo) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Contato)) {
            return false;
        }
        Contato contato = (Contato) o;
        return Objects.equals(id, contato.id) && Objects.equals(numero, contato.numero)
                && Objects.equals(tipo, contato.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, tipo);
    }

}