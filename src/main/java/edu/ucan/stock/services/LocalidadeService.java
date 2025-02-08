package edu.ucan.stock.services;

import edu.ucan.stock.dto.records.LocalidadeRecord;
import edu.ucan.stock.entities.Localidade;
import edu.ucan.stock.repositories.LocalidadeRepository;
import edu.ucan.stock.specification.LocalidadeSpec;
import edu.ucan.stock.utils.AbstractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocalidadeService extends AbstractService<Localidade, Integer> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LocalidadeRepository repository;

    public Optional<Localidade> findByFilter(String nome) {
        return repository.findOne(LocalidadeSpec.filterBy(nome));
    }


    public LocalidadeRecord toDTO(Localidade localidade) {
        Integer FkLocalidadePai = null;
        if (localidade != null) {
            if (localidade.getFkLocalidadePai() != null) {
                FkLocalidadePai = localidade.getFkLocalidadePai().getPkLocalidade();
            }
            return new LocalidadeRecord(localidade.getPkLocalidade(), localidade.getNome(), localidade.getCodigo(), FkLocalidadePai);
        }
        return null;
    }

    public Localidade toEntity(LocalidadeRecord dto) {
        Localidade localidade = new Localidade();
        if (dto.pkLocalidade() != null) {
            localidade = repository.findById(dto.pkLocalidade()).orElse(new Localidade());
        }
        if (dto.fkLocalidadePai() != null) {
            Localidade pai = repository.findById(dto.fkLocalidadePai()).orElse(null);
            if (pai != null) {
                localidade.setFkLocalidadePai(pai);
            }
        }
        localidade.setNome(dto.nome());
        localidade.setCodigo(dto.codigo());

        return localidade;
    }
}
