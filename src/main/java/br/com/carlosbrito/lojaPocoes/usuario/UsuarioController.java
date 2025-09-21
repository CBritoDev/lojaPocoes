package br.com.carlosbrito.lojaPocoes.usuario;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author carlos.brito
 * Criado em: 21/09/2025
 */
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioDTO dto, UriComponentsBuilder uriBuilder){
       UsuarioDTO usuarioDTO = usuarioService.criarUsuario(dto);
        URI endereco = uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioDTO.getId()).toUri();
        return ResponseEntity.created(endereco).body(usuarioDTO);
    }


}
