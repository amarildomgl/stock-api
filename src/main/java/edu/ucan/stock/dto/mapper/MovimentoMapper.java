package edu.ucan.stock.dto.mapper;

import edu.ucan.stock.dto.request.MovimentoRequest;
import edu.ucan.stock.dto.response.MovimentoResponse;
import edu.ucan.stock.entities.Armazem;
import edu.ucan.stock.entities.Movimento;
import edu.ucan.stock.entities.Produto;
import edu.ucan.stock.enums.TipoMovimento;
import edu.ucan.stock.repositories.ArmazemRepository;
import edu.ucan.stock.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MovimentoMapper {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ArmazemRepository armazemRepository;


    public Movimento toEntity(MovimentoRequest request) {
        Movimento movimento = new Movimento();

        movimento.setTipoMovimento(TipoMovimento.values()[request.getTipoMovimento()]);

        Produto produto = produtoRepository.findById(request.getFkProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        Armazem armazem = armazemRepository.findById(request.getFkArmazem())
                .orElseThrow(() -> new RuntimeException("Armazém não encontrado"));

        movimento.setProduto(produto);
        movimento.setArmazem(armazem);
        movimento.setQuantidade(request.getQuantidade());

        movimento.setData(request.getData() != null ? request.getData() : LocalDateTime.now());

        return movimento;
    }

    public MovimentoResponse toResponse(Movimento movimento) {
        MovimentoResponse response = new MovimentoResponse();
        response.setTipoMovimento(movimento.getTipoMovimento());

        String armazem = movimento.getArmazem() != null ? movimento.getArmazem().getNome() : "N/A";
        String produto = movimento.getProduto() != null ? movimento.getProduto().getNome() : "N/A";

        response.setArmazem(armazem);
        response.setProduto(produto);
        response.setQuantidade(movimento.getQuantidade());
        response.setData(movimento.getData());

        return response;
    }
}
