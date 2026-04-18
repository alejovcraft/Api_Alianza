package com.cibertec.alianzaweb.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Persona")
@Data
public class Persona {
    
    @Id
    @Column(length = 30)
    private String id_persona;
    
    @Column(unique = true, length = 8)
    private String dni;
    
    @Column(length = 50)
    private String nombre_persona;
    
    @Column(length = 50)
    private String apellido_persona;
    
    @Column(unique = true, length = 9)
    private String telefono;
    
    @Column(unique = true, length = 50)
    private String email;
}