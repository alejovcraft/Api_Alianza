package com.cibertec.alianzaweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.alianzaweb.dto.LoginRequest;
import com.cibertec.alianzaweb.dto.RegistroRequest;
import com.cibertec.alianzaweb.model.Usuario;
import com.cibertec.alianzaweb.repository.UsuarioRepository;
import com.cibertec.alianzaweb.service.UsuarioService;
import com.cibertec.alianzaweb.util.JwtUtil;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    
    @Autowired
    private UsuarioRepository usuarioRepo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<?> listarUsuarios() {
        try {
            List<Usuario> lista = service.listarUsuarios();
            return ResponseEntity.ok(lista);
            
        } catch (Exception e) {
            // Usamos un Map para mandar un JSON de error estructurado
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error al listar los usuarios en la base de datos");
            response.put("error", e.getMessage());
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginData) {
        Map<String, Object> response = new HashMap<>();

        Usuario usuarioEnBD = usuarioRepo.findByUsername(loginData.getUsername());

        if (usuarioEnBD == null) {
            response.put("mensaje", "Error: El usuario no existe");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // Si la clave es correcta...
        if (passwordEncoder.matches(loginData.getPassword(), usuarioEnBD.getPassword())) {
            
            // ¡AQUÍ ESTÁ LA MAGIA! Fabricamos el Token JWT
            String tokenGenerado = jwtUtil.generarToken(usuarioEnBD.getUsername(), usuarioEnBD.getRol().getNombre_rol());
            
            // Preparamos la respuesta para Angular
            response.put("mensaje", "¡Bienvenido al sistema, " + usuarioEnBD.getUsername() + "!");
            response.put("token", tokenGenerado); // Le mandamos la pulsera VIP
            response.put("username", usuarioEnBD.getUsername());
            response.put("rol", usuarioEnBD.getRol().getNombre_rol()); 
            
            return ResponseEntity.ok(response); 
            
        } else {
            response.put("mensaje", "Error: Contraseña incorrecta");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody RegistroRequest registroData) {
        try {
            Usuario usuarioCreado = service.registrarNuevoHincha(registroData);
            
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "¡Registro exitoso! Ya eres parte de la familia Blanquiazul.");
            response.put("usuario", usuarioCreado.getUsername());
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response); // Status 201 Created
            
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error al registrar el usuario. Es posible que el DNI, Email o Usuario ya estén en uso.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
}