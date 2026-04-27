package com.cibertec.alianzaweb.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.alianzaweb.dto.RegistroVentaDTO;
import com.cibertec.alianzaweb.model.DuenoEntrada;
import com.cibertec.alianzaweb.model.Entrada;
import com.cibertec.alianzaweb.model.EntradaIndividual;
import com.cibertec.alianzaweb.model.Venta;
import com.cibertec.alianzaweb.model.VentaEntrada;
import com.cibertec.alianzaweb.repository.DuenoEntradaRepository;
import com.cibertec.alianzaweb.repository.EntradaIndividualRepository;
import com.cibertec.alianzaweb.repository.EntradaRepository;
import com.cibertec.alianzaweb.repository.UsuarioRepository;
import com.cibertec.alianzaweb.repository.VentaEntradaRepository;
import com.cibertec.alianzaweb.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService {

    @Autowired private VentaRepository ventaRepo;
    @Autowired private VentaEntradaRepository veRepo;
    @Autowired private EntradaIndividualRepository eiRepo;
    @Autowired private DuenoEntradaRepository duenoRepo;
    @Autowired private UsuarioRepository userRepo;
    @Autowired private EntradaRepository entradaRepo;

    
    
    @Transactional
    public void procesarCompra(RegistroVentaDTO dto) {
        
        // 1. Validar el evento y el stock
        Entrada partido = entradaRepo.findById(dto.getIdEntrada())
                .orElseThrow(() -> new RuntimeException("El evento no existe"));

        if (partido.getCantidadDisponible() < dto.getCantidad()) {
            throw new RuntimeException("¡Lo sentimos! Solo quedan " + partido.getCantidadDisponible() + " entradas disponibles.");
        }

        // 2. Actualizar stock del partido
        partido.setCantidadDisponible(partido.getCantidadDisponible() - dto.getCantidad());
        entradaRepo.save(partido); 
        
        // 3. Crear la Venta (Cabecera)
        Venta v = new Venta();
        v.setIdVenta("V-" + System.currentTimeMillis()); 
        v.setFecha_venta(LocalDateTime.now());            
        v.setUsuario(userRepo.findByUsername(dto.getUsername()));

        BigDecimal precio = partido.getPrecio();
        BigDecimal total = precio.multiply(new BigDecimal(dto.getCantidad()));
        v.setTotal(total);
        ventaRepo.save(v);

        // 4. Crear VentaEntrada (El detalle que agrupará a los asistentes)
        VentaEntrada ve = new VentaEntrada();
        ve.setIdVentaentrada("VE-" + System.currentTimeMillis()); 
        ve.setVenta(v);
        ve.setEntrada(partido);
        ve.setCantidad(dto.getCantidad());
        
        // Inicializamos la lista para evitar errores de puntero nulo
        ve.setEntradasIndividuales(new ArrayList<>()); 
        veRepo.save(ve);

        // 5. El Bucle de Asistentes (Aquí es donde se guarda cada entrada individual)
        for (var datosDueno : dto.getAsistentes()) {
            
            // A. Primero creamos al Dueño/Asistente
            DuenoEntrada dueno = new DuenoEntrada();
            dueno.setIdDuenoentrada("D-" + datosDueno.getDni() + "-" + (System.currentTimeMillis() % 1000));
            dueno.setNombre(datosDueno.getNombre());
            dueno.setApellido_p(datosDueno.getApellido_p());
            dueno.setApellido_m(datosDueno.getApellido_m());
            dueno.setDni(datosDueno.getDni());
            dueno.setTelefono(datosDueno.getTelefono());
            dueno.setGenero(datosDueno.getGenero());
            
            // Importante: Guardamos al dueño primero
            duenoRepo.save(dueno);

            // B. Luego creamos la Entrada Individual que vincula al asistente con la venta
            EntradaIndividual ei = new EntradaIndividual();
            ei.setIdEntradaIndividual("EI-" + UUID.randomUUID().toString().substring(0,8));
            ei.setVentaEntrada(ve);  // Vincula al detalle de la venta
            ei.setEntrada(partido);   // Vincula al partido
            ei.setDuenoEntrada(dueno); // Vincula al dueño recién guardado
            
            // Guardamos la entrada individual
            eiRepo.save(ei);
            
            // Opcional: añadimos a la lista del objeto 've'
            ve.getEntradasIndividuales().add(ei);
        }
    }
}
