package com.cibertec.alianzaweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posicion {

    @Id
    @Column(length = 30)
    private String id_posicion;

    private String nombre_pos;
}
