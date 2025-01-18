package edu.ucan.stock.specification;


import edu.ucan.stock.entities.Localidade;
import edu.ucan.stock.entities.Localidade_;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class LocalidadeSpec {

    private static Specification<Localidade> byNome(String nome) {
        return (root, query, builder) -> {

            if (Objects.nonNull(nome)) {
                return builder.equal(root.get(Localidade_.NOME), nome);
            }
            return null;
        };
    }

    private static Specification<Localidade> byCodigo(String codigo) {
        return (root, query, builder) -> {

            if (Objects.nonNull(codigo)) {
                return builder.equal(root.get(Localidade_.CODIGO), codigo);
            }
            return null;
        };
    }

    public static Specification<Localidade> filterBy(String nome) {
        return byNome(nome);
    }
}
