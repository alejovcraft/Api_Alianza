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

import com.cibertec.alianzaweb.model.Jugador;
import com.cibertec.alianzaweb.service.JugadorService;

@RestController
@RequestMapping("/api/jugador")
public class JugadorController {

    @Autowired
    private JugadorService service;

    @GetMapping("/listar")
    public List<Jugador> listarJugador() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Jugador obtenerJugador(@PathVariable String id) {
        return service.obtener(id);
    }

    @PostMapping("/agregar")
    public String agregarJugador(@RequestBody Jugador j) {
        return service.agregar(j);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarJugador(@PathVariable String id) {
        return service.eliminar(id);
    }
}
