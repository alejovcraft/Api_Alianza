package com.cibertec.alianzaweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Postulante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_postulante;

    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private String direccion;

    private String estado = "A";
}
