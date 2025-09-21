package br.com.carlosbrito.lojaPocoes.pocao;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author carlos.brito
 * Criado em: 19/09/2025
 */

@Entity //define como uma entidade
@Table(name = "pocao") //especifica que é uma tabela
@Data //engloba @Getter, @Setter, @EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Pocao {
    //Toda vez que criar um registro, o bd incrementa
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;
    private boolean disponivel;
    //Informa que o tipo de Enum é String
    @Enumerated(EnumType.STRING)
    private Efeito efeito;
    @Enumerated(EnumType.STRING)
    private Tamanho tamanho;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    //DTO = DATA TRANSFER OBJECT - entrega somente o que é útil e relevante
}
