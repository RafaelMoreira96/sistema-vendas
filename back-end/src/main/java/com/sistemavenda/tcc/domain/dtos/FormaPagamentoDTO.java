package com.sistemavenda.tcc.domain.dtos;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.sistemavenda.tcc.domain.FormaPagamento;

public class FormaPagamentoDTO {
    private Integer id;
    @NotNull(message = "O campo DESCRICAO deve ser informado!")
    private String descricao;

    public FormaPagamentoDTO() {
        super();
    }

    public FormaPagamentoDTO(FormaPagamento fp) {
        this.id = fp.getId();
        this.descricao = fp.getDescricao();
    }

    public FormaPagamentoDTO(Optional<FormaPagamento> fp) {
        this.id = fp.get().getId();
        this.descricao = fp.get().getDescricao();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}