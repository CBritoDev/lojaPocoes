package br.com.carlosbrito.lojaPocoes.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author carlos.brito
 * Criado em: 21/09/2025
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
}
