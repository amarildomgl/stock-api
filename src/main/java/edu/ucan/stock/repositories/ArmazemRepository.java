package edu.ucan.stock.repositories;

import edu.ucan.stock.entities.Armazem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArmazemRepository extends JpaRepository<Armazem, Integer>, JpaSpecificationExecutor<Armazem> {

    Optional<Armazem> findByNome(String nome);

    Optional<Armazem> findByCodigo(String codigo);

}
