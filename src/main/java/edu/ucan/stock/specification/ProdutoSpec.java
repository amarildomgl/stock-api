package edu.ucan.stock.specification;

import edu.ucan.stock.entities.Localidade_;
import edu.ucan.stock.entities.Produto;
import edu.ucan.stock.entities.Produto_;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class ProdutoSpec {

    private static Specification<Produto> byNome(String nome) {
        return (root, query, builder) -> {

            if (Objects.nonNull(nome)) {
                return builder.equal(root.get(Localidade_.NOME), nome);
            }
            return null;
        };
    }

    private static Specification<Produto> byCodigo(String codigo) {
        return (root, query, builder) -> {

            if (Objects.nonNull(codigo)) {
                return builder.equal(root.get(Produto_.CODIGO), codigo);
            }
            return null;
        };
    }

    public static Specification<Produto> filterBy(String nome, String codigo) {
        return byNome(nome).and(byCodigo(codigo));
    }
}
