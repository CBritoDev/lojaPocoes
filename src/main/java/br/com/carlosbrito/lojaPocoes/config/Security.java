package br.com.carlosbrito.lojaPocoes.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author carlos.brito
 * Criado em: 21/09/2025
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {

    private final Filter filter;

    @Bean
    public SecurityFilterChain filtroSeguranca(HttpSecurity http) throws Exception{
        //ataques cross-site-request-forgery
        http
                // Desabilita a proteção CSRF para uma API stateless
                .csrf(csrf -> csrf.disable())

                // Configura o gerenciamento de sessão para ser 'STATELESS'
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //diz ao spring qual filtro usar primeiro
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)

                // Configura as regras de autorização das requisições HTTP
                .authorizeHttpRequests(auth -> auth
                        // Permite o acesso público ao endpoint de login
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        //tudo depois do ** está liberado
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        // Requer autenticação para todas as outras requisições
                        .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
