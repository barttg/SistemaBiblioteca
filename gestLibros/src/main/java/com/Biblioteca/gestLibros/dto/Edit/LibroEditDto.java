package com.Biblioteca.gestLibros.dto.Edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroEditDto {
    private String titulo;
    private String editorial;
    private Integer anioPublicacion;
    private String isbn;
    private Long idAutor; // solo se necesita el id de Autor


    //Metodos utilitarios para poder verificar si un campo no fue llenado

    public boolean hasTitulo(){ return titulo !=null; }
    public boolean hastEditorial(){ return editorial !=null; }
    public boolean hasAnioPublicacion(){ return anioPublicacion !=null; }
    public boolean hasIsbn(){ return isbn !=null; }
    public boolean hasIdAutor(){ return idAutor !=null; }
}
