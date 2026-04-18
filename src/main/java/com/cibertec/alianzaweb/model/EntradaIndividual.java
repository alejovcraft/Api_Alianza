package com.cibertec.alianzaweb.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "EntradaIndividual")
public class EntradaIndividual {

    @Id
    @Column(name = "id_entrada_individual", length = 30)
    private String idEntradaIndividual;

    @ManyToOne
    @JoinColumn(name = "id_ventaentrada", nullable = false)
    private VentaEntrada ventaEntrada;

    @ManyToOne
    @JoinColumn(name = "id_entrada", nullable = false)
    private Entrada entrada;
}