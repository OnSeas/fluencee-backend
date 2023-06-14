package ueg.tc.fluencee.service;

import br.ueg.prog.webi.api.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ueg.tc.fluencee.dto.AlterarSenhaDTO;
import ueg.tc.fluencee.dto.UsuarioRequestDTO;
import ueg.tc.fluencee.dto.UsuarioResponseDTO;
import ueg.tc.fluencee.exception.ServiceException;
import ueg.tc.fluencee.exception.SistemaMessageCode;
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

    // Validações
    public Boolean validarSenha(String senha){
        if (senha.length() < 8 || senha.length() > 35) {
            throw new ServiceException(SistemaMessageCode.COMPRIMENTO_SENHA);
        } else if (!senha.matches("^(?=.*[a-zA-Z])(?=.*\\d).*$")) {
            throw new ServiceException(SistemaMessageCode.SENHA_LETRA_NUMERO);
        } else {
            return Boolean.TRUE;
        }
    }

    public Boolean validarEmail(String email){
        if(usuarioRepository.emailRepetido(email)){
            throw new ServiceException(SistemaMessageCode.EMAIL_REPETIDO);
        } else if (email.length() > 256){
            throw new ServiceException(SistemaMessageCode.COMPRIMENTO_EMAIL);
        } else if (!email.contains("@") || !email.contains(".") || email.indexOf("@") > email.indexOf(".")) {
            throw new ServiceException(SistemaMessageCode.EMAIL_INVALIDO);
        } else{
            return Boolean.TRUE;
        }
    }

    public Boolean validarNome(String nome){
        if (nome.length() < 3 || nome.length() > 200) {
            throw new ServiceException(SistemaMessageCode.COMPRIMENTO_NOME);
        } else {
            return Boolean.TRUE;
        }
    }



    //Endpoints
    public UsuarioResponseDTO getUsuarioById(Long id){
        UsuarioResponseDTO usuarioResponseDTO;
        usuarioResponseDTO = usuarioMapper.toDTO(usuarioRepository.findByIdUsuario(id));

        if(usuarioResponseDTO.getAtivado() == Boolean.FALSE){
            throw new ServiceException(SistemaMessageCode.USUARIO_INATIVO);
        }

        if(usuarioResponseDTO == null){
            throw new ServiceException(SistemaMessageCode.USUARIO_NAO_ENCONTRADO);
        }

        return usuarioResponseDTO;
    }

    public List<UsuarioResponseDTO> listarTudo(){
        return usuarioMapper.toUsuarioResponseDTO(usuarioRepository.findAll());
    }

    public UsuarioResponseDTO inserir(UsuarioRequestDTO usuarioRequestDTO) {

        // Inicialização de Usuário
        Usuario usuario;
        usuario = usuarioMapper.toModel(usuarioRequestDTO);
        usuario.setAtivado(Boolean.TRUE);

        if(validarNome(usuarioRequestDTO.getNome()) && validarEmail(usuarioRequestDTO.getEmail()) && validarSenha(usuarioRequestDTO.getSenha())) {
            usuarioRepository.save(usuario);
        } else {
            throw new ServiceException(SistemaMessageCode.ERRO_CRIAR_USUARIO);
        }

        UsuarioResponseDTO usuarioResponseDTO;
        usuarioResponseDTO = usuarioMapper.toDTO(usuario);
        return usuarioResponseDTO;
    }

    public UsuarioResponseDTO alterarNome(Long id, UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuarioBD = usuarioRepository.findByIdUsuario(id);

        if(usuarioBD.getAtivado() == Boolean.FALSE){
            throw new ServiceException(SistemaMessageCode.USUARIO_INATIVO);
        }

        if(validarNome(usuarioRequestDTO.getNome())){
            usuarioBD.setNome(usuarioRequestDTO.getNome());
            usuarioRepository.save(usuarioBD);
        }

        UsuarioResponseDTO usuarioResponseDTO;
        usuarioResponseDTO = usuarioMapper.toDTO(usuarioBD);
        return usuarioResponseDTO;
    }

    public UsuarioResponseDTO trocarSenha(Long id, AlterarSenhaDTO alterarSenhaDTO){
        Usuario usuarioBD = usuarioRepository.findByIdUsuario(id);

        if(usuarioBD.getAtivado() == Boolean.FALSE){
            throw new ServiceException(SistemaMessageCode.USUARIO_INATIVO);
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (!alterarSenhaDTO.getEmail().equals(usuarioBD.getEmail())){
            throw new ServiceException(SistemaMessageCode.EMAIL_ERRADO);
        } else if(!bCryptPasswordEncoder.matches(alterarSenhaDTO.getSenhaAntiga(), usuarioBD.getSenha())) {
            throw new ServiceException(SistemaMessageCode.SENHA_ERRADA);
        } else if (validarSenha(alterarSenhaDTO.getSenhaNova())){
            String novaSenha = bCryptPasswordEncoder.encode(alterarSenhaDTO.getSenhaNova());
            usuarioBD.setSenha(novaSenha);
            usuarioRepository.save(usuarioBD);
        }

        UsuarioResponseDTO usuarioResponseDTO;
        usuarioResponseDTO = usuarioMapper.toDTO(usuarioBD);
        return usuarioResponseDTO;
    }

    public UsuarioResponseDTO desativar(Long id){
        Usuario usuarioBD = usuarioRepository.findByIdUsuario(id);

        if(usuarioBD.getAtivado() == Boolean.FALSE){
            throw new ServiceException(SistemaMessageCode.USUARIO_INATIVO);
        } else {
            usuarioBD.setAtivado(Boolean.FALSE);
            usuarioRepository.save(usuarioBD);
        }

        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.toDTO(usuarioBD);
        return usuarioResponseDTO;
    }
}
