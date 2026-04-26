package com.cibertec.alianzaweb.dto;

import java.util.List;

import lombok.Data;

@Data
public class RegistroVentaDTO {
    private String idEntrada;
    private String username;
    private Integer cantidad;
    private List<AsistenteDTO> asistentes;

    @Data
    public static class AsistenteDTO {
        private String nombre;
        private String apellido_p;
        private String apellido_m;
        private String dni;
        private String telefono;
        private String genero;
    }
}
