package com.Biblioteca.gestLibros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroPrestadoDto {

    private String titulo;
    private String codigoCopia;
    private boolean devuelto;
}
