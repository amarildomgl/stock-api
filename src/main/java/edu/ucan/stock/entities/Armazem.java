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
@Entity(name = "armazens")
public class Armazem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_armazem")
    private Integer pkArmazem;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "codigo")
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "fk_endereco")
    private Localidade endereco;

    @OneToMany(mappedBy = "armazem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stock> stocks;

    public Armazem(String nome, String codigo, Localidade endereco) {
        this.nome = nome;
        this.codigo = codigo;
        this.endereco = endereco;
    }

    public Armazem(Integer pkArmazem, String nome, String codigo, Localidade endereco) {
        this.pkArmazem = pkArmazem;
        this.nome = nome;
        this.codigo = codigo;
        this.endereco = endereco;
    }

}
