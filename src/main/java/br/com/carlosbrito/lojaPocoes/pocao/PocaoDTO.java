package br.com.carlosbrito.lojaPocoes.pocao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author carlos.brito
 * Criado em: 19/09/2025
 */

@Getter
@Setter
public class PocaoDTO {

    private Long id;
    @NotBlank
    private String nome;
    @Positive
    private BigDecimal preco;
    private boolean disponivel;
    private Efeito efeito;
    private Tamanho tamanho;
    private Categoria categoria;
}
