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

import com.cibertec.alianzaweb.model.Tiempo;
import com.cibertec.alianzaweb.service.TiempoService;

@RestController
@RequestMapping("/api/tiempo")
public class TiempoController {

    @Autowired
    private TiempoService service;

    @GetMapping("/listar")
    public List<Tiempo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Tiempo obtener(@PathVariable Integer id) {
        return service.obtener(id);
    }

    @PostMapping("/agregar")
    public Tiempo agregar(@RequestBody Tiempo t) {
        return service.agregar(t);
    }

    @PutMapping("/actualizar/{id}")
    public Tiempo actualizar(@PathVariable Integer id, @RequestBody Tiempo t) {
        return service.actualizar(id, t);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        return service.eliminar(id);
    }
}
