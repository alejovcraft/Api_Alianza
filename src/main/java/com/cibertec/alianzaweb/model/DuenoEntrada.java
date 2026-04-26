package com.cibertec.alianzaweb.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "DuenoEntrada")
public class DuenoEntrada {

    @Id
    @Column(name = "id_duenoentrada", length = 30)
    private String idDuenoentrada;

    @OneToOne
    @JoinColumn(name = "id_entrada_individual", nullable = false)
    private EntradaIndividual entradaIndividual;

    private String nombre;
    private String apellido_p;
    private String apellido_m;
    
    @Column(length = 8)
    private String dni;
    
    private String telefono;
    private String genero;
}