package com.cibertec.alianzaweb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "entradaindividual")
public class EntradaIndividual {

    @Id
    @Column(name = "id_entradaindividual", length = 30)
    private String idEntradaIndividual;

    // Usamos JsonIgnore para que no intente serializar la venta completa de nuevo
    @ManyToOne
    @JoinColumn(name = "id_ventaentrada") // Nombre de la columna física en MySQL
    private VentaEntrada ventaEntrada; // Este nombre debe ser igual al mappedBy de arriba

    @ManyToOne
    @JoinColumn(name = "id_entrada", nullable = false)
    private Entrada entrada;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_duenoentrada", referencedColumnName = "id_duenoentrada") 
    private DuenoEntrada duenoEntrada;
}