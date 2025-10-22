package com.Biblioteca.gestLibros.dto.edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoEditDto {

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private Long id_usuario;
    private String estado;

    //metodos utilitarios para validar si algun campo no fue asignado
    public boolean hasIdUsuario(){ return id_usuario !=null; }
    public boolean hasFechaPrestamo(){ return fechaPrestamo != null;}
    public boolean hasFechaDevolucionn(){ return fechaDevolucion !=null; }
    public boolean hasEstado(){ return  estado !=null; }

}
