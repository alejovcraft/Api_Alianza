package com.cibertec.alianzaweb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cibertec.alianzaweb.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    
    // Traemos al usuario y su rol de golpe para que el JSON salga completo
    @Query("SELECT u FROM Usuario u JOIN FETCH u.rol")
    List<Usuario> listarUsuariosConRol();
    
    Usuario findByUsername(String username);
}