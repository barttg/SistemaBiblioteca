package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.CrearLibroDto;
import com.Biblioteca.gestLibros.dto.edit.LibroEditDto;
import com.Biblioteca.gestLibros.dto.response.CopiaResponseDto;
import com.Biblioteca.gestLibros.dto.response.ResponseLibroDto;
import com.Biblioteca.gestLibros.model.Libro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ILibroService {

    public List<ResponseLibroDto> libros();

    public void saveLibro(CrearLibroDto lib);

    public ResponseLibroDto findLibro(Long id_libro);

    public void deleteLibro(Long id_libro);

    public ResponseLibroDto editLibro(Long id_original, LibroEditDto editLibroDt);

    public Optional<ResponseLibroDto> obtenerLibro(Long id);

    public List<CopiaResponseDto> copiasLib(Long idLibro);
}
