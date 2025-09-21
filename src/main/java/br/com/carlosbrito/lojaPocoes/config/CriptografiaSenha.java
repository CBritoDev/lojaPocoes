package br.com.carlosbrito.lojaPocoes.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author carlos.brito
 * Criado em: 21/09/2025
 */


public class CriptografiaSenha {

    private static final BCryptPasswordEncoder enconder =  new BCryptPasswordEncoder();

    public static String criptografia(String password){
        return enconder.encode(password);
    }
}
