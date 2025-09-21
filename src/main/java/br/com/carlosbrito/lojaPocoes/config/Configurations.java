package br.com.carlosbrito.lojaPocoes.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author carlos.brito
 * Criado em: 19/09/2025
 */
@Configuration
public class Configurations {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
