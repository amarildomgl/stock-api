package edu.ucan.stock.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentoRequest {
    private Integer tipoMovimento;
    private Integer fkArmazem;
    private Integer fkProduto;
    private int quantidade;
    private LocalDateTime data;
}
