package com.cibertec.alianzaweb.repository;

import com.cibertec.alianzaweb.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, String> {
}