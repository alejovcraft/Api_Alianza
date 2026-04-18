package com.cibertec.alianzaweb.service;

import java.util.List;

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

	    public Producto guardar(Producto p) {
	        return repo.save(p);
	    }

	    public void eliminar(String id) {
	        repo.deleteById(id);
	    }

}
