package com.cibertec.alianzaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.alianzaweb.model.Tiempo;

public interface TiempoRepository extends JpaRepository<Tiempo, Integer> {
	
}
