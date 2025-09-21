package br.com.carlosbrito.lojaPocoes.pocao;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author carlos.brito
 * Criado em: 19/09/2025
 */
@Service
@RequiredArgsConstructor
public class PocaoService {

    private final PocaoRepository pocaoRepository;

    private final ModelMapper modelMapper;

    public PocaoDTO criarPocao(PocaoDTO dto) {
        Pocao pocao = modelMapper.map(dto,Pocao.class); //converte dto
        pocaoRepository.save(pocao);

        return modelMapper.map(pocao, PocaoDTO.class);
    }

    public Page<PocaoDTO> buscarTodos(Pageable paginacao) {
        return pocaoRepository
                .findAll(paginacao)
                .map(pocao -> modelMapper.map(pocao, PocaoDTO.class));
    }

    public PocaoDTO buscarPorID(Long id) {
        Pocao pocao = pocaoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
        return modelMapper.map(pocao, PocaoDTO.class);
    }

    public PocaoDTO atualizarPocao(Long id, PocaoDTO pocaoDTO) {
        Pocao pocao =  modelMapper.map(pocaoDTO, Pocao.class);
        //seta o id novamente para garantir o retorno do ModelMapper
        pocao.setId(id);
        pocao =  pocaoRepository.save(pocao);
        return modelMapper.map(pocao, PocaoDTO.class);
    }

    public void excluir(Long id) {
        pocaoRepository.deleteById(id);
    }
}
