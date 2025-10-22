package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.dto.edit.AutorEditDto;
import com.Biblioteca.gestLibros.model.Autor;
import com.Biblioteca.gestLibros.services.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/autores")
public class AutorController {


    private final AutorService autservice;

    @GetMapping
    public List<Autor> autors(){
        return autservice.autGet();
    }
    @GetMapping("/{id_autor}")
    public Autor autBs(@PathVariable Long id_autor){
        return autservice.findaut(id_autor);
    }
    @PostMapping("/crear")
    public String nuevoAut(@RequestBody Autor autor){
        autservice.saveAutor(autor);
        return"Se a registrado un nuevo autor con exito";
    }
    @DeleteMapping("/eliminar/{id_autor}")
    public String autormenos(@PathVariable Long id_autor){
        autservice.deleteAutor(id_autor);
        return"El autor se a eliminado exitosamente junto a sus libros";
    }
    @PutMapping("/editar/{id_autor}")
    public String editar(@PathVariable Long id_autor, @RequestBody AutorEditDto autor){
        autservice.editAutor(id_autor, autor);
        return"Se a actualizado el autor exitosamente";
    }
}
