package com.cibertec.alianzaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.alianzaweb.model.Puesto;
import com.cibertec.alianzaweb.service.PuestoService;

@RestController
@RequestMapping("/api/puesto")
public class PuestoController {

    @Autowired
    private PuestoService service;

    @GetMapping("/listar")
    public List<Puesto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Puesto obtener(@PathVariable Integer id) {
        return service.obtener(id);
    }

    @PostMapping("/agregar")
    public Puesto agregar(@RequestBody Puesto p) {
        return service.agregar(p);
    }

    @PutMapping("/actualizar/{id}")
    public Puesto actualizar(@PathVariable Integer id, @RequestBody Puesto p) {
        return service.actualizar(id, p);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        return service.eliminar(id);
    }
}
