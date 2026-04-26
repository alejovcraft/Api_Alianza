package com.cibertec.alianzaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.alianzaweb.model.Solicitud;
import com.cibertec.alianzaweb.repository.SolicitudRepository;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository repo;

    public List<Solicitud> listar() {
        return repo.findAll();
    }

    public Solicitud relacionar(Solicitud s) {
        return repo.save(s);
    }
}
