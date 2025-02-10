package edu.ucan.stock.enums;

public enum TipoUsuario {
    ADMINISTRADOR(1),
    GESTOR(2),
    COLABORADOR(3),
    CLIENTE(4),
    FORNECEDOR(5);

    private int tipoUsuario;

    TipoUsuario(int tipo) {
        this.tipoUsuario = tipo;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }
}
