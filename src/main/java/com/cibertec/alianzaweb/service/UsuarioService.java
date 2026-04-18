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
        // Usamos tu método optimizado
        return usuarioRepo.listarUsuariosConRol(); 
    }
    
    @org.springframework.transaction.annotation.Transactional 
    public Usuario registrarNuevoHincha(RegistroRequest dto) {
        
        // 1. CREAR LA PERSONA
        Persona nuevaPersona = new Persona();
        // Truco: Como tus IDs son Varchar(30), generamos uno único con P + la hora en milisegundos
        nuevaPersona.setId_persona("P" + System.currentTimeMillis()); 
        nuevaPersona.setDni(dto.getDni());
        nuevaPersona.setNombre_persona(dto.getNombre());
        nuevaPersona.setApellido_persona(dto.getApellido());
        nuevaPersona.setTelefono(dto.getTelefono());
        nuevaPersona.setEmail(dto.getEmail());
        
        // Guardamos la persona primero porque el Usuario depende de ella
        personaRepo.save(nuevaPersona);

        // 2. BUSCAR EL ROL "R02" POR DEFECTO
        Rol rolHincha = rolRepo.findById("R02")
                .orElseThrow(() -> new RuntimeException("El Rol R02 no existe en la BD"));

        // 3. CREAR EL USUARIO
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setId_usuario("U" + System.currentTimeMillis());
        nuevoUsuario.setUsername(dto.getUsername());
        nuevoUsuario.setPassword(passwordEncoder.encode(dto.getPassword())); // ¡ENCRIPTAMOS LA CLAVE AQUÍ!
        nuevoUsuario.setPersona(nuevaPersona); // Vinculamos la persona que acabamos de crear
        nuevoUsuario.setRol(rolHincha); // Le asignamos el rol
        nuevoUsuario.setFecha_creacion(new Date()); // Fecha de hoy
        
        // Guardamos el usuario final
        return usuarioRepo.save(nuevoUsuario);
    }
    
    @org.springframework.transaction.annotation.Transactional
    public void actualizarRolEstado(Usuario usuarioActualizado) {
        // Buscamos el usuario existente
        Usuario usuarioExistente = usuarioRepo.findById(usuarioActualizado.getId_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizamos solo lo necesario (el Rol en este caso)
        if (usuarioActualizado.getRol() != null) {
            Rol nuevoRol = rolRepo.findById(usuarioActualizado.getRol().getId_rol())
                    .orElseThrow(() -> new RuntimeException("Rol no válido"));
            usuarioExistente.setRol(nuevoRol);
        }
        
        // Guardamos los cambios
        usuarioRepo.save(usuarioExistente);
    }
}