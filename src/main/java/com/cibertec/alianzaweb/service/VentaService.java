package com.cibertec.alianzaweb.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    	
        Entrada partido = entradaRepo.findById(dto.getIdEntrada())
                .orElseThrow(() -> new RuntimeException("El evento no existe"));

        if (partido.getCantidadDisponible() < dto.getCantidad()) {
            throw new RuntimeException("¡Lo sentimos! Solo quedan " + partido.getCantidadDisponible() + " entradas disponibles.");
        }

        partido.setCantidadDisponible(partido.getCantidadDisponible() - dto.getCantidad());
        entradaRepo.save(partido); 
    	
    	Venta v = new Venta();
    	v.setIdVenta("V-" + System.currentTimeMillis()); 
        v.setFecha_venta(LocalDateTime.now());            
        v.setUsuario(userRepo.findByUsername(dto.getUsername()));
        

        BigDecimal precio = entradaRepo.findById(dto.getIdEntrada()).get().getPrecio();
        BigDecimal total = precio.multiply(new BigDecimal(dto.getCantidad()));
        v.setTotal(total);
        
        ventaRepo.save(v);

        // 2. Crear VentaEntrada	
        VentaEntrada ve = new VentaEntrada();
        ve.setIdVentaentrada("VE-" + System.currentTimeMillis()); // Sin guion bajo
        ve.setVenta(v);
        ve.setEntrada(entradaRepo.findById(dto.getIdEntrada()).get());
        ve.setCantidad(dto.getCantidad());
        veRepo.save(ve);

        for (var datosDueno : dto.getAsistentes()) {
            EntradaIndividual ei = new EntradaIndividual();
          
            ei.setIdEntradaIndividual("EI-" + java.util.UUID.randomUUID().toString().substring(0,8));
            ei.setVentaEntrada(ve);
            ei.setEntrada(entradaRepo.findById(dto.getIdEntrada()).get());
            eiRepo.save(ei);

            DuenoEntrada dueno = new DuenoEntrada();
            
            dueno.setIdDuenoentrada("D-" + datosDueno.getDni());
            dueno.setEntradaIndividual(ei);
            dueno.setNombre(datosDueno.getNombre());
            dueno.setApellido_p(datosDueno.getApellido_p());
            dueno.setApellido_m(datosDueno.getApellido_m());
            dueno.setDni(datosDueno.getDni());
            dueno.setTelefono(datosDueno.getTelefono());
            dueno.setGenero(datosDueno.getGenero());
            
            duenoRepo.save(dueno);
        }
    
    }
}
