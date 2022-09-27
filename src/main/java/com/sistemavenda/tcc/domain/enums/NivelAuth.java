package com.sistemavenda.tcc.domain.enums;

public enum NivelAuth {

    ADMIN(0, "ROLE_ADMIN"), PADRAO(1, "ROLE_PADRAO");

    private Integer codigo;
    private String descricao;

    private NivelAuth(Integer codigo, String descricao) {
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

    public static NivelAuth toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (NivelAuth x : NivelAuth.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Permissão inválida!");
    }
}