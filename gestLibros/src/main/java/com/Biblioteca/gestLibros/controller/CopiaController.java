package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.dto.response.CopiaResponseDto;
import com.Biblioteca.gestLibros.dto.CopiaRquestDto;
import com.Biblioteca.gestLibros.dto.edit.CopiaEditDto;
import com.Biblioteca.gestLibros.dto.response.ResponseLibroDto;
import com.Biblioteca.gestLibros.services.CopiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/copias")
public class CopiaController {

    private final CopiaService copService;

    @GetMapping
    public ResponseEntity<List<CopiaResponseDto>>copiGet(){
        return new ResponseEntity<>(copService.getCopias(), HttpStatus.OK);
    }


    @PostMapping("/crear")
    public ResponseEntity<String> nuevaCopia(@RequestBody CopiaRquestDto copiR){
        copService.createCopia(copiR);
        return new ResponseEntity<>("Se a registrado una nueva copia con exito", HttpStatus.CREATED);
    }

     @GetMapping("/{id_copia}")
     public ResponseEntity<CopiaResponseDto> copias(@PathVariable Long id_copia){
         return new ResponseEntity<>(copService.findCopia(id_copia), HttpStatus.OK);
     }


     @DeleteMapping("/eliminar/{id_copia}")
     public ResponseEntity<String> eliminarCop(@PathVariable Long id_copia){
        copService.deleteCopia(id_copia);
        return  new ResponseEntity<>("Se a eliminado exitosamente la copia", HttpStatus.OK);
     }

     @PutMapping("/editar/{id_copia}")
    public ResponseEntity<String> editCopi(@PathVariable Long id_copia, @RequestBody CopiaEditDto copiEdit){
        copService.editCopia(id_copia, copiEdit);
        return new ResponseEntity<>("La copia se a actualizadp exitosamente", HttpStatus.OK);
     }
}
