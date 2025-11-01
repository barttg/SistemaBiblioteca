package com.Biblioteca.gestLibros.dto.edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservEditDto {

    private Long  id_reserva;
    private Long idUsuario;
    private LocalDate fechaATomar;


    //metodos utilitarios para validar si hay campos vacios

    public boolean hasIdReserva(){return id_reserva !=null; }
    public boolean hasIdUsuario(){return idUsuario != null; }
    public boolean hasFechaATomar(){return  fechaATomar != null; }
}
