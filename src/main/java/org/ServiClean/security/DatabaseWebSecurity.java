package org.ServiClean.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {
    @Bean
    public UserDetailsManager customUsers (DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        users.setUsersByUsernameQuery("select correo, contrasena, estado from usuarios where correo = ?");
        users.setAuthoritiesByUsernameQuery("select u.correo, r.nombre from usuario_rol ur " +
                "inner join usuarios u on u.id_usuario = ur.usuario_id " +
                "inner join roles r on r.id_rol = ur.rol_id " +
                "where u.correo = ?");

        return users;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                //aperturar el acceso a los recursos estaticos
                .requestMatchers("/assets/**", "/css/**", "/js/**", "img/**" ).permitAll()
                // las vistas publicas no requieren autenticacion
                .requestMatchers("/", "/privacy", "/terms").permitAll()

                //Asignar permisos a Url por roles
                .requestMatchers("/roles/**").hasAnyAuthority("admin")
                .requestMatchers("/usuarios/**").hasAnyAuthority("admin")
                .requestMatchers("/equipos/**").hasAnyAuthority("admin")
                .requestMatchers("/auditorias/**").hasAnyAuthority("admin")
                .requestMatchers("/reportes/**").hasAnyAuthority("admin")
                .requestMatchers("/historial_tareas/**").hasAnyAuthority("admin", "Tecnico")
                .requestMatchers("/tareas/**").hasAnyAuthority("admin", "Tecnico")

                //todas las demas vistas requieren autenticacion
                .anyRequest().authenticated());
        http.formLogin(form -> form.loginPage("/login").permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}