package com.cibertec.alianzaweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Puesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_puesto;

    private String nombre_puesto;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_rubro")
    private Rubro rubro;

    @ManyToOne
    @JoinColumn(name = "id_tiempo")
    private Tiempo tiempo;

    private String estado = "A";
}
