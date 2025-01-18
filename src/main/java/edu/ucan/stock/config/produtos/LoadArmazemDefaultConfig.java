package edu.ucan.stock.config.produtos;

import edu.ucan.stock.entities.Armazem;
import edu.ucan.stock.entities.Localidade;
import edu.ucan.stock.repositories.ArmazemRepository;
import edu.ucan.stock.repositories.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
@DependsOn("loadLocalidadesDefaultConfig")
public class LoadArmazemDefaultConfig {

    @Autowired
    private ArmazemRepository armazemRepository;

    @Autowired
    LocalidadeRepository localidadeRepository;

    @Bean
    @Order(3)
    public CommandLineRunner loadarmazem() {
        return args -> {

            Localidade localidade = null;
            Optional<Localidade> localidadeOptional = localidadeRepository.findByNome("INDEFINIDO(A)");

            if (localidadeOptional.isPresent())
                localidade = localidadeOptional.get();

            List<Armazem> armazens = Arrays.asList(
                    new Armazem("Armazém A", "ARMAZ.A", localidade),
                    new Armazem("Armazém B", "ARMAZ.B", localidade),
                    new Armazem("Armazém C", "ARMAZ.C", localidade),
                    new Armazem("Armazém D", "ARMAZ.D", localidade)
            );

            armazemRepository.saveAll(armazens);

            System.out.println("Armazéns carregadas com sucesso!");


        };


    }
}

