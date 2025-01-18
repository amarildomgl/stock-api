package edu.ucan.stock.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockResponse {
    private Integer pkStock;
    private String armazem;
    private String produto;
    private int quantidade;
    private int quantidadeCritica;
}
