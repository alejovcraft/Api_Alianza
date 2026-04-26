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

import com.cibertec.alianzaweb.model.Postulante;
import com.cibertec.alianzaweb.service.PostulanteService;

@RestController
@RequestMapping("/api/postulante")
public class PostulanteController {

    @Autowired
    private PostulanteService service;

    @GetMapping("/listar")
    public List<Postulante> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Postulante obtener(@PathVariable Integer id) {
        return service.obtener(id);
    }

    @PostMapping("/agregar")
    public Postulante agregar(@RequestBody Postulante p) {
        return service.agregar(p);
    }

    @PutMapping("/actualizar/{id}")
    public Postulante actualizar(@PathVariable Integer id, @RequestBody Postulante p) {
        return service.actualizar(id, p);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        return service.eliminar(id);
    }
}
