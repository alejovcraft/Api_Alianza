package com.cibertec.alianzaweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "VentaEntrada")
public class VentaEntrada {
    @Id
    @Column(name = "id_ventaentrada", length = 30) // <-- ¡El escudo protector!
    private String idVentaentrada;
    
    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;
    
    @ManyToOne
    @JoinColumn(name = "id_entrada")
    private Entrada entrada;
    
    private Integer cantidad;
}
