package br.com.carlosbrito.lojaPocoes.pocao;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author carlos.brito
 * Criado em: 19/09/2025
 */
//extends JpaRepository traz o CRUD
public interface PocaoRepository extends JpaRepository<Pocao,Long> {
}
