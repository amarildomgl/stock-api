package edu.ucan.stock.config;

import edu.ucan.stock.services.ExcelImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ExcelImportService excelImportService;

    @Override
    public void run(String... args) throws Exception {
        File file = new ClassPathResource("portfolio.xlsx").getFile();
        excelImportService.importUsers(file);
    }
}