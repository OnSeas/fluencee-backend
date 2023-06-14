package ueg.tc.fluencee.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
@Entity
@Table(name="tbl_usuario")
public class Usuario {

    @Id
    @SequenceGenerator(name = "musica_sequence", sequenceName = "musica_sequence", allocationSize = 1)
    @Column(name = "id_usuario", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "musica_sequence")
    private Long idUsuario;

    @Column(name = "c_nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "c_email", length = 256, nullable = false, unique = true)
    private String email;

    @Column(name = "c_senha", length = 256, nullable = false)
    private String senha; // Eu preciso criar no banco como varbinary? ou eu coloco aqui? to meio perdido nessa parte

    @PrePersist
    private void hashPassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        setSenha(bCryptPasswordEncoder.encode(this.senha));
    }

    @Column(name = "c_ativado", nullable = false)
    private Boolean ativado;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivado() {
        return ativado;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }
}
