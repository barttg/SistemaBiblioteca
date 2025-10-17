package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.dto.CrearLibroDto;
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
    public List<Libro> libross(){
        return librservice.libros();
    }
    @GetMapping("/{id_ibro}")
    public Libro librov(@PathVariable Long id_libro){

        return librservice.findLibro(id_libro);
    }
    @PostMapping("/crear")
    public String nueviLibro(@RequestBody CrearLibroDto libro){
        librservice.saveLibro(libro);
        return"Se a registrado un nuevo libro con exito";
    }
    @DeleteMapping("eliminar/{id_libro}")
    public String eliminarL(@PathVariable Long id_libro){
        librservice.deleteLibro(id_libro);
        return"El libro se a eliminado exitosamente";
    }
    @PutMapping("/edit/{id}")
    public String editarLib(@PathVariable Long id_libro, @RequestBody Libro libro){
        return"El libro se a actualizado exitosamente";
    }
}
