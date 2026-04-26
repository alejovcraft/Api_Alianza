package com.cibertec.alianzaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.alianzaweb.model.Posicion;
import com.cibertec.alianzaweb.repository.PosicionRepository;

@Service
public class PosicionService {

    @Autowired
    private PosicionRepository repo;

    public List<Posicion> listar() {
        return repo.findAll();
    }
}
