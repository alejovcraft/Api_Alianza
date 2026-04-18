package com.cibertec.alianzaweb.repository;

import com.cibertec.alianzaweb.model.EntradaIndividual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaIndividualRepository extends JpaRepository<EntradaIndividual, String> {
}