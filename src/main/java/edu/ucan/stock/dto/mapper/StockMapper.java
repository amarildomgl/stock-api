package edu.ucan.stock.dto.mapper;

import edu.ucan.stock.dto.request.StockRequest;
import edu.ucan.stock.dto.response.StockResponse;
import edu.ucan.stock.entities.Armazem;
import edu.ucan.stock.entities.Produto;
import edu.ucan.stock.entities.Stock;
import edu.ucan.stock.exceptions.ArmazemException;
import edu.ucan.stock.exceptions.ProdutoException;
import edu.ucan.stock.repositories.ArmazemRepository;
import edu.ucan.stock.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    @Autowired
    private ArmazemRepository armazemRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Stock toEntity(StockRequest request) {
        Armazem armazem = armazemRepository.findById(request.getFkArmazem())
                .orElseThrow(() -> new ArmazemException("Armazém não encontrado"));

        Produto produto = produtoRepository.findById(request.getFkProduto())
                .orElseThrow(() -> new ProdutoException("Produto não encontrado"));

        Stock stock = new Stock();
        stock.setArmazem(armazem);
        stock.setProduto(produto);
        stock.setQuantidadeCritica(request.getQuantidadeCritica());

        return stock;
    }

    public StockResponse toResponse(Stock stock) {
        StockResponse response = new StockResponse();
        response.setPkStock(stock.getPkStock());
        response.setArmazem(stock.getArmazem().getNome());
        response.setProduto(stock.getProduto().getNome());
        response.setQuantidade(stock.getQuantidade());
        response.setQuantidadeCritica(stock.getQuantidadeCritica());

        return response;
    }
}
