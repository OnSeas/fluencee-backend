package ueg.tc.fluencee.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ueg.tc.fluencee.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByIdUsuario(Long idUsuario);

    @Query(
            "SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
                    "FROM  Usuario u " +
                    "WHERE (u.email = :email) "
    )
    Boolean emailRepetido(@Param("email") String email);
}
