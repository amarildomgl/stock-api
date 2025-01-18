package edu.ucan.stock.services;

import edu.ucan.stock.entities.Movimento;
import edu.ucan.stock.enums.TipoMovimento;
import edu.ucan.stock.repositories.MovimentoRepository;
import edu.ucan.stock.specification.MovimentoSpec;
import edu.ucan.stock.utils.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentoService extends AbstractService<Movimento, Integer> {

    @Autowired
    private MovimentoRepository repository;

    public List<Movimento> findAllFilterBy(Integer fkArmazem, Integer fkProduto, TipoMovimento tipoMovimento, LocalDateTime data, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return repository.findAll(MovimentoSpec.filterBy(fkArmazem, fkProduto, tipoMovimento, data, dataInicial, dataFinal));
    }
}
