package com.cibertec.alianzaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.alianzaweb.model.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, String> {
}
