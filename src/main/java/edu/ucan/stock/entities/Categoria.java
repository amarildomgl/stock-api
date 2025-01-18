package edu.ucan.stock.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "categorias")
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_categoria")
    private Integer pkCategoria;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "codigo")
    private String codigo;

    public Categoria(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }
}
