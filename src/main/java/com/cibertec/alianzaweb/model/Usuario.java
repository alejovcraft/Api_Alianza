package com.cibertec.alianzaweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "Usuario")
@Data
public class Usuario {
    
    @Id
    @Column(length = 30) 
    private String id_usuario;
    
    @Column(name = "nombre_usuario", unique = true, length = 50)
    private String username;
    
    @JsonIgnore // Para que la contraseña no viaje al listar
    @Column(name = "contrasena", length = 300) 
    private String password;

    // Relación con Persona
    @OneToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_rol") 
    private Rol rol;
    
    // Spring Boot le asignará la fecha actual al crear el objeto
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date fecha_creacion;
}