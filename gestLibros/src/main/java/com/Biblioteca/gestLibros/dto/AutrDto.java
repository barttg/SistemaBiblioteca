package com.Biblioteca.gestLibros.dto;

import com.Biblioteca.gestLibros.model.Libro;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutrDto {

    private Long id_autor;
    private String nombreAutor;
    private List<Libro> libros;

}
