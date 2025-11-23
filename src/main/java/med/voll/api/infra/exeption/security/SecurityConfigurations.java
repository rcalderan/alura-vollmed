package med.voll.api.infra.exeption.security;

import med.voll.api.domain.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(securedEnabled = true) habilitar caso queira controlar o metodo. exc Delete sob role especifica
public class SecurityConfigurations{

    @Value("${api.security.dev.allow_all:true}")
    private Boolean byPass;

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return !byPass ? http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/login").permitAll()
//                        .requestMatchers( "/consultas/**")
//                            .hasAnyRole(Perfil.PACIENTE.name(),Perfil.ATENDENTE.name(),Perfil.MEDICO.name())
//                        .requestMatchers("/pacientes/**")
//                            .hasRole(Perfil.PACIENTE.name())
//                        .requestMatchers(HttpMethod.GET, "/medicos/**")
//                            .hasAnyRole(Perfil.PACIENTE.name(),Perfil.ATENDENTE.name())
//                        .requestMatchers("/medicos/**")
//                            .hasRole(Perfil.PACIENTE.name())
                        // se quiser restringir sob um Role especifico:
                        //.requestMatchers(HttpMethod.DELETE, "/medicos").hasRole("ADMIN")
                        .anyRequest().authenticated()

                ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build()
        : http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/login").permitAll()

                        //.requestMatchers("/v3/api-docs/**","/swagger-ui/index.html
                        // ","swagger-ui/**").permitAll() //enable swagger
                        // se quiser restringir sob um Role especifico:
                        //.requestMatchers(HttpMethod.DELETE, "/medicos").hasRole("ADMIN")
                        //.anyRequest().authenticated()

                //).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
