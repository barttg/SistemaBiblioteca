package com.Biblioteca.gestLibros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearLibroDto {

    private String titulo;
    private String isbn;
    private String editorial;
    private int anioPublicacion;
    private Long id_autor;
    private int cantidadCopias;

}
