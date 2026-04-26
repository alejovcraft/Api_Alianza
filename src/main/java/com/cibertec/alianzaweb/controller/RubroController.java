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

import com.cibertec.alianzaweb.model.Rubro;
import com.cibertec.alianzaweb.service.RubroService;

@RestController
@RequestMapping("/api/rubro")
public class RubroController {

    @Autowired
    private RubroService service;

    @GetMapping("/listar")
    public List<Rubro> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Rubro obtener(@PathVariable Integer id) {
        return service.obtener(id);
    }

    @PostMapping("/agregar")
    public Rubro agregar(@RequestBody Rubro r) {
        return service.agregar(r);
    }

    @PutMapping("/actualizar/{id}")
    public Rubro actualizar(@PathVariable Integer id, @RequestBody Rubro r) {
        return service.actualizar(id, r);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        return service.eliminar(id);
    }
}
