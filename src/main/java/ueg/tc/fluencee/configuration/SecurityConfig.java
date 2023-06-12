package ueg.tc.fluencee.configuration;

import br.ueg.prog.webi.api.config.ApiSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig extends ApiSecurityConfig {
    @Override
    protected void configureHttpSecurity(HttpSecurity http) throws Exception {

    }

    // Configurações para permitir que se possa fazer cadastro e login sem autenticação
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                // ...
                .authorizeHttpRequests(authorize -> {
                    try {
                        authorize
                                .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                                .requestMatchers("/fluencee/usuario", "/login",
                                        // TODO tirar os que estão a seguir, após realizar o login.
                                        "/fluencee/usuario/{idUsuario}", "/fluencee/usuario/desativar/{idUsuario}").permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .csrf().disable(); // Pelo que entendo o csrf proíbe post e put, então precisei tirar
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        return http.build();
    }
}
