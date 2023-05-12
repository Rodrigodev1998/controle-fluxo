package br.com.controlefluxocaixa.usuario.repository;

import br.com.controlefluxocaixa.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}

