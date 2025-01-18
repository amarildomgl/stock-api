package edu.ucan.stock.services;

import edu.ucan.stock.dto.CategoriaDTO;
import edu.ucan.stock.entities.Categoria;
import edu.ucan.stock.repositories.CategoriaRepository;
import edu.ucan.stock.specification.CategoriaSpec;
import edu.ucan.stock.utils.AbstractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService extends AbstractService<Categoria, Integer> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriaRepository repository;


    public Optional<Categoria> findByFilter(String nome, String codigo) {
        return repository.findOne(CategoriaSpec.filterBy(nome, codigo));
    }


    public CategoriaDTO toDto(Categoria categoria) {
        return categoria != null ? modelMapper.map(categoria, CategoriaDTO.class) : null;
    }

    public Categoria toEntity(CategoriaDTO categoriaDTO) {
        return categoriaDTO != null ? modelMapper.map(categoriaDTO, Categoria.class) : null;
    }


}
