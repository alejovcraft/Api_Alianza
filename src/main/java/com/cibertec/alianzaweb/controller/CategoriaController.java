package com.cibertec.alianzaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.alianzaweb.model.Categoria;
import com.cibertec.alianzaweb.service.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/listar")
    public List<Categoria> listarCategoria() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Categoria obtenerCategoria(@PathVariable String id) {
        return service.obtener(id);
    }

    @PostMapping("/agregar")
    public String agregarCategoria(@RequestBody Categoria c) {
        return service.agregar(c);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable String id) {
        return service.eliminar(id);
    }
}