package edu.ucan.stock.entities;

import edu.ucan.stock.enums.TipoMovimento;
import edu.ucan.stock.exceptions.StockException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "movimentos")
public class Movimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_movimento")
    private Integer pkMovimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao", nullable = false)
    private TipoMovimento tipoMovimento;

    @ManyToOne
    @JoinColumn(name = "fk_produto", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "fk_armazem", nullable = false)
    private Armazem armazem;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_stock", nullable = false)
    private Stock stock;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @PrePersist
    public void actualizarStock() {
        if (this.stock != null) {
            if (TipoMovimento.CORRECAO.equals(tipoMovimento)) {
                this.stock.corrigirQuantidade(quantidade);
                return;
            }

            int valorMovimento = tipoMovimento.getValor();
            
            if (valorMovimento < 0 && this.stock.getQuantidade() < quantidade) {
                throw new StockException("Quantidade insuficiente no estoque para a saída.");
            }
            this.stock.actualizarQuandidade(valorMovimento * quantidade);
        }
    }
}
