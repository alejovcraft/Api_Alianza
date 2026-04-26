package com.cibertec.alianzaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.alianzaweb.model.Postulante;
import com.cibertec.alianzaweb.repository.PostulanteRepository;

@Service
public class PostulanteService {

    @Autowired
    private PostulanteRepository repo;

    public List<Postulante> listar() {
        return repo.findAll();
    }

    public Postulante obtener(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Postulante agregar(Postulante p) {
        p.setEstado("A");
        return repo.save(p);
    }

    public Postulante actualizar(Integer id, Postulante p) {
        Postulante existente = repo.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre(p.getNombre());
            existente.setApellido(p.getApellido());
            existente.setTelefono(p.getTelefono());
            existente.setEmail(p.getEmail());
            existente.setDireccion(p.getDireccion());
            return repo.save(existente);
        }
        return null;
    }

    public String eliminar(Integer id) {
        Postulante p = repo.findById(id).orElse(null);
        if (p != null) {
            p.setEstado("I");
            repo.save(p);
            return "Eliminado lógico";
        }
        return "No existe";
    }
}
