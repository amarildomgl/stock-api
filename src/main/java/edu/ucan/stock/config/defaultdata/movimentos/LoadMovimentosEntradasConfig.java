package edu.ucan.stock.config.defaultdata.movimentos;

import edu.ucan.stock.entities.Movimento;
import edu.ucan.stock.entities.Stock;
import edu.ucan.stock.enums.TipoMovimento;
import edu.ucan.stock.repositories.MovimentoRepository;
import edu.ucan.stock.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
@DependsOn({"loadStockDefaultConfig"})
public class LoadMovimentosEntradasConfig {

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private StockRepository stockRepository;

    @Bean
    @Order(5)
    @Transactional
    public CommandLineRunner carregarMovimentosDefault() {
        return args -> {
            entradas();
            System.out.println("Entradas carregadas com sucesso!");
            
        };
    }

    public void entradas() {
        List<Stock> estoques = stockRepository.findAll();
        for (Stock stock : estoques) {
            int quantidadeEntrada = ThreadLocalRandom.current().nextInt(10, 101);

            Movimento movimentoEntrada = new Movimento();
            movimentoEntrada.setTipoMovimento(TipoMovimento.ENTRADA);
            movimentoEntrada.setStock(stock);
            movimentoEntrada.setProduto(stock.getProduto());
            movimentoEntrada.setArmazem(stock.getArmazem());
            movimentoEntrada.setQuantidade(quantidadeEntrada);
            movimentoEntrada.setData(LocalDateTime.now());
            movimentoRepository.save(movimentoEntrada);
            stockRepository.save(stock);

        }
    }


}
