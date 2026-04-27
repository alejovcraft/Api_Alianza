package com.cibertec.alianzaweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.alianzaweb.model.Producto;
import com.cibertec.alianzaweb.repository.ProductoRepository;



@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

  
    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto buscarPorId(String id) {
        Optional<Producto> producto = repo.findById(id);
        return producto.orElse(null);
    }

    
    public Producto guardar(Producto p) {
        return repo.save(p);
    }
    
    public Producto actualizar(String id, Producto p) {
        Optional<Producto> existente = repo.findById(id);

        if (existente.isPresent()) {
            Producto prod = existente.get();

            prod.setNombre(p.getNombre());
            prod.setDescripcion(p.getDescripcion());
            prod.setPrecio(p.getPrecio());
            prod.setStock(p.getStock());

            return repo.save(prod);
        }

        return null; 
    }

    public boolean eliminar(String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}