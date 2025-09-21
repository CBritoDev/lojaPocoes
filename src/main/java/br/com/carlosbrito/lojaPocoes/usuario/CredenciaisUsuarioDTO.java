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
public class CredenciaisUsuarioDTO {
    @NotBlank
    private String login;
    private String password;
}
