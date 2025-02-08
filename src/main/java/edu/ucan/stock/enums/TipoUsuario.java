package edu.ucan.stock.enums;

public enum TipoUsuario {
    ADMINISTRADOR("ADMINISTRADOR"),
    GESTOR("GESTOR"),
    COLABORADOR("COLABORADOR"),
    CLIENTE("CLIENTE"),
    FORNECEDOR("FORNECEDOR");

    private String tipoUsuario;

    TipoUsuario(String tipo) {
        this.tipoUsuario = tipo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }
}
