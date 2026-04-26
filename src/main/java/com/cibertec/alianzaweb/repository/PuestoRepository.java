package com.cibertec.alianzaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.alianzaweb.model.Puesto;

public interface PuestoRepository extends JpaRepository<Puesto, Integer> {
	
}
