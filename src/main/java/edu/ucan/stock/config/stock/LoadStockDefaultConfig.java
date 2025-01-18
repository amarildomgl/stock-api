package edu.ucan.stock.config.stock;

import edu.ucan.stock.entities.Armazem;
import edu.ucan.stock.entities.Produto;
import edu.ucan.stock.entities.Stock;
import edu.ucan.stock.repositories.ArmazemRepository;
import edu.ucan.stock.repositories.ProdutoRepository;
import edu.ucan.stock.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
@DependsOn({"loadProdutosDefaultConfig", "loadArmazemDefaultConfig"})
public class LoadStockDefaultConfig {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    ArmazemRepository armazemRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Bean
    @Order(4)
    public CommandLineRunner vincularStockArmazem() {
        return args -> {
            List<Produto> produtos = produtoRepository.findAll();
            List<Armazem> armazens = armazemRepository.findAll();
            for (Armazem armazem : armazens) {
                for (Produto produto : produtos) {
                    if (stockRepository.findByArmazemAndProduto(armazem, produto) == null) {
                        stockRepository.save(new Stock(armazem, produto));
                    }
                }
            }
            System.out.println("Stock carregado com sucesso!");
        };
    }
}
