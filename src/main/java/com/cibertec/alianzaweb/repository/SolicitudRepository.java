package com.cibertec.alianzaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.alianzaweb.model.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
	
}
