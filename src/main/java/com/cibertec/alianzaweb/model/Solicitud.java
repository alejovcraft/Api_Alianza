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
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_solicitud;

    @ManyToOne
    @JoinColumn(name = "id_postulante")
    private Postulante postulante;

    @ManyToOne
    @JoinColumn(name = "id_puesto")
    private Puesto puesto;

    private String estado = "Pendiente";
}
