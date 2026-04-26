package com.cibertec.alianzaweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.alianzaweb.model.Solicitud;
import com.cibertec.alianzaweb.service.SolicitudService;

@RestController
@RequestMapping("/api/solicitud")
public class SolicitudController {

    @Autowired
    private SolicitudService service;

    @GetMapping("/listar")
    public List<Solicitud> listar() {
        return service.listar();
    }

    @PostMapping("/relacionar")
    public Solicitud relacionar(@RequestBody Solicitud s) {
        return service.relacionar(s);
    }
}
