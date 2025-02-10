package edu.ucan.stock.controller;

import edu.ucan.stock.config.security.TokenService;
import edu.ucan.stock.dto.records.AuthenticationRecord;
import edu.ucan.stock.dto.records.LoginResponseRecord;
import edu.ucan.stock.dto.records.RegisterRecord;
import edu.ucan.stock.entities.Usuario;
import edu.ucan.stock.repositories.UsuarioRepository;
import edu.ucan.stock.utils.BaseController;
import edu.ucan.stock.utils.ResponseBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController extends BaseController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRecord data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseRecord(token));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseBody> register(@RequestBody @Valid RegisterRecord data) {
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario usuario = new Usuario(data.login(), encryptedPassword, data.tipoUsuario());

        return this.created("Adicionado com sucesso.", this.repository.save(usuario));

    }
}
