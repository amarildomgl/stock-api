package edu.ucan.stock.specification;

import edu.ucan.stock.entities.Categoria;
import edu.ucan.stock.entities.Categoria_;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class CategoriaSpec {


    private static Specification<Categoria> byNome(String nome) {
        return (root, query, builder) -> {

            if (Objects.nonNull(nome)) {
                return builder.equal(root.get(Categoria_.NOME), nome);
            }
            return null;
        };
    }

    private static Specification<Categoria> byCodigo(String codigo) {
        return (root, query, builder) -> {

            if (Objects.nonNull(codigo)) {
                return builder.equal(root.get(Categoria_.CODIGO), codigo);
            }
            return null;
        };
    }

    public static Specification<Categoria> filterBy(String nome, String codigo) {
        return byNome(nome).and(byCodigo(codigo));
    }
}
