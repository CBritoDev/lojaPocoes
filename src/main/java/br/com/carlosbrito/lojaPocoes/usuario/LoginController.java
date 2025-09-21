package br.com.carlosbrito.lojaPocoes.usuario;

import br.com.carlosbrito.lojaPocoes.config.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author carlos.brito
 * Criado em: 21/09/2025
 */
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticator;

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity validacaoCredenciaisUsuario(@RequestBody @Valid CredenciaisUsuarioDTO credenciais){
        // Cria um token de autenticação que contém as credenciais do usuário.
        // Este token é um objeto padrão do Spring Security
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(credenciais.getLogin(),credenciais.getPassword());
        // O AuthenticationManager (injetado no construtor) usa o token para autenticar o usuário.
        // Se a autenticação falhar, uma exceção é lançada.
        Authentication authentication = authenticator.authenticate(token);
        return ResponseEntity.ok(tokenService.criarToken((Usuario)authentication.getPrincipal()));

    }
}
