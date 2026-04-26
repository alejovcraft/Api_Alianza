package com.cibertec.alianzaweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @Column(length = 30)
    private String id_categoria;

    private String nombre_cat;

    private String estado; // A o I
}
