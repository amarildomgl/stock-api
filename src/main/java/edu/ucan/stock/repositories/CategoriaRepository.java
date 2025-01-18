package edu.ucan.stock.repositories;

import edu.ucan.stock.entities.Categoria;
import edu.ucan.stock.entities.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> , JpaSpecificationExecutor<Categoria> {
    Categoria findByNome(String nome);
    Categoria findByCodigo(String codigo);
}
