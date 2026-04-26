package com.cibertec.alianzaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.alianzaweb.model.Tiempo;
import com.cibertec.alianzaweb.repository.TiempoRepository;

@Service
public class TiempoService {

    @Autowired
    private TiempoRepository repo;

    public List<Tiempo> listar() {
        return repo.findAll();
    }

    public Tiempo obtener(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Tiempo agregar(Tiempo t) {
        t.setEstado("A");
        return repo.save(t);
    }

    public Tiempo actualizar(Integer id, Tiempo t) {
        Tiempo existente = repo.findById(id).orElse(null);
        if (existente != null) {
            existente.setTipo_tiempo(t.getTipo_tiempo());
            return repo.save(existente);
        }
        return null;
    }

    public String eliminar(Integer id) {
        Tiempo t = repo.findById(id).orElse(null);
        if (t != null) {
            t.setEstado("I");
            repo.save(t);
            return "Eliminado lógico";
        }
        return "No existe";
    }
}
