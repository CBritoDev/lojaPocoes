package br.com.carlosbrito.lojaPocoes.usuario;

import br.com.carlosbrito.lojaPocoes.config.CriptografiaSenha;
import br.com.carlosbrito.lojaPocoes.pocao.PocaoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author carlos.brito
 * Criado em: 21/09/2025
 */
@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login);
    }

    public UsuarioDTO criarUsuario(@Valid UsuarioDTO dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);

        String senhaCriptografada = CriptografiaSenha.criptografia(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);

        usuarioRepository.save(usuario);
        return modelMapper.map(usuario, UsuarioDTO.class);
    }
}
