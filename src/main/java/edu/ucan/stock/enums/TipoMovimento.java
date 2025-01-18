package edu.ucan.stock.enums;

import edu.ucan.stock.exceptions.StockException;

public enum TipoMovimento {
    ENTRADA(1),
    SAIDA(-1),
    CORRECAO(0);

    private final int valor;

    TipoMovimento(int valor) {
        this.valor = valor;
    }


    public int getValor() {
        return valor;
    }

    public static TipoMovimento fromValor(int valor) {
        for (TipoMovimento tipo : TipoMovimento.values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        throw new StockException("Valor inválido para TipoMovimento: " + valor);
    }
}
