package edu.ucan.stock.dto.response;

import edu.ucan.stock.enums.TipoMovimento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentoResponse {
    private TipoMovimento tipoMovimento;
    private String armazem;
    private String produto;
    private int quantidade;
    private LocalDateTime data;
}
