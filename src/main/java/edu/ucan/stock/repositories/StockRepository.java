package edu.ucan.stock.repositories;

import edu.ucan.stock.entities.Armazem;
import edu.ucan.stock.entities.Produto;
import edu.ucan.stock.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer>, JpaSpecificationExecutor<Stock> {
    Stock findByArmazemAndProduto(Armazem armazem, Produto produto);
}
