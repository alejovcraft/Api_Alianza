package com.cibertec.alianzaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.alianzaweb.model.Rubro;
import com.cibertec.alianzaweb.repository.RubroRepository;

@Service
public class RubroService {

    @Autowired
    private RubroRepository repo;

    public List<Rubro> listar() {
        return repo.findAll();
    }

    public Rubro obtener(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Rubro agregar(Rubro r) {
        r.setEstado("A");
        return repo.save(r);
    }

    public Rubro actualizar(Integer id, Rubro r) {
        Rubro existente = repo.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre_rubro(r.getNombre_rubro());
            return repo.save(existente);
        }
        return null;
    }

    public String eliminar(Integer id) {
        Rubro r = repo.findById(id).orElse(null);
        if (r != null) {
            r.setEstado("I");
            repo.save(r);
            return "Eliminado lógico";
        }
        return "No existe";
    }
}
