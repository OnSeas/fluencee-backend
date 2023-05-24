package ueg.tc.fluencee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ueg.tc.fluencee.dto.UsuarioDTO;
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

    public Usuario inserir(UsuarioDTO usuarioDTO){

        // Inicialização de Usuário
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setAtivado(Boolean.TRUE);

        return usuarioRepository.save(usuario);
    }

    public Usuario alterarNome(Long id, UsuarioDTO usuarioDTO){
        Usuario usuarioBD = getUsuarioById(id);

        if(StringUtils.hasLength(usuarioDTO.getNome())){
            usuarioBD.setNome(usuarioDTO.getNome());
        }

        usuarioRepository.save(usuarioBD);
        return usuarioBD;
    }

    public Usuario remover(Long id){
        Usuario usuario = getUsuarioById(id);
        usuarioRepository.delete(usuario);
        return usuario;
    }
}
