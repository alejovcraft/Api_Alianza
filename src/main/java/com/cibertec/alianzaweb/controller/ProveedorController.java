package com.cibertec.alianzaweb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/proveedor")
@CrossOrigin(origins = "http://localhost:4200")
public class ProveedorController {

	/*
    @Autowired
    private ProductoService productoService; // Debes crear este Service

    @PostMapping("/productos/registrar")
    public ResponseEntity<?> registrarProducto(@RequestBody Producto producto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Producto nuevo = productoService.guardar(producto);
            response.put("mensaje", "Producto registrado en el inventario");
            response.put("producto", nuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("mensaje", "Error al registrar producto");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/mis-productos")
    public ResponseEntity<?> listarProductos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }
    */
}
