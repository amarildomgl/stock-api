package edu.ucan.stock.services;

import edu.ucan.stock.entities.Produto;
import edu.ucan.stock.repositories.ProdutoRepository;
import edu.ucan.stock.specification.ProdutoSpec;
import edu.ucan.stock.utils.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService extends AbstractService<Produto, Integer> {

    @Autowired
    private ProdutoRepository repository;

    public Optional<Produto> findByFilter(String nome, String codigo) {
        return repository.findOne(ProdutoSpec.filterBy(nome, codigo));
    }


}
