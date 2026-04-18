package com.cibertec.alianzaweb.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 1. La "Firma" del club (Llave secreta muy segura generada automáticamente)
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    // 2. Tiempo de vida del pase VIP: 24 horas (en milisegundos)
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; 

    // 3. Método que arma el Token
    public String generarToken(String username, String rol) {
        return Jwts.builder()
                .setSubject(username) // A quién le pertenece el token
                .claim("rol", rol) // Metemos su rol adentro de la pulsera
                .setIssuedAt(new Date()) // Fecha en la que se creó
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION)) // Fecha en la que caduca
                .signWith(SECRET_KEY) // Lo firmamos para que no lo falsifiquen
                .compact(); // Lo convertimos a un String largo
    }
}