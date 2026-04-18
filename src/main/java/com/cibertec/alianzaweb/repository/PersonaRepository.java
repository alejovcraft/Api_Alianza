package com.cibertec.alianzaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.alianzaweb.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository <Persona,String> {

}
