package com.cibertec.alianzaweb.repository;

import com.cibertec.alianzaweb.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, String> {
    // Spring Boot hace la magia aquí. ¡No hay que escribir nada más!
}