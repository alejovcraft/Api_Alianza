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
public class Rubro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rubro;

    private String nombre_rubro;

    private String estado = "A";
}
