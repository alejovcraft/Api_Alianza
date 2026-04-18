package com.cibertec.alianzaweb.model; // Cambia esto por tu paquete real si es diferente

import jakarta.persistence.*; // Si usas una versión muy antigua de Java, podría ser javax.persistence.*
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "Ubicacion")
@Data
public class Ubicacion {

    @Id
    @Column(name = "id_ubicacion", length = 30)
    private String idUbicacion;

    @Column(name = "lugar", length = 50, unique = true)
    private String lugar;



}