package com.cibertec.alianzaweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
	
    @Id
    private String id_producto;

    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String imagen; 



}
