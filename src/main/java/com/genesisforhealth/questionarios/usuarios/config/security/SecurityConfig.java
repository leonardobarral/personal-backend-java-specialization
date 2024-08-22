package com.genesisforhealth.questionarios.usuarios.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private VerificaToken verificaToken;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(
                        Session -> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                                .requestMatchers("/security/authority**").hasAuthority("GERENCIAR_AUTHORITIES")
//                        .requestMatchers(HttpMethod.POST, "/security/authority").hasAuthority("GERENCIAR_AUTHORITIES")
//                        .requestMatchers(HttpMethod.GET, "/security/authority/*").hasAuthority("GERENCIAR_AUTHORITIES")
//                        .requestMatchers(HttpMethod.GET, "/security/authority").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/security/authority").hasAuthority("GERENCIAR_AUTHORITIES")
//                        .requestMatchers(HttpMethod.DELETE, "/security/authority/*").hasAuthority("GERENCIAR_AUTHORITIES")

                                .requestMatchers("/security/role**").hasAuthority("GERENCIAR_ROLES")
//                        .requestMatchers(HttpMethod.POST, "/security/role").hasAuthority("GERENCIAR_ROLES")
//                        .requestMatchers(HttpMethod.GET, "/security/role/*").hasAuthority("GERENCIAR_ROLES")
//                        .requestMatchers(HttpMethod.GET, "/security/role").hasAnyRole("ADMIN","MANAGER")
//                        .requestMatchers(HttpMethod.PUT, "/security/role").hasAuthority("GERENCIAR_ROLES")
//                        .requestMatchers(HttpMethod.DELETE, "/security/role/*").hasAuthority("GERENCIAR_ROLES")

                                .requestMatchers("/security/user**").hasAuthority("GERENCIAR_USERS")
//                        .requestMatchers(HttpMethod.GET, "/security/user/*").hasAuthority("GERENCIAR_USERS")
//                        .requestMatchers(HttpMethod.GET, "/security/user").hasAnyRole("ADMIN","MANAGER","COORDINATOR")
//                        .requestMatchers(HttpMethod.PUT, "/security/user").hasAuthority("GERENCIAR_USERS")
//                        .requestMatchers(HttpMethod.DELETE, "/security/user/*").hasAuthority("GERENCIAR_USERS")

                                .requestMatchers(HttpMethod.POST, "/auth/register").hasAuthority("GERENCIAR_USERS")
                                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                                .anyRequest()
                                .authenticated()

                ).addFilterBefore(
                        verificaToken,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
