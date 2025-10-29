package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.dto.CrearLibroDto;
import com.Biblioteca.gestLibros.dto.edit.LibroEditDto;
import com.Biblioteca.gestLibros.dto.response.CopiaResponseDto;
import com.Biblioteca.gestLibros.dto.response.ResponseLibroDto;
import com.Biblioteca.gestLibros.model.Libro;
import com.Biblioteca.gestLibros.services.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/libros")
public class LibroController {


    private final LibroService librservice;

    @GetMapping
    public List<ResponseLibroDto> libross(){
        return librservice.libros();
    }
    @GetMapping("/{id_libro}")
    public ResponseLibroDto librov(@PathVariable Long id_libro){

        return librservice.findLibro(id_libro);
    }
    @PostMapping("/crear")
    public String nuevoLibro(@RequestBody CrearLibroDto libro){
        librservice.saveLibro(libro);
        return"Se a registrado un nuevo libro con exito";
    }
    @DeleteMapping("eliminar/{id_libro}")
    public String eliminarL(@PathVariable Long id_libro){
        librservice.deleteLibro(id_libro);
        return"El libro se a eliminado exitosamente";
    }
    @PutMapping("/edit/{id_libro}")
    public String editarLib(@PathVariable Long id_libro, @RequestBody LibroEditDto libro){
        librservice.editLibro(id_libro, libro);
        return"El libro se a actualizado exitosamente";
    }
    @GetMapping("/lista/{idLibro}")
    public List<CopiaResponseDto> copiasList(@PathVariable Long idLibro){
        return librservice.copiasLib(idLibro);
    }
}
