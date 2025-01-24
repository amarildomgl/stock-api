package edu.ucan.stock.config.defaultdata.localidades;

import edu.ucan.stock.entities.Localidade;
import edu.ucan.stock.repositories.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class LoadLocalidadesDefaultConfig {

    @Autowired
    private LocalidadeRepository repository;

    @Bean
    @Order(1)
    public CommandLineRunner localidades() {
        return args -> {
            continentes();
            africa();
            europa();
            asia();
            americaSul();
            americaNorte();
            angola();
            luanda();
            System.out.println("Localidades carregadas com sucesso!");
        };
    }

    public void continentes() {
        List<Localidade> continentes = Arrays.asList(
                new Localidade("INDEFINIDO(A)", "N/A", null),
                new Localidade("África", "AF", null),
                new Localidade("América do Norte", "NA", null),
                new Localidade("América do Sul", "SA", null),
                new Localidade("Antártica", "AN", null),
                new Localidade("Ásia", "AS", null),
                new Localidade("Europa", "EU", null),
                new Localidade("Oceania", "OC", null)
        );

        repository.saveAll(continentes);
    }

    public void africa() {
        Optional<Localidade> continenteAfricaOp = repository.findByNome("África");
        if (continenteAfricaOp.isPresent()) {
            Localidade continenteAfrica = continenteAfricaOp.get();

            List<Localidade> paisesAfricanos = Arrays.asList(
                    new Localidade("África do Sul", "ZA", continenteAfrica),
                    new Localidade("Albânia", "AL", continenteAfrica),
                    new Localidade("Algéria", "DZ", continenteAfrica),
                    new Localidade("Angola", "AO", continenteAfrica),
                    new Localidade("Benim", "BJ", continenteAfrica),
                    new Localidade("Botswana", "BW", continenteAfrica),
                    new Localidade("Burkina Faso", "BF", continenteAfrica),
                    new Localidade("Burundi", "BI", continenteAfrica),
                    new Localidade("Cabo Verde", "CV", continenteAfrica),
                    new Localidade("Camarões", "CM", continenteAfrica),
                    new Localidade("República Centro-Africana", "CF", continenteAfrica),
                    new Localidade("Chade", "TD", continenteAfrica),
                    new Localidade("Comores", "KM", continenteAfrica),
                    new Localidade("Congo (República do Congo)", "CG", continenteAfrica),
                    new Localidade("Congo (República Democrática do Congo)", "CD", continenteAfrica),
                    new Localidade("Djibuti", "DJ", continenteAfrica),
                    new Localidade("Egito", "EG", continenteAfrica),
                    new Localidade("Eritreia", "ER", continenteAfrica),
                    new Localidade("Eswatini", "SZ", continenteAfrica),
                    new Localidade("Etiópia", "ET", continenteAfrica),
                    new Localidade("Gabão", "GA", continenteAfrica),
                    new Localidade("Gâmbia", "GM", continenteAfrica),
                    new Localidade("Gana", "GH", continenteAfrica),
                    new Localidade("Guiné", "GN", continenteAfrica),
                    new Localidade("Guiné-Bissau", "GW", continenteAfrica),
                    new Localidade("Guiné Equatorial", "GQ", continenteAfrica),
                    new Localidade("Costa do Marfim", "CI", continenteAfrica),
                    new Localidade("Quênia", "KE", continenteAfrica),
                    new Localidade("Lesoto", "LS", continenteAfrica),
                    new Localidade("Libéria", "LR", continenteAfrica),
                    new Localidade("Líbia", "LY", continenteAfrica),
                    new Localidade("Madagáscar", "MG", continenteAfrica),
                    new Localidade("Malawi", "MW", continenteAfrica),
                    new Localidade("Mali", "ML", continenteAfrica),
                    new Localidade("Mauritânia", "MR", continenteAfrica),
                    new Localidade("Maurício", "MU", continenteAfrica),
                    new Localidade("Marrocos", "MA", continenteAfrica),
                    new Localidade("Moçambique", "MZ", continenteAfrica),
                    new Localidade("Namíbia", "NA", continenteAfrica),
                    new Localidade("Níger", "NE", continenteAfrica),
                    new Localidade("Nigéria", "NG", continenteAfrica),
                    new Localidade("República do Senegal", "SN", continenteAfrica),
                    new Localidade("Seychelles", "SC", continenteAfrica),
                    new Localidade("Somália", "SO", continenteAfrica),
                    new Localidade("Sudão", "SD", continenteAfrica),
                    new Localidade("Sudão do Sul", "SS", continenteAfrica),
                    new Localidade("Togo", "TG", continenteAfrica),
                    new Localidade("Tunísia", "TN", continenteAfrica),
                    new Localidade("Uganda", "UG", continenteAfrica),
                    new Localidade("Zâmbia", "ZM", continenteAfrica),
                    new Localidade("Zimbábue", "ZW", continenteAfrica)
            );

            repository.saveAll(paisesAfricanos);
        }
    }

    public void europa() {

        Optional<Localidade> data = repository.findByNome("Europa");
        if (data.isPresent()) {
            Localidade continenteEuropa = data.get();

            List<Localidade> paisesEuropa = Arrays.asList(
                    new Localidade("Alemanha", "DE", continenteEuropa),
                    new Localidade("Áustria", "AT", continenteEuropa),
                    new Localidade("Bélgica", "BE", continenteEuropa),
                    new Localidade("Bulgária", "BG", continenteEuropa),
                    new Localidade("Chipre", "CY", continenteEuropa),
                    new Localidade("Croácia", "HR", continenteEuropa),
                    new Localidade("Dinamarca", "DK", continenteEuropa),
                    new Localidade("Eslováquia", "SK", continenteEuropa),
                    new Localidade("Eslovênia", "SI", continenteEuropa),
                    new Localidade("Espanha", "ES", continenteEuropa),
                    new Localidade("Estônia", "EE", continenteEuropa),
                    new Localidade("Finlândia", "FI", continenteEuropa),
                    new Localidade("França", "FR", continenteEuropa),
                    new Localidade("Grécia", "GR", continenteEuropa),
                    new Localidade("Hungria", "HU", continenteEuropa),
                    new Localidade("Irlanda", "IE", continenteEuropa),
                    new Localidade("Islândia", "IS", continenteEuropa),
                    new Localidade("Itália", "IT", continenteEuropa),
                    new Localidade("Letônia", "LV", continenteEuropa),
                    new Localidade("Lituânia", "LT", continenteEuropa),
                    new Localidade("Luxemburgo", "LU", continenteEuropa),
                    new Localidade("Malta", "MT", continenteEuropa),
                    new Localidade("Moldávia", "MD", continenteEuropa),
                    new Localidade("Mônaco", "MC", continenteEuropa),
                    new Localidade("Montenegro", "ME", continenteEuropa),
                    new Localidade("Noruega", "NO", continenteEuropa),
                    new Localidade("Países Baixos", "NL", continenteEuropa),
                    new Localidade("Polônia", "PL", continenteEuropa),
                    new Localidade("Portugal", "PT", continenteEuropa),
                    new Localidade("República Checa", "CZ", continenteEuropa),
                    new Localidade("Romênia", "RO", continenteEuropa),
                    new Localidade("San Marino", "SM", continenteEuropa),
                    new Localidade("Sérvia", "RS", continenteEuropa),
                    new Localidade("Suécia", "SE", continenteEuropa),
                    new Localidade("Suíça", "CH", continenteEuropa),
                    new Localidade("Ucrânia", "UA", continenteEuropa)
            );

            repository.saveAll(paisesEuropa);
        }
    }

    public void americaSul() {
        Optional<Localidade> continenteAmericaDoSul = repository.findByNome("América do Sul");
        if (continenteAmericaDoSul.isPresent()) {
            Localidade continente = continenteAmericaDoSul.get();
            List<Localidade> paisesAmericaDoSul = Arrays.asList(
                    new Localidade("Argentina", "AR", continente),
                    new Localidade("Bolívia", "BO", continente),
                    new Localidade("Brasil", "BR", continente),
                    new Localidade("Chile", "CL", continente),
                    new Localidade("Colômbia", "CO", continente),
                    new Localidade("Equador", "EC", continente),
                    new Localidade("Guiana", "GY", continente),
                    new Localidade("Paraguai", "PY", continente),
                    new Localidade("Peru", "PE", continente),
                    new Localidade("Suriname", "SR", continente),
                    new Localidade("Uruguai", "UY", continente),
                    new Localidade("Venezuela", "VE", continente)
            );
            repository.saveAll(paisesAmericaDoSul);
        }
    }

    public void americaNorte() {
        Optional<Localidade> continenteAmericaDoNorte = repository.findByNome("América do Norte");
        if (continenteAmericaDoNorte.isPresent()) {
            Localidade continente = continenteAmericaDoNorte.get();
            List<Localidade> paisesAmericaDoNorte = Arrays.asList(
                    new Localidade("Canadá", "CA", continente),
                    new Localidade("Estados Unidos", "US", continente),
                    new Localidade("México", "MX", continente)
            );
            repository.saveAll(paisesAmericaDoNorte);
        }
    }

    public void asia() {
        Optional<Localidade> continenteAsia = repository.findByNome("Ásia");
        if (continenteAsia.isPresent()) {
            Localidade continente = continenteAsia.get();
            List<Localidade> paisesAsia = Arrays.asList(
                    new Localidade("Afeganistão", "AF", continente),
                    new Localidade("Arábia Saudita", "SA", continente),
                    new Localidade("Armênia", "AM", continente),
                    new Localidade("Azerbaijão", "AZ", continente),
                    new Localidade("Bahrein", "BH", continente),
                    new Localidade("Bangladesh", "BD", continente),
                    new Localidade("Butão", "BT", continente),
                    new Localidade("Brunei", "BN", continente),
                    new Localidade("Camboja", "KH", continente),
                    new Localidade("China", "CN", continente),
                    new Localidade("Chipre", "CY", continente),
                    new Localidade("Coreia do Norte", "KP", continente),
                    new Localidade("Coreia do Sul", "KR", continente),
                    new Localidade("Emirados Árabes Unidos", "AE", continente),
                    new Localidade("Geórgia", "GE", continente),
                    new Localidade("Iémen", "YE", continente),
                    new Localidade("Índia", "IN", continente),
                    new Localidade("Indonésia", "ID", continente),
                    new Localidade("Irã", "IR", continente),
                    new Localidade("Iraque", "IQ", continente),
                    new Localidade("Israel", "IL", continente),
                    new Localidade("Japão", "JP", continente),
                    new Localidade("Jordânia", "JO", continente),
                    new Localidade("Cazaquistão", "KZ", continente),
                    new Localidade("Quirguistão", "KG", continente),
                    new Localidade("Kuwait", "KW", continente),
                    new Localidade("Laos", "LA", continente),
                    new Localidade("Líbano", "LB", continente),
                    new Localidade("Malásia", "MY", continente),
                    new Localidade("Maldivas", "MV", continente),
                    new Localidade("Mongólia", "MN", continente),
                    new Localidade("Myanmar", "MM", continente),
                    new Localidade("Nepal", "NP", continente),
                    new Localidade("Omã", "OM", continente),
                    new Localidade("Paquistão", "PK", continente),
                    new Localidade("Palestina", "PS", continente),
                    new Localidade("Filipinas", "PH", continente),
                    new Localidade("Qatar", "QA", continente),
                    new Localidade("Singapura", "SG", continente),
                    new Localidade("Sri Lanka", "LK", continente),
                    new Localidade("Síria", "SY", continente),
                    new Localidade("Tajiquistão", "TJ", continente),
                    new Localidade("Tailândia", "TH", continente),
                    new Localidade("Timor-Leste", "TL", continente),
                    new Localidade("Turcomenistão", "TM", continente),
                    new Localidade("Turquia", "TR", continente),
                    new Localidade("Uzbequistão", "UZ", continente),
                    new Localidade("Vietnã", "VN", continente),
                    new Localidade("Zimbábue", "ZW", continente)
            );
            repository.saveAll(paisesAsia);
        }
    }

    public void angola() {
        Optional<Localidade> angola = repository.findByNome("Angola");
        if (angola.isPresent()) {
            Localidade pais = angola.get();

            List<Localidade> provinciasAngola = Arrays.asList(
                    new Localidade("Bengo", "BE", pais),
                    new Localidade("Benguela", "BG", pais),
                    new Localidade("Bié", "BI", pais),
                    new Localidade("Cabinda", "CA", pais),
                    new Localidade("Cunene", "CN", pais),
                    new Localidade("Huambo", "HU", pais),
                    new Localidade("Huíla", "HL", pais),
                    new Localidade("Kwanza Norte", "KN", pais),
                    new Localidade("Kwanza Sul", "KS", pais),
                    new Localidade("Luanda", "LA", pais),
                    new Localidade("Lunda Norte", "LN", pais),
                    new Localidade("Lunda Sul", "LS", pais),
                    new Localidade("Malanje", "ML", pais),
                    new Localidade("Moxico", "MX", pais),
                    new Localidade("Namibe", "NA", pais),
                    new Localidade("Uíge", "UI", pais),
                    new Localidade("Zaire", "ZA", pais)
            );
            repository.saveAll(provinciasAngola);
        }
    }

    public void luanda() {
        Optional<Localidade> launda = repository.findByNome("Luanda");
        if (launda.isPresent()) {
            Localidade pais = launda.get();

            List<Localidade> provinciasLuanda = Arrays.asList(
                    new Localidade("Luanda", "LU", pais),
                    new Localidade("Belas", "BL", pais),
                    new Localidade("Cazenga", "CZ", pais),
                    new Localidade("Cuito Cuanavale", "CC", pais),
                    new Localidade("Quiçama", "QS", pais),
                    new Localidade("Viana", "VN", pais),
                    new Localidade("Icolo e Bengo", "IB", pais),
                    new Localidade("Kilamba Kiaxi", "KK", pais)
            );

            repository.saveAll(provinciasLuanda);
        }
    }

}
