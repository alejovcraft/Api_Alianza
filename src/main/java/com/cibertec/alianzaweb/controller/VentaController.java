package com.cibertec.alianzaweb.controller;

import com.cibertec.alianzaweb.dto.RegistroVentaDTO;
import com.cibertec.alianzaweb.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "http://localhost:4200")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping("/procesar")
    public ResponseEntity<?> procesarCompra(@RequestBody RegistroVentaDTO compraDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Llamamos al service que orquesta todo el guardado en cascada
            ventaService.procesarCompra(compraDto);
            
            response.put("mensaje", "¡Compra realizada con éxito! Tus entradas están listas.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            response.put("mensaje", "Hubo un problema al procesar tu compra");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}