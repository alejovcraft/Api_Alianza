package com.cibertec.alianzaweb.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Entrada")
public class Entrada {

    @Id
    @Column(name = "id_entrada", length = 30)
    private String idEntrada;

    @Column(name = "evento", length = 100, nullable = false)
    private String evento;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "precio", precision = 8, scale = 2)
    private BigDecimal precio;

    @Column(name = "cantidad_disponible")
    private Integer cantidadDisponible;

    // ¡Aquí está la Llave Foránea!
    @ManyToOne
    @JoinColumn(name = "id_ubicacion")
    private Ubicacion ubicacion;

    
}