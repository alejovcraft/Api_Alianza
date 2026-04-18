package com.cibertec.alianzaweb.dto;

import lombok.Data;

@Data
public class RegistroRequest {
    // Datos para la tabla Usuario
    private String username;
    private String password;
    
    // Datos para la tabla Persona
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
}