package edu.ucan.stock.repositories;

import edu.ucan.stock.entities.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, Integer>, JpaSpecificationExecutor<Localidade> {
    Optional<Localidade> findByNome(String nome);

    Optional<Localidade> findByCodigo(String codigo);
}
