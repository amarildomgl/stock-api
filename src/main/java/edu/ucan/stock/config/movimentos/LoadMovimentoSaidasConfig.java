package edu.ucan.stock.config.movimentos;

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
@DependsOn({"loadStockDefaultConfig", "loadMovimentosEntradasConfig"})
public class LoadMovimentoSaidasConfig {

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private StockRepository stockRepository;

    @Bean
    @Order(6)
    @Transactional
    public CommandLineRunner carregarMovimentosSaidas() {
        return args -> {
            saidas();
            System.out.println("Saídas carregadas com sucesso!");
            
        };
    }

    public void saidas() {
        List<Stock> estoques = stockRepository.findAll();
        for (Stock stock : estoques) {
            if (stock.getQuantidade() > 0) {
                int quantidadeSaida = ThreadLocalRandom.current().nextInt(1, stock.getQuantidade() + 1);
                Movimento movimentoSaida = new Movimento();
                movimentoSaida.setTipoMovimento(TipoMovimento.SAIDA);
                movimentoSaida.setStock(stock);
                movimentoSaida.setProduto(stock.getProduto());
                movimentoSaida.setArmazem(stock.getArmazem());
                movimentoSaida.setQuantidade(quantidadeSaida);
                movimentoSaida.setData(LocalDateTime.now());
                movimentoRepository.save(movimentoSaida);
                stockRepository.save(stock);

            }
        }
    }

}
