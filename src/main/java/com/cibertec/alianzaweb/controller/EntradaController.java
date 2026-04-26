package com.cibertec.alianzaweb.controller;

import com.cibertec.alianzaweb.model.Entrada;
import com.cibertec.alianzaweb.repository.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/entradas")
@CrossOrigin(origins = "http://localhost:4200")
public class EntradaController {

    @Autowired
    private EntradaRepository entradaRepository;

    @GetMapping("/lista")
    public ResponseEntity<?> listarEntradas() {
        try {
            List<Entrada> lista = entradaRepository.findAll();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error al obtener la lista de entradas");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> actualizarEntrada(@PathVariable String id, @RequestBody Entrada entradaDetalles) {
        Map<String, Object> response = new HashMap<>();
        try {
            return entradaRepository.findById(id)
                .map(entrada -> {
                    entrada.setEvento(entradaDetalles.getEvento());
                    entrada.setFecha(entradaDetalles.getFecha());
                    entrada.setPrecio(entradaDetalles.getPrecio());
                    entrada.setCantidadDisponible(entradaDetalles.getCantidadDisponible());
                    // Asegúrate de actualizar la ubicación si es necesario
                    entrada.setUbicacion(entradaDetalles.getUbicacion());
                    
                    entradaRepository.save(entrada);
                    response.put("mensaje", "Entrada actualizada correctamente");
                    return ResponseEntity.ok(response);
                }).orElseGet(() -> {
                    response.put("mensaje", "No se encontró la entrada");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEntrada(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (entradaRepository.existsById(id)) {
                entradaRepository.deleteById(id);
                response.put("mensaje", "Entrada eliminada con éxito");
                return ResponseEntity.ok(response);
            } else {
                response.put("mensaje", "La entrada no existe");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("error", "No se puede eliminar la entrada porque tiene ventas asociadas");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}