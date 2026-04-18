package com.cibertec.alianzaweb.repository;

import com.cibertec.alianzaweb.model.VentaEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaEntradaRepository extends JpaRepository<VentaEntrada, String> {
}