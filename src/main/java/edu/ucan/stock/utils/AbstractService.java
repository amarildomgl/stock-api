package edu.ucan.stock.utils;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class AbstractService<E, K> {

    @Autowired
    protected JpaRepository<E, K> repository;

    public List<E> findAll() {
        return this.repository.findAll();
    }

    public Page<E> findAll(int pagina, int itens) {
        return this.repository.findAll(PageRequest.of(pagina, itens));
    }

    public Optional<E> findById(K id) {
        return this.repository.findById(id);
    }

    public E create(E entidade) {
        if (entidade == null)
            throw new NullPointerException("Entidade a ser registrada está nula.");
        return this.repository.save(entidade);
    }

    public E update(K id, E entity) {
        if (this.findById(id).isEmpty())
            throw new EntityNotFoundException("Não foi possível encontrar o registro.");
        return this.repository.save(entity);
    }

    public E delete(K id) {
        Optional<E> entidade = this.findById(id);
        if (entidade.isEmpty())
            return null;
        this.repository.deleteById(id);
        return entidade.get();
    }


    public Page<E> findPage(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Repository<E, K> getRepository() {
        return repository;
    }
}
