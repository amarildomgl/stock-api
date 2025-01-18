package edu.ucan.stock.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockRequest {
    private Integer fkArmazem;
    private Integer fkProduto;
    private int quantidadeCritica;
}
