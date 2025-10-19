package com.Biblioteca.gestLibros.dto;

import com.Biblioteca.gestLibros.model.Prestamo;
import com.Biblioteca.gestLibros.model.Reserva;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Long id_usuario;
    private String nombre;
    private List<Long> prestamos;
    private List<Long> reservas;

}
