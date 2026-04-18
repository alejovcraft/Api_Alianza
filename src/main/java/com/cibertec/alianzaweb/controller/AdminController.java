package com.cibertec.alianzaweb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.alianzaweb.model.Usuario;
import com.cibertec.alianzaweb.service.UsuarioService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios para la tabla de Angular
    @GetMapping("/gestion-usuarios")
    public ResponseEntity<?> listarParaGestion() {
        try {
			return ResponseEntity.ok(usuarioService.listarUsuarios());
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
			response.put("mensaje", "Error al obtener lista de gestion");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			
		}
    }

    // Actualizar estado (ahora recibimos el objeto y devolvemos respuesta limpia)
    @PutMapping("/usuarios/actualizar-status")
    public ResponseEntity<?> actualizarRolEstado(@RequestBody Usuario usuario) {
        usuarioService.actualizarRolEstado(usuario);
        return ResponseEntity.ok().build();
    }
    
 // Cambiar el rol o estado de un usuario
    @PostMapping("/usuarios/actualizar")
    public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.actualizarRolEstado(usuario);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Usuario actualizado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar");
        }
    }
	
}
