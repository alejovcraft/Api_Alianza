package com.cibertec.alianzaweb.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cibertec.alianzaweb.dto.RegistroRequest;
import com.cibertec.alianzaweb.model.Persona;
import com.cibertec.alianzaweb.model.Rol;
import com.cibertec.alianzaweb.model.Usuario;
import com.cibertec.alianzaweb.repository.PersonaRepository;
import com.cibertec.alianzaweb.repository.RolRepository;
import com.cibertec.alianzaweb.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;
    
    @Autowired
    private PersonaRepository personaRepo;
    
    @Autowired
    private RolRepository rolRepo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuarios() {
   
        return usuarioRepo.listarUsuariosConRol(); 
    }
    
    @org.springframework.transaction.annotation.Transactional 
    public Usuario registrarNuevoHincha(RegistroRequest dto) {
        
      
        Persona nuevaPersona = new Persona();
        
        nuevaPersona.setId_persona("P" + System.currentTimeMillis()); 
        nuevaPersona.setDni(dto.getDni());
        nuevaPersona.setNombre_persona(dto.getNombre());
        nuevaPersona.setApellido_persona(dto.getApellido());
        nuevaPersona.setTelefono(dto.getTelefono());
        nuevaPersona.setEmail(dto.getEmail());
        
        
        personaRepo.save(nuevaPersona);

        
        Rol rolHincha = rolRepo.findById("R02")
                .orElseThrow(() -> new RuntimeException("El Rol R02 no existe en la BD"));

       
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setId_usuario("U" + System.currentTimeMillis());
        nuevoUsuario.setUsername(dto.getUsername());
        nuevoUsuario.setPassword(passwordEncoder.encode(dto.getPassword())); 
        nuevoUsuario.setPersona(nuevaPersona); 
        nuevoUsuario.setRol(rolHincha);  
        nuevoUsuario.setFecha_creacion(new Date()); 
        
  
        return usuarioRepo.save(nuevoUsuario);
    }
    
    @org.springframework.transaction.annotation.Transactional
    public void actualizarRolEstado(Usuario usuarioActualizado) {
        
        Usuario usuarioExistente = usuarioRepo.findById(usuarioActualizado.getId_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

 
        if (usuarioActualizado.getRol() != null) {
            Rol nuevoRol = rolRepo.findById(usuarioActualizado.getRol().getId_rol())
                    .orElseThrow(() -> new RuntimeException("Rol no válido"));
            usuarioExistente.setRol(nuevoRol);
        }
        
       
        usuarioRepo.save(usuarioExistente);
    }
    
    
}