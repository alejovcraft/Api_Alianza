package com.cibertec.alianzaweb.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jugador {

    @Id
    @Column(length = 30)
    private String id_jugador;

    private String nombre_juga;
    private String apellido_juga;
    private String sexo;

    private LocalDate fecha_nacimiento;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_posicion")
    private Posicion posicion;

    private String estado; // A o I
}
