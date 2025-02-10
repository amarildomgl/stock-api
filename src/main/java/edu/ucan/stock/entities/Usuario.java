package edu.ucan.stock.entities;

import edu.ucan.stock.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_usuario")
    private Integer pkUsuario;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    public Usuario(String login, String senha, TipoUsuario tipoUsuario) {
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (this.tipoUsuario) {
            case ADMINISTRADOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_GESTOR"),
                    new SimpleGrantedAuthority("ROLE_COLABORADOR"),
                    new SimpleGrantedAuthority("ROLE_CLIENTE"),
                    new SimpleGrantedAuthority("ROLE_FORNECEDOR")
            );
            case GESTOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_GESTOR"),
                    new SimpleGrantedAuthority("ROLE_COLABORADOR"),
                    new SimpleGrantedAuthority("ROLE_CLIENTE"),
                    new SimpleGrantedAuthority("ROLE_FORNECEDOR")
            );
            case COLABORADOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_COLABORADOR"),
                    new SimpleGrantedAuthority("ROLE_CLIENTE"),
                    new SimpleGrantedAuthority("ROLE_FORNECEDOR")
            );
            case CLIENTE -> List.of(
                    new SimpleGrantedAuthority("ROLE_CLIENTE")
            );
            case FORNECEDOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_FORNECEDOR")
            );
        };
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
