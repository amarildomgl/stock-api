package edu.ucan.stock.services;

import edu.ucan.stock.entities.Stock;
import edu.ucan.stock.repositories.StockRepository;
import edu.ucan.stock.specification.StockSpec;
import edu.ucan.stock.utils.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService extends AbstractService<Stock, Integer> {


    @Autowired
    private StockRepository repository;


    public List<Stock> findAllFilterBy(Integer fkArmazem, Integer fkProduto, boolean quantidadeCritica) {
        return repository.findAll(StockSpec.filterBy(fkArmazem, fkProduto, quantidadeCritica));
    }
}
