package com.cibertec.alianzaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.alianzaweb.model.Producto;
import com.cibertec.alianzaweb.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    @Autowired
    private ProductoService service;

    
    @GetMapping("/listar")
    public List<Producto> listar() {
        return service.listar();
    }

  
    @GetMapping("/buscar/{id}")
    public Producto buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id);
    }

 
    @PostMapping("/crear")
    public Producto guardar(@RequestBody Producto p) {
        return service.guardar(p);
    }

  
    @PutMapping("/editar/{id}")
    public Producto actualizar(@PathVariable String id, @RequestBody Producto p) {
    	p.setId_producto(id); // importante para actualizar correctamente
        return service.guardar(p);
    }


    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable String id) {
        service.eliminar(id);
    }
}