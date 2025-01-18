package edu.ucan.stock.specification;

import edu.ucan.stock.entities.Armazem;
import edu.ucan.stock.entities.Armazem_;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class ArmazemSpec {
    private static Specification<Armazem> byNome(String nome) {
        return (root, query, builder) -> {

            if (Objects.nonNull(nome)) {
                return builder.equal(root.get(Armazem_.NOME), nome);
            }
            return null;
        };
    }

    private static Specification<Armazem> byCodigo(String codigo) {
        return (root, query, builder) -> {
            if (Objects.nonNull(codigo)) {
                return builder.equal(root.get(Armazem_.CODIGO), codigo);
            }
            return null;
        };
    }

    public static Specification<Armazem> filterBy(String nome, String codigo) {
        return byNome(nome).and(byCodigo(codigo));
    }
}
