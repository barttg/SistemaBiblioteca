package com.Biblioteca.gestLibros.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaResponseDto {

    private Long id_reserva;
    private Long id_usuario;
    private LocalDate fechaReserv;
    private LocalDate fechaExpira;
    private LocalDate fechaReservacion;
}
