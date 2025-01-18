package edu.ucan.stock.specification;

import edu.ucan.stock.entities.Movimento;
import edu.ucan.stock.entities.Movimento_;
import edu.ucan.stock.enums.TipoMovimento;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Objects;

public class MovimentoSpec {

    private static Specification<Movimento> byFkArmazem(Integer fkArmazem) {
        return (root, query, builder) -> {
            if (Objects.nonNull(fkArmazem)) {
                return builder.equal(root.get(Movimento_.ARMAZEM).get("pkArmazem"), fkArmazem);
            }
            return null;
        };
    }

    private static Specification<Movimento> byFkProduto(Integer fkProduto) {
        return (root, query, builder) -> {
            if (Objects.nonNull(fkProduto)) {
                return builder.equal(root.get(Movimento_.PRODUTO).get("pkProduto"), fkProduto);
            }
            return null;
        };
    }

    public static Specification<Movimento> byTipoMovimento(TipoMovimento tipoMovimento) {
        return (root, query, builder) -> {
            if (Objects.nonNull(tipoMovimento)) {
                return builder.equal(root.get(Movimento_.TIPO_MOVIMENTO), tipoMovimento);
            }
            return null;
        };
    }

    public static Specification<Movimento> byData(LocalDateTime data) {
        return (root, query, builder) -> {
            if (Objects.nonNull(data)) {
                return builder.equal(root.get(Movimento_.DATA), data);
            }
            return null;
        };
    }

    public static Specification<Movimento> byIntervaloDeDatas(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return (root, query, builder) -> {
            if (Objects.nonNull(dataInicial) && Objects.nonNull(dataFinal)) {
                return builder.between(root.get(Movimento_.DATA), dataInicial, dataFinal);
            } else if (Objects.nonNull(dataInicial)) {
                return builder.greaterThanOrEqualTo(root.get(Movimento_.DATA), dataInicial);
            } else if (Objects.nonNull(dataFinal)) {
                return builder.lessThanOrEqualTo(root.get(Movimento_.DATA), dataFinal);
            }
            return null;
        };
    }

    public static Specification<Movimento> filterBy(Integer fkArmazem, Integer fkProduto, TipoMovimento tipoMovimento, LocalDateTime data, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Specification<Movimento> spec = Specification.where(null);

        if (Objects.nonNull(fkArmazem)) {
            spec = spec.and(byFkArmazem(fkArmazem));
        }
        if (Objects.nonNull(fkProduto)) {
            spec = spec.and(byFkProduto(fkProduto));
        }
        if (Objects.nonNull(tipoMovimento)) {
            spec = spec.and(byTipoMovimento(tipoMovimento));
        }
        if (Objects.nonNull(data)) {
            spec = spec.and(byData(data));
        }
        if (Objects.nonNull(dataInicial) || Objects.nonNull(dataFinal)) {
            spec = spec.and(byIntervaloDeDatas(dataInicial, dataFinal));
        }

        return spec;
    }

}
