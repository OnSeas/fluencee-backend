package ueg.tc.fluencee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ueg.tc.fluencee.dto.UsuarioRequestDTO;
import ueg.tc.fluencee.dto.UsuarioResponseDTO;
import ueg.tc.fluencee.mapping.UsuarioMapper;
import ueg.tc.fluencee.model.Usuario;
import ueg.tc.fluencee.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;


    public UsuarioResponseDTO getUsuarioById(Long id){
        UsuarioResponseDTO usuarioResponseDTO;
        usuarioResponseDTO = usuarioMapper.toDTO(usuarioRepository.findByIdUsuario(id));
        return usuarioResponseDTO;
    }

    public List<UsuarioResponseDTO> listarTudo(){
        return usuarioMapper.toUsuarioResponseDTO(usuarioRepository.findAll());
    }

    public UsuarioResponseDTO inserir(UsuarioRequestDTO usuarioRequestDTO){

        UsuarioResponseDTO usuarioResponseDTO;

        // Inicialização de Usuário
        Usuario usuario = new Usuario();
        usuario = usuarioMapper.toModel(usuarioRequestDTO);
        usuario.setAtivado(Boolean.TRUE);

        usuarioRepository.save(usuario);

        usuarioResponseDTO = usuarioMapper.toDTO(usuario);
        return usuarioResponseDTO;
    }

    public UsuarioResponseDTO alterarNome(Long id, UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuarioBD = usuarioRepository.findByIdUsuario(id);

        if(StringUtils.hasLength(usuarioRequestDTO.getNome())){
            usuarioBD.setNome(usuarioRequestDTO.getNome());
        }

        usuarioRepository.save(usuarioBD);

        UsuarioResponseDTO usuarioResponseDTO;
        usuarioResponseDTO = usuarioMapper.toDTO(usuarioBD);

        return usuarioResponseDTO;
    }

    public UsuarioResponseDTO desativar(Long id){
        Usuario usuarioBD = usuarioRepository.findByIdUsuario(id);

        if(usuarioBD.getAtivado() == Boolean.TRUE){
            usuarioBD.setAtivado(Boolean.FALSE);
        }

        usuarioRepository.save(usuarioBD);

        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.toDTO(usuarioBD);
        return usuarioResponseDTO;
    }
}
