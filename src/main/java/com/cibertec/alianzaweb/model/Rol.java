package com.cibertec.alianzaweb.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Rol")
@Data
public class Rol {
    @Id
    @Column(length = 30) 
    private String id_rol;
    @Column(name = "nombre_rol", unique = true, length = 50)
    private String nombre_rol;
}