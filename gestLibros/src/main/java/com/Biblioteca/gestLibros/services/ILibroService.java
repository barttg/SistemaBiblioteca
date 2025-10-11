package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.CrearLibroDto;
import com.Biblioteca.gestLibros.model.Libro;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ILibroService {

    public List<Libro> libros();

    public void saveLibro(CrearLibroDto lib);

    public Libro findLibro(Long id_libro);

    public void deleteLibro(Long id_libro);

    public void editLibro(CrearLibroDto libro);
}
