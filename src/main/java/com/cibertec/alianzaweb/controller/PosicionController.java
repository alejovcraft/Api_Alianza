package com.cibertec.alianzaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.alianzaweb.model.Posicion;
import com.cibertec.alianzaweb.service.PosicionService;

@RestController
@RequestMapping("/api/posicion")
public class PosicionController {

    @Autowired
    private PosicionService service;

    @GetMapping("/listar")
    public List<Posicion> listarPosicion() {
        return service.listar();
    }
}
