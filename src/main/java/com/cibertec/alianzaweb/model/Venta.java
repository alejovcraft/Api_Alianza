package com.cibertec.alianzaweb.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Venta")
public class Venta {
    @Id
    @Column(name = "id_venta", length = 30) // <-- ¡El escudo protector!
    private String idVenta;
    private LocalDateTime fecha_venta;
    private BigDecimal total;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}