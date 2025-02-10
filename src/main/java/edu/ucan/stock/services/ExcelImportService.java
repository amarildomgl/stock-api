package edu.ucan.stock.services;

import edu.ucan.stock.entities.Usuario;
import edu.ucan.stock.enums.TipoUsuario;
import edu.ucan.stock.repositories.UsuarioRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelImportService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void importUsers(File file) throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {
            // iniciar da segunda folha (index 1)
            Sheet sheet = workbook.getSheetAt(1);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // saltar cabeçalho
                }
                String login = row.getCell(0).getStringCellValue();
                String senha = row.getCell(1).getStringCellValue();
                String tipoUsuarioStr = row.getCell(2).getStringCellValue();
                TipoUsuario tipoUsuario = TipoUsuario.valueOf(tipoUsuarioStr);

                String encryptedPassword = passwordEncoder.encode(senha);
                Usuario usuario = new Usuario(login, encryptedPassword, tipoUsuario);
                usuarios.add(usuario);
            }
        }

        usuarioRepository.saveAll(usuarios);
    }
}