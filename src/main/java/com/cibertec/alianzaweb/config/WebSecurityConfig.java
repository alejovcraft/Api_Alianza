package com.cibertec.alianzaweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// ... tus otros imports ...
import org.springframework.security.config.Customizer; // <-- 1. ¡Agrega este import vital!
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) // <-- 2. ¡LA LLAVE MÁGICA PARA ANGULAR!
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers("/api/ventas/**").permitAll() // <-- Prueba siendo más específico aquí
            	    .requestMatchers("/api/**").permitAll()
            	    .anyRequest().authenticated()
            	);
        return http.build();
    }
}