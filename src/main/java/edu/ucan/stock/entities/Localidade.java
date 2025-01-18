package edu.ucan.stock.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Entity(name = "localidades")
public class Localidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_localidade")
    private Integer pkLocalidade;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "codigo")
    private String codigo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_localidade_pai", referencedColumnName = "pk_localidade")
    @JsonBackReference // Evita loop infinito na serialização
    private Localidade fkLocalidadePai;


    @OneToMany(mappedBy = "fkLocalidadePai", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Evita loop infinito na serialização
    private List<Localidade> localidades;


    public Localidade(String nome, String codigo, Localidade fkLocalidadePai) {
        this.nome = nome;
        this.codigo = codigo;
        this.fkLocalidadePai = fkLocalidadePai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Localidade)) return false;
        Localidade that = (Localidade) o;
        return pkLocalidade != null && pkLocalidade.equals(that.pkLocalidade);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
