package com.Biblioteca.gestLibros.dto.edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservEditDto {

    private Long  id_reserva;
    private Long id_copia;


    //metodos utilitarios para validar si hay campos vacions
}
