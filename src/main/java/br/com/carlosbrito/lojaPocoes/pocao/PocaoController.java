package br.com.carlosbrito.lojaPocoes.pocao;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author carlos.brito
 * Criado em: 19/09/2025
 */
@RestController//diz ao Spring que é um controller do rest
@RequestMapping("/pocoes") //cria end point
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class PocaoController {

    private final PocaoService pocaoService;

    @PostMapping
    public ResponseEntity<PocaoDTO> cadastrar(@RequestBody @Valid PocaoDTO dto, UriComponentsBuilder uriBuilder){
        PocaoDTO pocaoDTO = pocaoService.criarPocao(dto);
        URI endereco =  uriBuilder.path("/pocoes/{id}").buildAndExpand(pocaoDTO.getId()).toUri();
        return ResponseEntity.created(endereco).body(pocaoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PocaoDTO>> buscarTodos(@PageableDefault(size = 10)Pageable paginacao){
        //Limite quantidade de retorno por pagina
        Page<PocaoDTO> pocoes =  pocaoService.buscarTodos(paginacao);
        return ResponseEntity.ok(pocoes);
    }

    //Pega dinamicamente com o PathVariable a variavel setada no url, marcada pelo GetMapping {}
    @GetMapping("/{id}")
    public ResponseEntity<PocaoDTO> buscarPorID(@PathVariable @NotNull Long id){
        PocaoDTO pocaoDTO = pocaoService.buscarPorID(id);
        return ResponseEntity.ok(pocaoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PocaoDTO> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PocaoDTO dto){
        PocaoDTO pocaoAtualizada =  pocaoService.atualizarPocao(id,dto);
        return ResponseEntity.ok(pocaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @NotNull Long id){
        pocaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
