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
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarEntradaPorId(@PathVariable String id) {
        try {
            // Buscamos la entrada (Spring nos devuelve un 'Optional' por si no existe)
            java.util.Optional<Entrada> entradaEncontrada = entradaRepository.findById(id);

            // Si la encontramos, la devolvemos con un status 200 OK
            if (entradaEncontrada.isPresent()) {
                return ResponseEntity.ok(entradaEncontrada.get());
            } else {
                // Si no existe, devolvemos el error 404
                Map<String, Object> response = new HashMap<>();
                response.put("mensaje", "No se encontró la entrada con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error al buscar la entrada");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}