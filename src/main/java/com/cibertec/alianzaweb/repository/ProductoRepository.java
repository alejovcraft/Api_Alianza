package com.cibertec.alianzaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.alianzaweb.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, String> {

}
