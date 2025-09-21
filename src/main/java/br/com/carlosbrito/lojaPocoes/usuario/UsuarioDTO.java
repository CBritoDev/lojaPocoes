package br.com.carlosbrito.lojaPocoes.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author carlos.brito
 * Criado em: 21/09/2025
 */
@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
