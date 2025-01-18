package edu.ucan.stock.repositories;

import edu.ucan.stock.entities.Categoria;
import edu.ucan.stock.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>, JpaSpecificationExecutor<Produto> {
    Optional<Produto> findByNome(String nome);
    Optional<Produto>  findByCodigo(String codigo);
}
