package com.cibertec.alianzaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.alianzaweb.model.Categoria;
import com.cibertec.alianzaweb.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public List<Categoria> listar() {
        return repo.findAll();
    }

    public Categoria obtener(String id) {
        return repo.findById(id).orElse(null);
    }

    public String agregar(Categoria c) {
        if (repo.existsById(c.getId_categoria())) {
            return "Categoria ya existe";
        }
        c.setEstado("A");
        repo.save(c);
        return "Categoria registrada";
    }

    public String eliminar(String id) {
        Categoria c = repo.findById(id).orElse(null);
        if (c != null) {
            c.setEstado("I");
            repo.save(c);
            return "Categoria eliminada";
        }
        return "No existe";
    }
}
