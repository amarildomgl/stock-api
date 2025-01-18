package edu.ucan.stock.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse {
    private Integer pkProduto;
    private String nome;
    private String codigo;
    private String categoria;
}
