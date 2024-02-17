package com.dualproject.ecommerce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    private final String productsPath = RoutesPath.routeProducts;
    private final String usersPath = RoutesPath.routeUsers;

    @Bean
    protected BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf.disable())
            .formLogin(login -> {
                login.usernameParameter("username");
                login.passwordParameter("password");

                login.loginPage("/login");
                login.loginProcessingUrl("/loginProcess");
            })
            .authorizeHttpRequests(req -> {
                req
                    .requestMatchers(
                        new AntPathRequestMatcher(usersPath + "/register", "POST"),
                        new AntPathRequestMatcher(usersPath + "/change_password", "PUT"))
                            .permitAll()
                    .requestMatchers(
                        new AntPathRequestMatcher(productsPath + "/add", "POST"))
                            .permitAll() // SOLO ADMINISTRADORES, temporalmente habilitado
                    .requestMatchers(
                        new AntPathRequestMatcher(productsPath + "/update/*", "PUT"),
                        new AntPathRequestMatcher(usersPath + "/update/*", "PUT"),
                        new AntPathRequestMatcher(usersPath + "/add/*", "PUT"))
                            .permitAll() // SOLO ADMINISTRADORES, temporalmente habilitado
                    .requestMatchers(
                        new AntPathRequestMatcher(productsPath + "/delete/*", "DELETE"),
                        new AntPathRequestMatcher(usersPath + "/delete/*", "DELETE"))
                            .permitAll() // SOLO ADMINISTRADORES, temporalmente habilitado
                    .requestMatchers(
                        new AntPathRequestMatcher(productsPath + "/**", "GET"),
                        new AntPathRequestMatcher(usersPath + "/**", "GET"))
                            .permitAll()
                    .requestMatchers("/img/**","/css/**","/js/**").permitAll()
                    .requestMatchers("/*").permitAll();
                    
            })
            .logout(logout -> {
                logout.logoutUrl("/logout");
                logout.logoutSuccessUrl("/");
            });

        return http.build();
    }
}
