package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.dto.NuevaCategoriaDto;
import com.Biblioteca.gestLibros.dto.edit.CategoriaEditDto;
import com.Biblioteca.gestLibros.dto.response.CategoriaResponseDto;
import com.Biblioteca.gestLibros.dto.response.ResponseLibroDto;
import com.Biblioteca.gestLibros.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService cateService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> categorias(){
        return new ResponseEntity<>(cateService.categorias(), HttpStatus.OK);
    }

    @GetMapping("/{id_categoria}")
    public ResponseEntity<CategoriaResponseDto> respuesta(@PathVariable Long id_categoria){
        return new ResponseEntity<>(cateService.categoriaFind(id_categoria), HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> nu0evaCategoria(@RequestBody NuevaCategoriaDto request){
        cateService.saveCategoria(request);
        return new ResponseEntity<>("Se a registrado exitosamente una nueva categoria", HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id_categoria}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id_categoria){
        cateService.deleteCategoria(id_categoria);
        return new ResponseEntity<>("La categoria a sido eliminada exitosamente", HttpStatus.OK);
    }

    @PutMapping("/editar/{id_categoria}")
    public ResponseEntity<String> editCategoria(@PathVariable Long id_categoria, @RequestBody CategoriaEditDto editdTO){
        cateService.editCategoria(id_categoria, editdTO);
        return new ResponseEntity<>("La categoria a sido actualizada exitosamente", HttpStatus.OK);
    }

}
