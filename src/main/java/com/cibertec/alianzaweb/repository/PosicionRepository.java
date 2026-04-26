package com.cibertec.alianzaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.alianzaweb.model.Posicion;

public interface PosicionRepository extends JpaRepository<Posicion, String> {
}
