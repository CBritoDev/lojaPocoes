package br.com.carlosbrito.lojaPocoes.config;

import br.com.carlosbrito.lojaPocoes.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author carlos.brito
 * Criado em: 21/09/2025
 */
@Component
@RequiredArgsConstructor
public class Filter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = buscarToken(request);
        if(token != null){
            var usuarioLogin = tokenService.buscarUsuarioToken(token);
            var usuario = usuarioRepository.findByLogin(usuarioLogin);

            var authenticator = new UsernamePasswordAuthenticationToken(usuario,null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticator);
        }

        filterChain.doFilter(request,response);
    }

    private String buscarToken(HttpServletRequest request){
        var authorization = request.getHeader("Authorization");

        if(authorization != null) {
            return authorization.replace("Bearer ", "");
        }
        return null;
    }
}
