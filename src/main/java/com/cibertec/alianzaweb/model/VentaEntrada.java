package com.cibertec.alianzaweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // <-- ¡IMPORTANTE!
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

//... (tus otros imports)
import com.fasterxml.jackson.annotation.JsonBackReference; // <-- Importante

@Data
@Entity
@Table(name = "ventaentrada")
public class VentaEntrada {
    @Id
    @Column(name = "id_ventaentrada", length = 30)
    private String idVentaentrada;
    
    @ManyToOne
    @JoinColumn(name = "id_venta")
    @JsonBackReference 
    private Venta venta;
    
    // ❌ BORRA ESTE BLOQUE QUE CAUSA EL ERROR:
    // @ManyToOne
    // @JoinColumn(name = "id_entradaindividual")
    // @JsonProperty("entradaindividual")
    // private EntradaIndividual entradaIndividual;
    
    // ✅ REEMPLÁZALO POR ESTA LISTA:
    @OneToMany(mappedBy = "ventaEntrada", cascade = CascadeType.ALL)
    @JsonProperty("entradasIndividuales")
    private List<EntradaIndividual> entradasIndividuales;
    
    @ManyToOne
    @JoinColumn(name = "id_entrada")
    private Entrada entrada;
    
    private Integer cantidad;
}