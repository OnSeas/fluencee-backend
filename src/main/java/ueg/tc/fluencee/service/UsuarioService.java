package ueg.tc.fluencee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ueg.tc.fluencee.dto.UsuarioRequestDTO;
import ueg.tc.fluencee.model.Usuario;
import ueg.tc.fluencee.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuarioById(Long id){
        return usuarioRepository.findByIdUsuario(id);
    }

    public List<Usuario> listarTudo(){
        return usuarioRepository.findAll();
    }

    public Usuario inserir(UsuarioRequestDTO usuarioRequestDTO){

        // Inicialização de Usuário
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenha(usuarioRequestDTO.getSenha());
        usuario.setAtivado(Boolean.TRUE);

        return usuarioRepository.save(usuario);
    }

    public Usuario alterarNome(Long id, UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuarioBD = getUsuarioById(id);

        if(StringUtils.hasLength(usuarioRequestDTO.getNome())){
            usuarioBD.setNome(usuarioRequestDTO.getNome());
        }

        usuarioRepository.save(usuarioBD);
        return usuarioBD;
    }

    //TODO
    public Usuario desativar(Long id){
        Usuario usuarioBD = getUsuarioById(id);

        if(usuarioBD.getAtivado() == Boolean.TRUE){
            usuarioBD.setAtivado(Boolean.FALSE);
        }

        return usuarioBD;
    }
}
