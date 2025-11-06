package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.dto.edit.AutorEditDto;
import com.Biblioteca.gestLibros.model.Autor;
import com.Biblioteca.gestLibros.services.AutorService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/autores")
public class AutorController {


    private final AutorService autservice;

    @GetMapping
    public ResponseEntity<List<Autor>> autor(){
        return new ResponseEntity<>(autservice.autGet(), HttpStatus.OK);
    }
    @GetMapping("/{id_autor}")
    public Autor autBs(@PathVariable Long id_autor){
        return autservice.findaut(id_autor);
    }
    @PostMapping("/crear")
    public ResponseEntity<String> saveAutor(@RequestBody Autor aut){
        autservice.saveAutor(aut);
        return new ResponseEntity<>("Se a registrado un nuevo autor!", HttpStatus.CREATED);
    }
    @DeleteMapping("/eliminar/{id_autor}")
    public ResponseEntity<String> eliminar(@PathVariable Long id_autor){
        autservice.deleteAutor(id_autor);
        return new ResponseEntity<>("El autor a sido eliminado correctamente", HttpStatus.OK);
    }

    @PutMapping("/editar/{id_autor}")
    public ResponseEntity<String> editar(@PathVariable Long id_autor, @RequestBody AutorEditDto ediAut){
        autservice.editAutor(id_autor, ediAut);
        return new ResponseEntity<>("El usuario a sido actualizado correctamente", HttpStatus.OK);
    }

}
