package com.cibertec.alianzaweb.repository;

import com.cibertec.alianzaweb.model.DuenoEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // <-- Importante
// Tiene que decir "interface" y "extends JpaRepository"
public interface DuenoEntradaRepository extends JpaRepository<DuenoEntrada, String> {
}