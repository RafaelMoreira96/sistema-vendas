package com.sistemavenda.tcc.domain.enums;

public enum StatusVenda {
    ANDAMENTO(0, "ANDAMENTO"), CANCELADO(1, "CANCELADO"), FINALIZADO(2, "FINALIZADO");

    private Integer codigo;
    private String descricao;

    private StatusVenda(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static StatusVenda toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (StatusVenda x : StatusVenda.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Status de venda inválido!");
    }

}