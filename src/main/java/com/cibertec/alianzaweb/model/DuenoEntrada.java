package com.cibertec.alianzaweb.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "duenoentrada")
public class DuenoEntrada {

    @Id
    @Column(name = "id_duenoentrada", length = 30)
    private String idDuenoentrada;

    // ❌ ELIMINAMOS la relación OneToOne hacia EntradaIndividual aquí
    // para romper el bucle infinito que causa el Error 500.

    private String nombre;
    private String apellido_p;
    private String apellido_m;
    
    @Column(length = 8)
    private String dni;
    
    private String telefono;
    private String genero;
}