package edu.ucan.stock.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "produtos")
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_produto")
    private Integer pkProduto;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "codigo")
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "fk_categoria")
    private Categoria categoria;


    public Produto(String nome, String codigo, Categoria categoria) {
        this.nome = nome;
        this.codigo = codigo;
        this.categoria = categoria;
    }
}
