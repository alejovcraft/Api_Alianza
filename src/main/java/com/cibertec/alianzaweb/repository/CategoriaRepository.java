package com.cibertec.alianzaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.alianzaweb.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {
}
