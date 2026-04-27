package com.cibertec.alianzaweb.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List; // <-- ¡Nuevo!

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany; // <-- ¡Nuevo!
import jakarta.persistence.Table;
import lombok.Data;
//... (tus otros imports)
import com.fasterxml.jackson.annotation.JsonManagedReference; // <-- Importante

@Data
@Entity
@Table(name = "Venta")
public class Venta {
 @Id
 @Column(name = "id_venta", length = 30)
 private String idVenta;
 
 private LocalDateTime fecha_venta;
 private BigDecimal total;
 
 @ManyToOne
 @JoinColumn(name = "id_usuario")
 private Usuario usuario;

 // 👇 AQUÍ ESTÁ EL CAMBIO 👇
 @JsonManagedReference // <-- ¡Obliga a enviar la lista a Angular!
 @OneToMany(mappedBy = "venta", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
 private List<VentaEntrada> ventaEntradas;
}