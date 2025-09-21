package br.com.carlosbrito.lojaPocoes.infraestrutura;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

/**
 * @author carlos.brito
 * Criado em: 20/09/2025
 */
@RestControllerAdvice
public class TratadorExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarException404(){
        System.out.println("Desenvolvedor Java");
        return ResponseEntity.notFound().build();

    }


}
