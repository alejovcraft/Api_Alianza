package com.cibertec.alianzaweb.repository;

import com.cibertec.alianzaweb.model.Venta;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, String> {

	@EntityGraph(attributePaths = {
		    "ventaEntradas", 
		    "ventaEntradas.entradaIndividual", // Debe coincidir con el nombre de la VARIABLE en VentaEntrada
		    "ventaEntradas.entradaIndividual.duenoEntrada",
		    "ventaEntradas.entrada"
		})
		List<Venta> findByUsuario_Username(String username);
}