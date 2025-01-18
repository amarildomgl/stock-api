package edu.ucan.stock.specification;

import edu.ucan.stock.entities.Stock;
import edu.ucan.stock.entities.Stock_;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class StockSpec {

    private static Specification<Stock> byFkArmazem(Integer fkArmazem) {
        return (root, query, builder) -> {
            if (Objects.nonNull(fkArmazem)) {
                return builder.equal(root.get(Stock_.ARMAZEM).get("pkArmazem"), fkArmazem);
            }
            return null;
        };
    }

    private static Specification<Stock> byFkProduto(Integer fkProduto) {
        return (root, query, builder) -> {
            if (Objects.nonNull(fkProduto)) {
                return builder.equal(root.get(Stock_.PRODUTO).get("pkProduto"), fkProduto);
            }
            return null;
        };
    }

    private static Specification<Stock> byQuantidadeCritica() {
        return (root, query, builder) ->
                builder.lessThanOrEqualTo(root.get(Stock_.QUANTIDADE), root.get(Stock_.QUANTIDADE_CRITICA));
    }

    public static Specification<Stock> filterBy(Integer fkArmazem, Integer fkProduto, boolean quantidadeCritica) {
        Specification<Stock> spec = Specification.where(byFkArmazem(fkArmazem)).and(byFkProduto(fkProduto));

        if (quantidadeCritica) {
            spec = spec.and(byQuantidadeCritica());
        }

        return spec;
    }
}
