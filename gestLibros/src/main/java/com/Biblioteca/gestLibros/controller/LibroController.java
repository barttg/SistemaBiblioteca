package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.dto.CrearLibroDto;
import com.Biblioteca.gestLibros.dto.edit.LibroEditDto;
import com.Biblioteca.gestLibros.dto.response.CopiaResponseDto;
import com.Biblioteca.gestLibros.dto.response.ResponseLibroDto;
import com.Biblioteca.gestLibros.model.Libro;
import com.Biblioteca.gestLibros.services.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/libros")
public class LibroController {


    private final LibroService librservice;

    @GetMapping
    public ResponseEntity<List<ResponseLibroDto>>librosGet(){
        return new ResponseEntity<>(librservice.libros(), HttpStatus.OK);
    }

    @GetMapping("/{id_libro}")
    public ResponseEntity<ResponseLibroDto> libroFind(@PathVariable Long id_libro){
        return new ResponseEntity<>(librservice.findLibro(id_libro), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<String>nuevoLib(@RequestBody CrearLibroDto libro){
        librservice.saveLibro(libro);
        return new ResponseEntity<>("El libro a sido registrado exitosamente", HttpStatus.CREATED);
    }

    @DeleteMapping("eliminar/{id_libro}")
    public ResponseEntity<String> eliminarLib(@PathVariable Long id_libro){
        librservice.deleteLibro(id_libro);
        return new ResponseEntity<>("El libro a sido eliminado correctamente", HttpStatus.OK);
    }

    @PutMapping("/edit/{id_libro}")
    public ResponseEntity<String> editLibro(@PathVariable Long id_libro, @RequestBody LibroEditDto libro){
        librservice.editLibro(id_libro, libro);
        return new ResponseEntity<>("El libro a sido actualizado exitosamente", HttpStatus.CREATED);
    }


    @GetMapping("/lista/{idLibro}")
    public ResponseEntity<List<CopiaResponseDto>> cpiaList(@PathVariable Long idLibro){
        return new ResponseEntity<>(librservice.copiasLib(idLibro), HttpStatus.OK);
    }

}
