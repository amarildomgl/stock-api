package edu.ucan.stock.services;

import edu.ucan.stock.dto.ArmazemDTO;
import edu.ucan.stock.entities.Armazem;
import edu.ucan.stock.entities.Localidade;
import edu.ucan.stock.repositories.ArmazemRepository;
import edu.ucan.stock.repositories.LocalidadeRepository;
import edu.ucan.stock.specification.ArmazemSpec;
import edu.ucan.stock.utils.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArmazemService extends AbstractService<Armazem, Integer> {

    @Autowired
    private ArmazemRepository repository;

    @Autowired
    private LocalidadeRepository localidadeRepository;

    public Optional<Armazem> findByFilter(String nome, String codigo) {
        return repository.findOne(ArmazemSpec.filterBy(nome, codigo));
    }

    public ArmazemDTO toDto(Armazem armazem) {
        if (armazem != null) {
            return new ArmazemDTO(
                    armazem.getPkArmazem(),
                    armazem.getNome(),
                    armazem.getCodigo(),
                    armazem.getEndereco().getPkLocalidade()
            );
        }
        return null;
    }

    public Armazem toEntity(ArmazemDTO dto) {
        if (dto == null)
            return null;
        Localidade endereco = null;
        Optional<Localidade> optionalLocalidade =
                localidadeRepository.findById(dto.fkEndereco());
        if (optionalLocalidade.isPresent())
            endereco = optionalLocalidade.get();

        return new Armazem(
                dto.pkArmazem(),
                dto.nome(),
                dto.codigo(),
                endereco
        );
    }


}
