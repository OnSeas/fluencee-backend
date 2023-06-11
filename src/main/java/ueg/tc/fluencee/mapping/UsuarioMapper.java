package ueg.tc.fluencee.mapping;


import org.mapstruct.Mapper;
import ueg.tc.fluencee.dto.UsuarioRequestDTO;
import ueg.tc.fluencee.dto.UsuarioResponseDTO;
import ueg.tc.fluencee.model.Usuario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toModel(UsuarioRequestDTO usuarioRequestDTO);

    UsuarioResponseDTO toDTO(Usuario usuario);

    List<UsuarioResponseDTO> toUsuarioResponseDTO(List<Usuario> usuarios);

}
