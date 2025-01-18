package edu.ucan.stock.repositories;

import edu.ucan.stock.entities.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Integer>, JpaSpecificationExecutor<Movimento> {
}
