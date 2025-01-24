package edu.ucan.stock.config.defaultdata.produtos;

import edu.ucan.stock.entities.Categoria;
import edu.ucan.stock.entities.Produto;
import edu.ucan.stock.repositories.CategoriaRepository;
import edu.ucan.stock.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.List;

@Configuration
@DependsOn("loadLocalidadesDefaultConfig")
public class LoadProdutosDefaultConfig {
    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Bean
    @Order(2)
    public CommandLineRunner loadCategorias() {
        return args -> {
            categorias();
            alimentos();
            electrodomesticos();
            moveis();
            eletronico();

            System.out.println("Produtos carregados com sucesso!");

        };
    }

    public void categorias() {
        List<Categoria> categorias = Arrays.asList(
                new Categoria("INDEFINIDO(A)", "N/A"),
                new Categoria("Alimentos", "ALIM001"),
                new Categoria("Bebidas", "BEBI001"),
                new Categoria("Roupas", "ROUP001"),
                new Categoria("Eletrodomésticos", "ELEC001"),
                new Categoria("Eletrônicos", "ELET001"),
                new Categoria("Móveis", "MOVI001"),
                new Categoria("Brinquedos", "BRIN001"),
                new Categoria("Livros", "LIVR001"),
                new Categoria("Ferramentas", "FERR001"),
                new Categoria("Automóveis", "AUTO001"),
                new Categoria("Saúde", "SAUD001"),
                new Categoria("Beleza e Cuidados Pessoais", "BELE001"),
                new Categoria("Esportes", "ESPO001"),
                new Categoria("Jardinagem", "JARD001"),
                new Categoria("Animais", "ANIM001"),
                new Categoria("Tecnologia", "TECN001"),
                new Categoria("Hobbies", "HOBB001"),
                new Categoria("Decoração", "DECO001"),
                new Categoria("Papeleria", "PAPE001"),
                new Categoria("Produtos de limpeza", "CLIN001")
        );
        categoriaRepository.saveAll(categorias);

    }

    public void alimentos() {
        Categoria categoriaAlimentos = categoriaRepository.findByNome("Alimentos");

        List<Produto> produtos = Arrays.asList(
                new Produto("Arroz", "ARZ001", categoriaAlimentos),
                new Produto("Feijão", "FEI001", categoriaAlimentos),
                new Produto("Açúcar", "ACU001", categoriaAlimentos)
        );

        produtoRepository.saveAll(produtos);
    }

    public void electrodomesticos() {
        Categoria categoriaElectrodomesticos = categoriaRepository.findByNome("Eletrodomésticos");

        List<Produto> produtos = Arrays.asList(
                new Produto("Geladeira", "GEL001", categoriaElectrodomesticos),
                new Produto("Micro-ondas", "MIC001", categoriaElectrodomesticos),
                new Produto("Máquina de Lavar", "MLV001", categoriaElectrodomesticos)
        );

        produtoRepository.saveAll(produtos);
    }

    public void moveis() {
        Categoria categoriaMoveis = categoriaRepository.findByNome("Móveis");

        List<Produto> produtos = Arrays.asList(
                new Produto("Sofá", "SOF001", categoriaMoveis),
                new Produto("Mesa de Jantar", "MEJ001", categoriaMoveis),
                new Produto("Cadeira de Escritório", "CES001", categoriaMoveis)
        );

        produtoRepository.saveAll(produtos);
    }

    public void eletronico() {
        Categoria categoriaEletronicos = categoriaRepository.findByNome("Eletrônicos");

        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", "SMP001", categoriaEletronicos),
                new Produto("Notebook", "NBT001", categoriaEletronicos),
                new Produto("Fone de Ouvido Bluetooth", "FOB001", categoriaEletronicos)
        );

        produtoRepository.saveAll(produtos);
    }
}
