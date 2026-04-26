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
public class Tiempo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tiempo;

    private String tipo_tiempo;

    private String estado = "A";
}
