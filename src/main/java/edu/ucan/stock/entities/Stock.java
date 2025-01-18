package edu.ucan.stock.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "stock")
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_stock")
    private Integer pkStock;

    @ManyToOne
    @JoinColumn(name = "fk_armazem")
    private Armazem armazem;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;

    @Column(name = "quantidade", nullable = false)
    private int quantidade = 0;

    @Column(name = "quantidade_critica", nullable = false)
    private int quantidadeCritica = 10;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimento> movimentos;

    public Stock(Armazem armazem, Produto produto, int quantidade) {
        this.armazem = armazem;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Stock(Armazem armazem, Produto produto) {
        this.armazem = armazem;
        this.produto = produto;
    }

    public void actualizarQuandidade(int quantidade) {
        this.quantidade += quantidade;
    }

    public void corrigirQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isQuantidadeCritica() {
        return this.quantidade <= this.quantidadeCritica;
    }
}
