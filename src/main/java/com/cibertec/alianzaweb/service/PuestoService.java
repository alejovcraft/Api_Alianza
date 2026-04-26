package com.cibertec.alianzaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.alianzaweb.model.Puesto;
import com.cibertec.alianzaweb.repository.PuestoRepository;

@Service
public class PuestoService {

    @Autowired
    private PuestoRepository repo;

    public List<Puesto> listar() {
        return repo.findAll();
    }

    public Puesto obtener(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Puesto agregar(Puesto p) {
        p.setEstado("A");
        return repo.save(p);
    }

    public Puesto actualizar(Integer id, Puesto p) {
        Puesto existente = repo.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre_puesto(p.getNombre_puesto());
            existente.setDescripcion(p.getDescripcion());
            existente.setRubro(p.getRubro());
            existente.setTiempo(p.getTiempo());
            return repo.save(existente);
        }
        return null;
    }

    public String eliminar(Integer id) {
        Puesto p = repo.findById(id).orElse(null);
        if (p != null) {
            p.setEstado("I");
            repo.save(p);
            return "Eliminado lógico";
        }
        return "No existe";
    }
}
