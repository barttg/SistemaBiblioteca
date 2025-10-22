package com.Biblioteca.gestLibros.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLibroDto {
    private  Long idLibro;
    private String titulo;
    private Integer anioPublicacion;
    private String editorial;
    private String isbn;
    private String autorNombre;

}
