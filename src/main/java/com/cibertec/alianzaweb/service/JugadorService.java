package com.cibertec.alianzaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.alianzaweb.model.Jugador;
import com.cibertec.alianzaweb.repository.JugadorRepository;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository repo;

    public List<Jugador> listar() {
        return repo.findAll();
    }

    public Jugador obtener(String id) {
        return repo.findById(id).orElse(null);
    }

    public String agregar(Jugador j) {
        if (repo.existsById(j.getId_jugador())) {
            return "Jugador ya existe";
        }
        j.setEstado("A");
        repo.save(j);
        return "Jugador registrado";
    }

    public String eliminar(String id) {
        Jugador j = repo.findById(id).orElse(null);
        if (j != null) {
            j.setEstado("I");
            repo.save(j);
            return "Jugador eliminado (lógico)";
        }
        return "Jugador no existe";
    }
}
