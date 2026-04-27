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
 // Método para buscar una sola entrada por su ID (Para la pantalla de compra)
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarEntradaPorId(@PathVariable String id) {
        try {
            // Buscamos la entrada en la base de datos
            java.util.Optional<Entrada> entradaEncontrada = entradaRepository.findById(id);
            
            // Si la encontramos, la devolvemos con un OK (200)
            if (entradaEncontrada.isPresent()) {
                return ResponseEntity.ok(entradaEncontrada.get());
            } 
            // Si no existe, devolvemos el error (404)
            else {
                Map<String, Object> error = new HashMap<>();
                error.put("mensaje", "La entrada no existe");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Error interno al buscar la entrada");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
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
                    entrada.setUbicacion(entradaDetalles.getUbicacion());	
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
    
    @PostMapping("/crear")
    public ResponseEntity<?> crearEntrada(@RequestBody Entrada nuevaEntrada) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Le generamos un ID automático si es un partido nuevo
            if (nuevaEntrada.getIdEntrada() == null || nuevaEntrada.getIdEntrada().isEmpty()) {
                nuevaEntrada.setIdEntrada("E-" + System.currentTimeMillis());
            }
            
            // Si tu BD requiere una Ubicacion obligatoria, le asignamos la número 1 por defecto
            // (Si no es obligatoria, puedes borrar estas dos líneas)
            // Ubicacion ubi = new Ubicacion(); ubi.setIdUbicacion(1);
            // nuevaEntrada.setUbicacion(ubi);

            entradaRepository.save(nuevaEntrada);
            response.put("mensaje", "¡Partido creado con éxito!");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            response.put("error", "Error al crear el partido: " + e.getMessage());
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