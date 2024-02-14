package com.dualproject.ecommerce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf.disable())
            // Maneja las condiciones para entrar a X ruta. Por defecto manda al login
            // Un asterisco es para marcar cualquier ruta, y dos astericos para marcar cualquier ruta y subruta
            .authorizeHttpRequests(req -> {
                req
                    .requestMatchers("/css/**","/img/**","/js/**").permitAll()
                    .requestMatchers("/*").permitAll();
            });

        return http.build();
    }
}
